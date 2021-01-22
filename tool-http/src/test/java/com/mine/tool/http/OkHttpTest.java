package com.mine.tool.http;

import com.mine.tool.http.util.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

@Slf4j
public class OkHttpTest {

    @Test
    public void get() throws IOException {
        String str = OkHttp.builder()
                .url("http://www.baidu.com")
                .get()
                .asString();
        System.out.println(str);
    }

    @Test
    public void postJson() throws IOException {
        String str = OkHttp.builder()
                .url("http://www.baidu.com")
                .body("123")
                .post()
                .asString();
        System.out.println(str);
    }

    @Test
    public void postForm() throws IOException {
        String str = OkHttp.builder()
                .url("http://www.baidu.com")
                .form("q", "腾讯")
                .file("asda", new File("pom.xml"))
                .form()
                .asString();
        System.out.println(str);
    }





}
