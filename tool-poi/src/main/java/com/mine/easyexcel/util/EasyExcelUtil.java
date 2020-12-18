package com.mine.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * EasyExcel工具类
 * @author
 */
public class EasyExcelUtil {

    /**
     * 同步读取Excel数据(适合小数据量的情况)
     * @param inputStream 输入流
     * @param clazz 模板类类型
     * @param <T> 模板类
     * @return 数据
     */
    public static <T> List<T> readSync(InputStream inputStream, Class<T> clazz) {
        return EasyExcel.read(inputStream).head(clazz).sheet().doReadSync();
    }

    /**
     * 同步读取Excel数据(适合小数据量的情况)
     * @param file 文件
     * @param clazz 模板类类型
     * @param <T> 模板类
     * @return 数据
     */
    public static <T> List<T> readSync(File file, Class<T> clazz) {
        return EasyExcel.read(file).head(clazz).sheet().doReadSync();
    }

    /**
     * 异步的读Excel数据(需自定义的监听器)
     * @param inputStream 输入流
     * @param clazz 模板类类型
     * @param analysisEventListener 解析事件监听器
     * @param <T> 模板类
     */
    public static <T> void read(InputStream inputStream, Class<T> clazz, AnalysisEventListener<T> analysisEventListener) {
        EasyExcel.read(inputStream, clazz, analysisEventListener).sheet().doRead();
    }

    /**
     * 异步的读Excel数据(需自定义的监听器)
     * @param file 文件
     * @param clazz 模板类类型
     * @param analysisEventListener 解析事件监听器
     * @param <T> 模板类
     */
    public static <T> void read(File file, Class<T> clazz, AnalysisEventListener<T> analysisEventListener) {
        EasyExcel.read(file, clazz, analysisEventListener).sheet().doRead();
    }

    /**
     * 写到文件
     * @param file 文件
     * @param clazz 模板类类型
     * @param data 数据
     * @param sheetName sheet名
     * @param <T> 模板类
     */
    public static <T> void write(File file, Class<T> clazz, List<T> data, String sheetName) {
        EasyExcel.write(file, clazz).sheet(sheetName).doWrite(data);
    }

    /**
     * 写到文件-多Sheet
     * @param file 文件
     * @param classes 模板类类型
     * @param data 数据
     * @param sheetNames sheet名
     */
    public static void write(File file, List<Class<?>> classes, List<List<?>> data, List<String> sheetNames) {
        ExcelWriter excelWriter = EasyExcel.write(file).build();
        for (int i = 0; i < classes.size(); i++) {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNames.get(i)).head(classes.get(i)).build();
            excelWriter.write(data.get(i), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 写到Response
     * @param response response对象
     * @param clazz 模板类类型
     * @param data 数据
     * @param sheetName sheet名
     * @param fileName 文件名
     * @param <T> 模板类
     * @throws IOException io异常
     */
    public static <T> void write(HttpServletResponse response, Class<T> clazz, List<T> data, String sheetName, String fileName) throws IOException {
        buildResponse(response, fileName);
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(data);
    }

    /**
     * 写到Response-多Sheet
     * @param response response对象
     * @param classes 模板类类型
     * @param data 数据
     * @param sheetNames sheet名
     * @param fileName 文件名
     * @throws IOException io异常
     */
    public static void write(HttpServletResponse response, List<Class> classes, List<List<?>> data, List<String> sheetNames, String fileName) throws IOException {
        buildResponse(response, fileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        for (int i = 0; i < classes.size(); i++) {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNames.get(i)).head(classes.get(i)).build();
            excelWriter.write(data.get(i), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 填充数据
     * @param response response对象
     * @param templateFileName 模板文件
     * @param fileName 导出文件
     * @param data 填充数据
     * @param <T> 模板类
     * @throws IOException io异常
     */
    public static <T> void fill(HttpServletResponse response, String templateFileName, String fileName, T data) throws IOException {
        buildResponse(response, fileName);
        InputStream inputStream = getResourcesFileInputStream(templateFileName);
        EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).sheet().doFill(data);
    }

    /**
     * 复杂数据填充(单个数据+list)
     * @param response response对象
     * @param templateFileName 模板文件
     * @param fileName 导出文件
     * @param list 列表数据
     * @param data 填充数据
     * @param <T> 模板类
     * @throws IOException io异常
     */
    public static <T> void complexFill(HttpServletResponse response, String templateFileName, String fileName, List<?> list, T data) throws IOException {
        buildResponse(response, fileName);
        InputStream inputStream = getResourcesFileInputStream(templateFileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 注意:如果模板list不是最后一行，下面还有数据需要填充，就必须设置 forceNewRow=true
        // 这样会把所有的数据都放到内存了，所以慎用
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(list, fillConfig, writeSheet);
        excelWriter.fill(data, writeSheet);
        excelWriter.finish();
    }

    private static void buildResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }

    private static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

}
