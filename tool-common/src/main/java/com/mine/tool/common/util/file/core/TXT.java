package com.mine.tool.common.util.file.core;

import com.mine.tool.common.exception.ExceptionUtils;
import com.mine.tool.common.util.file.filter.LineFilter;
import com.mine.tool.common.util.string.StringUtils;
import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * 功能 :
 * 文件工具类
 */
@Slf4j
@NoArgsConstructor(staticName = "create")
public class TXT {

    /**
     * The Unix line separator string.
     */
    public static final String LINE_SEPARATOR_UNIX = "\n";
    /**
     * The Windows line separator string.
     */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

    /**完整的文件路径**/
    private String fileName;
    /****/
    public TXT path(String fileName){
        this.fileName = fileName;
        return this;
    }

    /**按行读取文件内容**/
    public List<String> readLines(){
        if(StringUtils.isBlank(fileName)){
            return Lists.newArrayList();
        }

        try(FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader)
        ){
            String line;
            List<String> lines = Lists.newArrayList();
            while((line = bufferedReader.readLine())!=null){
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            ExceptionUtils.printStackTrace(e);
        }
        return Lists.newArrayList();
    }

    /**读取到byte数组中**/
    public byte[] readBytes(){
        if(StringUtils.isBlank(fileName)){
            return new byte[0];
        }
        try(FileInputStream fis = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            int length;
            byte[] buf = new byte[4096];
            while((length = fis.read(buf)) != -1){
                baos.write(buf,0,length);
            }
            return baos.toByteArray();
        } catch(Exception e){
            ExceptionUtils.printStackTrace(e);
        }
        return new byte[0];
    }

    /**迭代器读取方式**/
    public void lineIterator(LineFilter filter){
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(fileName));
        } catch (IOException e) {
            ExceptionUtils.printStackTrace(e);
        }
        if(Objects.isNull(lineIterator) ){
            return;
        }
        Long index = 0L;
        Long count = 0L;
        while (lineIterator.hasNext()) {
            if(!filter.doFilter(count,index,lineIterator.next())) {break;}
            index ++;
        }
        count = index + 1;
        filter.doFilter(count,index,null);
    }

    /**按行写入内容:默认使用windows换行符**/
    public void writeLines(List<String> lines){
        this.writeLines(lines,LINE_SEPARATOR_WINDOWS);
    }

    /**按行写入内容**/
    public void writeLines(List<String> lines,String lineEnding){
        try {
            FileUtils.writeLines(new File(fileName),lines,lineEnding,true);
        } catch (IOException e) {
            ExceptionUtils.printStackTrace(e);
        }
    }

    /**按行读取文件内容**/
    public String readLine(Integer rowNum){
        if(StringUtils.isBlank(fileName)){
            return null;
        }

        try(FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);){
            String line;
            int lineNum = 0;
            while((line = bufferedReader.readLine())!=null){
                lineNum ++;
                if( lineNum == rowNum){
                    return line;
                }
            }
        } catch (IOException e) {
            ExceptionUtils.printStackTrace(e);
        }
        return null;
    }
}
