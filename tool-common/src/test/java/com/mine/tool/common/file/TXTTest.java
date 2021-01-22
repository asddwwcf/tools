package com.mine.tool.common.file;

import com.mine.tool.common.arithmetic.Base64;
import com.mine.tool.common.exception.ExceptionUtils;
import com.mine.tool.common.util.LogUtils;
import com.mine.tool.common.util.file.FileUtils;
import com.mine.tool.common.util.file.core.TXT;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 功能 :
 */
@Slf4j
public class TXTTest {

    private static String basePath = System.getProperty("user.home")+"/Desktop/电话号码/";

    @Test
    public void paper() {
        TXT.create().path(basePath+"finish-aug.txt").lineIterator((total,index, line) -> {
            LogUtils.info(log,line);
            if( line.endsWith(".0") ){ line = line.replace(".0",""); }
            LogUtils.info(log,line);
            return true;
        });
    }

    @Test
    public void fileTypeTest() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.home") + "/Desktop/234.xls");
        } catch (FileNotFoundException e) {
            ExceptionUtils.printStackTrace(e);
            return;
        }
//        FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/Desktop/123.xlsx");
//        FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/Desktop/456.docx");
//        FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/Desktop/456.doc");
        byte[] bytes = FileUtils.transfer(fis);
        String item = Base64.byte2Hex(bytes,0,50);
        System.out.println(FileUtils.fetchType(bytes));
        System.out.println(item);
        // D0CF11E0A1B11AE1000000000000000000000000000000003E000300FEFF0900060000000000000000000000010000000100
        // D0CF11E0A1B11AE1000000000000000000000000000000003E000300FEFF0900060000000000000000000000020000008B00
        // D0CF11E0A1B11AE1000000000000000000000000000000003E000300FEFF0900060000000000000000000000010000002700
        // 504B0304140008080800C0AC814F000000000000
        // 504B030414000600080000002100DFA4D26C5A01
    }
}
