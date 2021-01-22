package com.mine.tool.http;

import com.alibaba.fastjson.JSON;
import com.mine.tool.http.handler.Timeout;
import com.mine.tool.http.handler.response.RetryResponse;
import com.mine.tool.http.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 功能 :
 */
@Slf4j
public class OkHttpUtilTest {

    @Test
    public void get() {
        String result = OkHttpUtil.create().add("a","12123").url("https://www.baidu.com/").get();
        System.out.println(result);
    }

    @Test
    public void download() {
        byte[] result = OkHttpUtil.create().url("https://www.baidu.com/img/xinshouye_77c426fce3f7fd448db185a7975efae5.png")
                .download();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void upload2() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("name", "测试文件上传");

        File file = new File("/Users/bruce/Desktop/test.xls");
        if (file.exists()) {
            String TYPE = "application/octet-stream";
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE), file);

            RequestBody requestBody = builder
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), fileBody)
                    .build();

            Request request = new Request.Builder()
                    .url("http://127.0.0.1:8086/tool/upload")
                    .post(requestBody)
                    .build();
            client.newCall(request).execute();
        }
    }

    /**
     * 测试文件上传
     **/
    @Test
    public void upload() throws IOException {
        File file = new File("/Users/bruce/Desktop/test.xls");
        if (!file.exists()) {
            return;
        }
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int length = -1;
        byte[] buf = new byte[1024];
        while ((length = in.read(buf)) > -1) {
            bos.write(buf, 0, length);
        }
        String result = OkHttpUtil.create().print(true)
                .url("http://127.0.0.1:8086/tool/upload")
                .timeout(Timeout.builder()
                        .connectTimeout(60)
                        .writeTimeout(60)
                        .readTimeout(60).build())
                .add("name", "测试文件上传")
                // 方式一: 文件
                .addBody("file", new File("/Users/bruce/Desktop/test.xls"))
                // 方式二: 字节数组
                .addBody("upload", bos.toByteArray())
                .upload();
        System.out.println(result);
    }

    /**
     * 测试重试机制
     **/
    @Test
    public void retry() {
        OkHttpUtil.create().retry(6, new Integer[]{500, 1000}, response -> {
            String result = new String(response);
            return RetryResponse.builder()
                    .need(result.contains("by zero"))
                    .reason("测试原因")
                    .build();
        })
                .url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .post();

        OkHttpUtil.create().retry(response -> {
            String result = new String(response);
            return RetryResponse.builder()
                    .need(result.contains("by zero"))
                    .reason("测试原因")
                    .build();
        }).url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdms/")
                .post();
    }
}
