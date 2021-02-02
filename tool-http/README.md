###工具包使用手册
#### 基本功能说明
请求参数说明:
```
添加header参数:
Https.create().addHeader(key,value)
添加get参数:
Https.create().add(key,value)
添加body参数:
Https.create().addBody(key,value)
```
支持的请求方式:
```
get
post/postjson/postxml
put/putjson/putxml
delete/deletejson/deletexml
download/upload
```
支持自定义结果解析:
```
继承ResponseAdapter接口,实现自己的结果处理
默认解析器包括:
DefaultResponseHandler(字符串处理)
ByteResponseHandler(字节处理)
```
get请求示例:
```
String result = Https.create().add("a","12123").url("https://www.baidu.com/").get();
```
下载文件示例:
```
byte[] result = Https.create().url("https://www.baidu.com/img/xinshouye_77c426fce3f7fd448db185a7975efae5.png").download();
```
日志打印开关(默认不打印日志):
```
String result = Https.create().print(true).url("https://www.baidu.com/").get();
```
请求超时设置(默认10秒超时):
三个参数分别表示connect,read,write的超时时间
```
String result = Https.create()
        .timeout(10,10,10)
        .url("https://www.baidu.com/").get();
```
是否对get请求中的参数编码(默认会对参数做urlencode编码):
```
String result = Https.create()
        .encode(false)
        .url("https://www.baidu.com/").get();
```
retry参数说明:
```
1.retry方法:
    第一个参数maxRetry(最大重试次数)
    第二个参数interval(每次请求,延迟的时间数组)
    第三个参数retryHandler(重试条件设置)
2.服务器访问超时自动重试设置(默认开启,重试3次):
3.服务器访问500以上错误,自动重试设置开启(单独开启):
    retry方法:
    第一个参数retry(是否重试)
4.其他的没有retryHandler参数的retry方法,调用,则自动开启服务器500重试机制.
```
请求重试示例:
```
Https.create().url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .retry(6, new Integer[]{500, 1000}, new RetryHandler() {
                    @Override
                    public RetryResponse needRetry(Object response) {
                        if (response instanceof String) {
                            String reason = (String) response;
                            return RetryResponse.builder()
                                    .need(reason.contains("by zero"))
                                    .reason("测试原因")
                                    .build();
                        }
                        return null;
                    }
                }).post();
```
请求参数签名实现:
```
signature(SignInfo signInfo, SignHandler signHandler)
signInfo(签名需要的信息)
    appKey:签名key值
    appSecret:签名value值
    phrase:生成秘钥所用的特定字符串,可以为空
signHandler(操作签名)
    signInfo(签名需要的信息)
    requestInfo(请求中的信息,用于生成签名源字符串)
        httpUrl:url信息对象
        method:请求方法(get/post/....)
        urlParams:url地址中的参数(get请求参数)
        bodyParams:请求body中的参数(post请求参数)
        headerParams:请求头中的参数(多半用于签名)
生成完签名之后,可以根据需要回写到header/url/body中
SignAdapter中有签名算法的实现(支持:HMAC_SHA_256等摘要签名算法)
```
get请求参数排序(默认不排序):
```
sort(true/false)
如果设置为true,则会自动将urlParams中的参数按字母排序,拼接到Url地址之后
```
补充说明:
```
1.如果是get方法,则addBody中的参数,无效.
2.如果是post方法,add和addBody中的参数都有效.
3.支持http请求和https请求,只需要根据url地址中http/https前缀判断即可,不用做额外设置.
4.证书目前没有对微信证书做测试,待完善.

```
使用样例:
文件下载:
```
byte[] result = Https.create()
        .url("http://c.hiphotos.baidu.com/zhidao/pic/item/a50f4bfbfbedab6471a3f9ccf636afc378311eec.jpg")
        .download();
```
文件上传-文件方式:
```
String result = Https.create().print(true)
                .url("http://127.0.0.1:8086/tool/upload")
                .timeout(Timeout.builder()
                        .connectTimeout(60)
                        .writeTimeout(60)
                        .readTimeout(60).build())
                .add("name","测试文件上传")
                // 方式一: 文件
                .addBody("file",new File("/Users/Desktop/test.xls"))
                .upload();
```
文件上传-字节数组方式:
```
FileInputStream in = new FileInputStream(new File(System.getProperty("user.home")+"/Desktop/test.xls"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int length = -1;
        byte[] buf = new byte[1024];
        while( (length = in.read(buf)) > -1 ){
            bos.write(buf,0,length);
        }
String result = Https.create().print(true)
                .url("http://127.0.0.1:8086/tool/upload")
                .timeout(Timeout.builder()
                        .connectTimeout(60)
                        .writeTimeout(60)
                        .readTimeout(60).build())
                .add("name","测试文件上传")
                // 方式二: 字节数组
                .addBody("upload",bos.toByteArray())
                .upload();
```
请求重试机制-重试次数,每次之间的间隔,重试条件设置
```
Https.create().url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .retry(6, new Integer[]{500, 1000}, new RetryHandler() {
                    @Override
                    public RetryResponse needRetry(byte[] response) {
                        String content = new String(response);
                        return RetryResponse.builder()
                                .need(content.contains("by zero"))
                                .reason("测试原因")
                                .build();
                    }
                }).post();
```
