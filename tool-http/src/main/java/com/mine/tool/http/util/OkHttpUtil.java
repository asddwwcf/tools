package com.mine.tool.http.util;

import com.mine.tool.http.handler.*;
import com.mine.tool.http.handler.request.RequestInfo;
import com.mine.tool.http.handler.response.ByteResponseHandler;
import com.mine.tool.http.handler.response.DefaultResponseHandler;
import com.mine.tool.http.handler.response.PingResponseHandler;
import com.mine.tool.http.handler.sign.SignInfo;
import com.mine.tool.http.handler.ssl.SSLAllHandler;
import com.mine.tool.http.handler.ssl.SSLSpecifiedHandler;
import com.mine.tool.http.handler.ssl.TrustAllManager;
import com.mine.tool.http.interceptor.LogInterceptor;
import com.mine.tool.http.interceptor.RetryIntercepter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 功能 :
 * Http核心功能:
 * 1.get方式封装
 * 2.post方式疯转
 * 3.posJson方式封装
 * 4.文件下载方式封装
 * 5.签名方法抽象.
 * 6.增加url地址参数,和body参数的区分;
 */

@Slf4j
@NoArgsConstructor(staticName = "create")
public class OkHttpUtil {

    public static final ThreadLocal<LogInfo> httpLogInfo = new ThreadLocal<>();

    public static final String REQUEST_URL = "URL";
    public static final String REQUEST_FORM = "FORM";
    public static final String REQUEST_JSON = "JSON";
    public static final String REQUEST_XML = "XML";
    public static final String REQUEST_FILE = "FILE";

    public static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
    public static final MediaType HTML = MediaType.parse("text/html; charset=utf-8");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml;charset=UTF-8");
    public static final MediaType DATA = MediaType.parse("application/octet-stream;charset=UTF-8");
    public static final String REQUEST_TAG = "tool-rpc-http";

    private static class ClientHolder{
        private static OkHttpClient okHttpClient = new OkHttpClient();
    }

    private Timeout timeout = Timeout.builder().connectTimeout(5).writeTimeout(5).readTimeout(5).build();
    private TimeUnit unit = TimeUnit.SECONDS;

    /**是否打印日志**/
    private boolean print=true;
    /**默认会重试**/
    private boolean retry=false;

    private String url;
    private boolean ping = false;
    private HttpUrl httpUrl;
    /**是否对参数做字母排序**/
    private boolean sort = false;
    /**get请求是否编码**/
    private boolean encode = true;

    /**证书路径:不传,则默认信任所有证书**/
    private String cert;
    /**证书的密码**/
    private String password;

    /**重试参数**/
    private Integer maxRetry = 3;
    private Integer[] interval = new Integer[]{500};
    private RetryHandler retryHandler;

    private SignInfo signInfo;
    private SignHandler signHandler;
    /**追加到url地址上的参数(get/post/postjson/put/putjson都可能有)**/
    private Map<String, Object> urlParams = Collections.synchronizedMap(new LinkedHashMap<>());
    /**追加到body里边的地址上的参数(get一般不会有)**/
    private Map<String, Object> bodyParams = Collections.synchronizedMap(new LinkedHashMap<>());
    /**头参数**/
    private Map<String, Object> headerParams = Collections.synchronizedMap(new LinkedHashMap<>());
    /**追加到body里边的地址上的参数(get一般不会有),无key的参数**/
    private List<Object> listBodyParam = new ArrayList<>();
    /**设置请求参数,无key**/
    private Object bodyParam = null;

    /**构建请求对象**/
    protected OkHttpClient build() {
        OkHttpClient.Builder builder = ClientHolder.okHttpClient.newBuilder();

        // 日志拦截器
        if(print){ builder.addInterceptor(new LogInterceptor()); }

        // 超时设置
        builder.connectTimeout(timeout.getConnectTimeout(), this.unit)
                .writeTimeout(timeout.getWriteTimeout(), this.unit)
                .readTimeout(timeout.getReadTimeout(), this.unit);

        // 重试设置
        if(retry){
            if( Objects.nonNull(retryHandler) ){
                builder.addInterceptor(new RetryIntercepter(maxRetry, interval, retryHandler));
            }else{
                builder.addInterceptor(new RetryIntercepter(maxRetry,interval));
            }
        }

        if( !this.httpUrl.isHttps() ){
            return builder.build();
        }

        // 增加证书逻辑
        if(StringUtils.isNotBlank(cert)){
            SSLSpecifiedHandler handler = SSLSpecifiedHandler.build();
            builder.sslSocketFactory(
                    handler.factory(cert,password),
                    handler.trustManager()
            ).hostnameVerifier((s, sslSession) -> true);
        }else{
            builder.sslSocketFactory(
                    SSLAllHandler.factory(),
                    new TrustAllManager()
            ).hostnameVerifier((s, sslSession) -> true);
        }

        return builder.build();
    }

    /**设置超时时间**/
    public OkHttpUtil timeout(Timeout timeout){
        this.timeout = timeout;
        return this;
    }

    /**设置超时时间**/
    public OkHttpUtil timeout(Integer connect, Integer read, Integer write){
        this.timeout.setConnectTimeout(connect);
        this.timeout.setWriteTimeout(write);
        this.timeout.setReadTimeout(read);
        return this;
    }

    // ---------------------------------------------重试次数设置----------------------------------------------- //

    /**控制是否重试的开关**/
    public OkHttpUtil retry(boolean retry){
        this.retry= retry;
        return this;
    }

    public OkHttpUtil retry(Integer maxRetry){
        this.retry = true;
        this.maxRetry = maxRetry;
        return this;
    }

    public OkHttpUtil retry(Integer maxRetry, Integer[] interval){
        this.retry = true;
        this.maxRetry = maxRetry;
        if( Objects.nonNull(interval) && interval.length > 0 ){
            this.interval = interval;
        }
        return this;
    }

    public OkHttpUtil retry(RetryHandler retryHandler){
        this.retry = true;
        this.retryHandler = retryHandler;
        return this;
    }

    public OkHttpUtil retry(Integer maxRetry, RetryHandler retryHandler){
        this.retry = true;
        this.maxRetry = maxRetry;
        this.retryHandler = retryHandler;
        return this;
    }

    public OkHttpUtil retry(Integer maxRetry, Integer[] interval, RetryHandler retryHandler){
        this.retry = true;
        this.maxRetry = maxRetry;
        this.retryHandler = retryHandler;
        if( Objects.nonNull(interval) && interval.length > 0 ){
            this.interval = interval;
        }
        return this;
    }

    // ---------------------------------------------重试次数设置----------------------------------------------- //

    public OkHttpUtil print(boolean print){
        this.print = print;
        return this;
    }

    /**是否对请求参数按字母排序**/
    public OkHttpUtil sort(boolean sort){
        this.sort = sort;
        return this;
    }

    /**是否对请求参数按字母排序**/
    public OkHttpUtil encode(boolean encode){
        this.encode = encode;
        return this;
    }

    public OkHttpUtil ping(boolean ping){
        this.ping = ping;
        return this;
    }

    public OkHttpUtil url(String url){
        this.url = url;
        this.httpUrl = HttpUrl.parse(url);
        return this;
    }

    public OkHttpUtil cert(String cert,String password){
        this.cert = cert;
        this.password = password;
        return this;
    }

    public OkHttpUtil add(String key, @NotNull Object value){
        this.urlParams.put(key,value);
        return this;
    }

    public OkHttpUtil addAll(Map<String, Object> urlParams){

        if( CollectionUtils.isEmpty(urlParams) ){ return this; }

        this.urlParams.putAll(urlParams);
        return this;
    }

    public OkHttpUtil addBody(String key, @NotNull Object value){
        this.bodyParams.put(key,value);
        return this;
    }

    public OkHttpUtil setBody(@NotNull Object value){
        this.bodyParam = value;
        return this;
    }

    public OkHttpUtil addBody(@NotNull Object value){
        this.listBodyParam.add(value);
        return this;
    }

    public OkHttpUtil addAllBodys(List<Object> bodyParams){
        if( CollectionUtils.isEmpty(bodyParams) ){ return this; }
        this.listBodyParam.addAll(bodyParams);
        return this;
    }

    public OkHttpUtil addAllBodys(Map<String, Object> bodyParams){

        if( CollectionUtils.isEmpty(bodyParams) ){ return this; }

        this.bodyParams.putAll(bodyParams);
        return this;
    }

    public OkHttpUtil addHeader(String key, @NotNull Object value){
        this.headerParams.put(key,value);
        return this;
    }

    public OkHttpUtil addAllHeaders(Map<String, Object> headerParams){

        if( CollectionUtils.isEmpty(headerParams) ){ return this; }

        this.headerParams.putAll(headerParams);
        return this;
    }

    public OkHttpUtil simple(String url, Map<String, Object> urlParams){
        this.url = url;
        this.urlParams = urlParams;
        return this;
    }

    public OkHttpUtil simple(String url, Map<String, Object> urlParams, Map<String, Object> headerParams){
        this.url = url;
        this.urlParams = urlParams;
        this.headerParams.putAll(headerParams);
        return this;
    }

    public OkHttpUtil simple(String url, Map<String, Object> urlParams, Map<String, Object> bodyParams, Map<String, Object> headerParams){
        this.url = url;
        this.urlParams.putAll(urlParams);
        this.bodyParams.putAll(bodyParams);
        this.headerParams.putAll(headerParams);
        return this;
    }

    public OkHttpUtil signature(SignInfo signInfo, SignHandler signHandler){
        this.signInfo = signInfo;
        this.signHandler = signHandler;
        return this;
    }

    /**get请求**/
    public String get() {
        return execute(RequestMethod.GET, REQUEST_URL);
    }

    /**带参数的请求,通过form表单传递参数**/
    public String put() {
        return execute(RequestMethod.PUT,REQUEST_FORM);
    }

    /**json请求**/
    public String putJson() {
        return execute(RequestMethod.PUT,REQUEST_JSON);
    }

    /**xml请求**/
    public String putXml() {
        return execute(RequestMethod.PUT,REQUEST_XML);
    }

    /**带参数的请求,通过form表单传递参数**/
    public String delete() {
        return execute(RequestMethod.DELETE,REQUEST_FORM);
    }

    /**json请求**/
    public String deleteJson() { return execute(RequestMethod.DELETE,REQUEST_JSON); }

    /**json请求**/
    public String deleteXml() { return execute(RequestMethod.DELETE,REQUEST_XML); }

    /**带参数的请求,通过form表单传递参数**/
    public String post() {
        return execute(RequestMethod.POST,REQUEST_FORM);
    }

    /**json请求**/
    public String postJson() {
        return execute(RequestMethod.POST,REQUEST_JSON);
    }
    /**xml请求**/
    public String postXml() {
        return execute(RequestMethod.POST,REQUEST_XML);
    }

    /**文件下载**/
    public byte[] download() {
        return execute(RequestMethod.GET,REQUEST_URL,new ByteResponseHandler());
    }

    /**文件上传**/
    public String upload(){
        return execute(RequestMethod.POST,REQUEST_FILE,new DefaultResponseHandler());
    }

    /**签名**/
    private void signature(RequestMethod method) {
        if( null == this.signHandler ){
            return;
        }
        RequestInfo requestInfo = RequestInfo.builder()
                .method(method)
                .httpUrl(httpUrl)
                .urlParams(urlParams)
                .bodyParams(bodyParams)
                .headerParams(headerParams).build();
        this.signHandler.sign(signInfo,requestInfo);
    }

    /**核心请求流程**/
    public String execute(RequestMethod method, String requestType){
        if( this.ping ){
            return String.valueOf(execute(method,requestType,new PingResponseHandler()));
        }
        return execute(method,requestType,new DefaultResponseHandler());
    }

    /**核心请求流程**/
    public <T> T execute(RequestMethod method, String requestType, ResponseHandler<T> handler){
        if( StringUtils.isBlank(this.url) ){
            throw new RuntimeException("URL为空");
        }
        // 签名
        signature(method);
        // 创建请求对象
        Request.Builder requestBuilder = new Request.Builder();
        // 追加请求头
        handleHeaders(this.headerParams, requestBuilder);
        // 追加参数到URL
        handleUrlParams(this.urlParams);
        // 追加参数到body
        if( REQUEST_FORM.equals(requestType) ){
            handleBodyParams(requestBuilder, method);
        }else if(REQUEST_JSON.equals(requestType)){
            handleJsonParams(requestBuilder, method);
        }else if(REQUEST_XML.equals(requestType)){
            handleXmlParams(requestBuilder,method);
        }else if(REQUEST_FILE.equals(requestType)){
            handleFileParams(requestBuilder,method);
        }
        // 设置url地址
        requestBuilder.tag(REQUEST_TAG).url(this.url);
        // 创建对象,并执行请求
        return execute(requestBuilder,handler);
    }

    /**执行请求**/
    private <T> T execute(Request.Builder builder, ResponseHandler<T> responseHandler) {
        OkHttpClient client = build();
        Request request = builder.build();

        //搜集请求日志
        if(print){ gatherLogInfo(); }

        try(Response response = client.newCall(request).execute()){
            return responseHandler.handle(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**搜集请求日志**/
    private void gatherLogInfo() {
        LogInfo logInfo = getLogInfo();
        logInfo.setBegin("--------------------start--------------------");

        if( !CollectionUtils.isEmpty(this.headerParams) ){
            logInfo.setHeaderInfo(com.alibaba.fastjson.JSON.toJSONString(this.headerParams));
        }

        if( !CollectionUtils.isEmpty(this.urlParams) ){
            logInfo.setUrlParams(com.alibaba.fastjson.JSON.toJSONString(this.urlParams));
        }

        if( !CollectionUtils.isEmpty(this.bodyParams) ){
            for (Map.Entry<String,Object> entry : this.bodyParams.entrySet()) {
                Object value = entry.getValue();
                if( value instanceof byte[] ){
                    this.bodyParams.put(entry.getKey(),"byte数组长度:"+((byte[])value).length);
                }else if(value instanceof File){
                    File file = (File)value;
                    this.bodyParams.put(entry.getKey(),"文件名:"+file.getName()+"文件大小:"+file.length());
                }
            }
            logInfo.setBodyParams(com.alibaba.fastjson.JSON.toJSONString(this.bodyParams));
        }
    }

    /**请求头**/
    private void handleHeaders(Map<String, ?> headerParams, Request.Builder requestBuilder) {
        if( CollectionUtils.isEmpty(headerParams) ){
            return;
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        for (Map.Entry<String, ?> entry : headerParams.entrySet()) {
            if( !Objects.isNull(entry.getValue()) ){
                headerBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        requestBuilder.headers(headerBuilder.build());
    }

    /**url请求参数**/
    private void handleUrlParams(Map<String, Object> urlParams) {
        if( CollectionUtils.isEmpty(urlParams) ){
            return;
        }
        String params;
        if( sort ){
            params = OkHttpUtil.sortParams(this.urlParams,encode);
        }else{
            params = OkHttpUtil.jointParams(this.urlParams,encode);
        }
        if( StringUtils.isNotBlank(params) ){
            this.url = this.url + "?" + params;
        }
    }

    /**form请求参数**/
    private void handleBodyParams(Request.Builder requestBuilder, RequestMethod method) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, ?> entry : this.bodyParams.entrySet()) {
            if( !Objects.isNull(entry.getValue()) ) {
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        handlerRequestMethod(requestBuilder, method, formBuilder.build());
    }

    /**json请求参数**/
    private void handleJsonParams(Request.Builder requestBuilder, RequestMethod method) {
        RequestBody body;
        if(null != this.bodyParam){
            if(this.bodyParam instanceof String){
                body = RequestBody.create(JSON, (String) this.bodyParam);
            }else{
                body = RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(this.bodyParam));
            }
            handlerRequestMethod(requestBuilder, method, body);
        }else
        if(!CollectionUtils.isEmpty(this.listBodyParam)){
            body = RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(listBodyParam));
            handlerRequestMethod(requestBuilder, method, body);
        }else
        if(!CollectionUtils.isEmpty(this.bodyParams)){
            body = RequestBody.create(JSON, com.alibaba.fastjson.JSON.toJSONString(bodyParams));
            handlerRequestMethod(requestBuilder, method, body);
        }
    }

    /**xml请求参数**/
    private void handleXmlParams(Request.Builder requestBuilder, RequestMethod method) {
        RequestBody body = RequestBody.create(XML, toXml(this.bodyParams));
        handlerRequestMethod(requestBuilder, method, body);
    }

    private String toXml(Map<?, ?> params) {
        StringBuilder buf = new StringBuilder();
        buf.append("<xml>");
        for (Map.Entry<?, ?> entry : params.entrySet()) {
            buf.append("<").append(entry.getKey()).append(">");
            buf.append("<![CDATA[").append(entry.getValue()).append("]]>");
            buf.append("</").append(entry.getKey()).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }

    /**文件上传参数处理**/
    private void handleFileParams(Request.Builder requestBuilder, RequestMethod method) {
        MultipartBody.Builder formBuilder = new MultipartBody.Builder();
        formBuilder.setType(MultipartBody.FORM);
        for (Map.Entry<String, ?> entry : this.bodyParams.entrySet()) {
            if( Objects.isNull(entry.getValue()) ) {
                continue;
            }
            Object object = entry.getValue();
            if( object instanceof File ){
                File file = (File) entry.getValue();
                formBuilder.addFormDataPart(entry.getKey(), file.getName(),  RequestBody.create(DATA,file));
            }else if( object instanceof byte[]){
                // 没有文件名,就固定一个文件名
                byte[] data = (byte[]) entry.getValue();
                formBuilder.addFormDataPart(entry.getKey(), "file",RequestBody.create(DATA,data));
            }else{
                formBuilder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        handlerRequestMethod(requestBuilder, method, formBuilder.build());
    }

    private void handlerRequestMethod(Request.Builder requestBuilder, RequestMethod method, RequestBody build) {
        if (RequestMethod.POST.equals(method)) {
            requestBuilder.post(build);
        }
        if (RequestMethod.PUT.equals(method)) {
            requestBuilder.put(build);
        }
        if (RequestMethod.DELETE.equals(method)) {
            requestBuilder.delete(build);
        }
    }

    public static String jointParams(Map<String, Object> params) {
        return jointParams(params,false);
    }

    /**拼接参数**/
    public static String jointParams(Map<String, Object> params,boolean encode) {
        StringBuilder sb = new StringBuilder(1024);
        // factory signature:
        Map<String, Object> map = Collections.synchronizedMap(params);
        return loopParams(encode, sb, map.entrySet());
    }

    /**拼接参数**/
    public static String sortParams(Map<String, Object> params) {
        return sortParams(params,false);
    }

    /**拼接参数**/
    public static String sortParams(Map<String, Object> params,boolean encode) {
        StringBuilder sb = new StringBuilder(1024);
        // factory signature:
        SortedMap<String, Object> map = Collections.synchronizedSortedMap(new TreeMap<>(params));
        return loopParams(encode, sb, map.entrySet());
    }

    /**循环map参数**/
    private static String loopParams(boolean encode, StringBuilder sb, Set<Map.Entry<String, Object>> entries) {
        for (Map.Entry<String, Object> entry : entries) {
            if (Objects.nonNull(entry.getValue())) {
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                if (encode) {
                    value = urlEncode(value);
                }
                sb.append(key).append('=').append(value).append('&');
            }
        }
        // remove last '&':
        if (StringUtils.isNotBlank(sb)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static LogInfo getLogInfo() {
        LogInfo logInfo = httpLogInfo.get();
        if( Objects.isNull(logInfo) ){
            logInfo = new LogInfo();
            httpLogInfo.set(logInfo);
        }
        return logInfo;
    }

    /**删除日志变量**/
    public static void removeLogInfo() {
        httpLogInfo.remove();
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

}
