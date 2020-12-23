package com.mine.tool.http.handler.response;

import com.alibaba.fastjson.JSON;
import com.mine.tool.http.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :结果解析-字节处理
 */
@Slf4j
public class ByteResponseHandler extends ResponseAdapter<byte[]> {

    @Override
    public byte[] handle(Response response) throws IOException {
        ResponseBody body = response.body();

        if( Objects.isNull(body) ){return new byte[0]; }

        String contentType = response.header("Content-Type");

        if (StringUtils.isEmpty(contentType)) { return new byte[]{0}; }

        // 下载逻辑里边,只要出现json格式的数据,一定是异常信息
        if (contentType.contains(OkHttpUtil.JSON.subtype())) {
            byte[] result = body.bytes();
            Map object = JSON.parseObject(new String(result), Map.class);
            if( result.length > 0 ){
                Object message = object.get("message");
                throw new RuntimeException("业务错误");
            }
            return result;
        }
        return body.bytes();
    }

}
