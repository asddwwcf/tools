package com.mine.tool.common.util.file;

import com.mine.tool.common.arithmetic.Base64;
import com.mine.tool.common.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 功能 :
 * 1.字节流-转byte数组
 */
@Slf4j
public class FileUtils {

    /**流->数组**/
    public static byte[] transfer(InputStream in){
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            int length;
            byte[] buffer = new byte[4096];
            while ((length = in.read(buffer))!= -1){
                baos.write(buffer,0,length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("{}",e.getMessage(),e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return new byte[0];
    }

    /**根据流获取文件类型**/
    public static String fetchType(byte[] bytes){
        String header = Base64.byte2Hex(bytes,0,20);
        FileTypeEnum byCode = FileTypeEnum.getByCode(header);
        if(Objects.nonNull(byCode)){
            return byCode.getType();
        }
        return null;
    }

    /**导出到指定的流文件中:需要先执行stream()方法,初始化outputStream**/
    public static void toStream(byte[] bytes, OutputStream out){
        try {
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
