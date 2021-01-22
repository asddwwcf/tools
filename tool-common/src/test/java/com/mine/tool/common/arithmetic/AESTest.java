package com.mine.tool.common.arithmetic;

import com.mine.tool.common.arithmetic.constant.AesType;
import org.junit.Test;

/**
 * 功能 :
 */
public class AESTest {

    @Test
    public void aes(){
        System.out.println(AES.encrypt(AesType.CBC,"123456","123456789"));
        System.out.println(AES.decrypt(AesType.CBC,AES.encrypt(AesType.CBC,"123456","123456789"),"123456789"));
        System.out.println(AES.encrypt(AesType.ECB,"123456","123456789"));
        System.out.println(AES.decrypt(AesType.ECB,AES.encrypt(AesType.ECB,"123456","123456789"),"123456789"));
        System.out.println(AES.encrypt(AesType.GCM,"123456","123456789"));
        System.out.println(AES.decrypt(AesType.GCM,AES.encrypt(AesType.GCM,"123456","123456789"),"123456789"));
    }
}
