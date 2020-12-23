package com.mine.tool.http.interceptor;

import com.mine.tool.http.handler.LogInfo;
import com.mine.tool.http.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class LogInterceptor implements Interceptor {

    private static Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        LogInfo logInfo = OkHttpUtil.getLogInfo();
        StringBuilder requester = new StringBuilder()
                .append("{")
                .append("\"method\":\"").append(request.method()).append("\",")
                .append("\"url\":\"").append(request.url().toString()).append("\"")
                .append("}");
        logInfo.setRequestInfo(requester.toString());
        Response response = chain.proceed(chain.request());
        long duration = System.currentTimeMillis() - startTime;
        logInfo.setDuration(duration);
        MediaType mediaType = response.body().contentType();
        byte[] content = response.body().bytes();
        if(HttpStatus.OK.value() != response.code()){
            gatherErrorInfo(logInfo, new String(content), response);
        }else{
            gatherResponseInfo(logInfo, mediaType, content);
        }

        logInfo.setEnd("-----------------End:"+duration+"毫秒-----------------\n");

        //打印日志逻辑
        print(logInfo);

        OkHttpUtil.removeLogInfo();

        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    private void print(LogInfo logInfo) {
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isNotBlank(logInfo.getHeaderInfo()) ){
            builder.append("请求头:").append(logInfo.getHeaderInfo());
        }
        if(StringUtils.isNotBlank(logInfo.getRequestInfo()) ){
            builder.append("请求信息:").append(logInfo.getRequestInfo());
        }
        if(StringUtils.isNotBlank(logInfo.getBodyParams()) ){
            builder.append("body参数:").append(logInfo.getBodyParams());
        }
        if(StringUtils.isNotBlank(logInfo.getResponseInfo()) ){
            builder.append("返回结果:").append(logInfo.getResponseInfo());
        }
        if(null != logInfo.getDuration()){
            builder.append("耗时:").append(logInfo.getDuration()).append("ms");
        }
        log.info("{}",builder.toString());
    }

    /**校验http验证码**/
    private void gatherErrorInfo(LogInfo logInfo, String content, Response response) {
        if(StringUtils.isNotBlank(content) && content.length() > 1024){
            content = content.substring(0,1024);
        }
        String message = "HTTP状态码:" + response.code() + ",错误信息:" + content;
        logInfo.setResponseInfo(message);
    }

    private void gatherResponseInfo(LogInfo logInfo, MediaType mediaType, byte[] content) {
        String mediaTypeString = mediaType.toString();
        if( !mediaTypeString.contains("application/json")
                && !mediaTypeString.contains("text/xml")
                && !mediaTypeString.contains("text/html")
                && !mediaTypeString.contains("text/plain")
                && !mediaTypeString.contains("text/asp")){
            logInfo.setResponseInfo("请求类型:"+mediaTypeString+",请求大小:"+content.length);
        }else{
            String result = unicodeToString(new String(content));
            logInfo.setResponseInfo(result);
        }
    }

    private String unicodeToString(String unicode){
        Matcher matcher = pattern.matcher(unicode);
        char ch;
        while (matcher.find()) {
            String group = matcher.group(2);
            ch = (char) Integer.parseInt(group, 16);
            String group1 = matcher.group(1);
            unicode = unicode.replace(group1, ch + "");
        }
        return unicode;
    }

}