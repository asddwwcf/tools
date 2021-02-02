package com.mine.tool.mail;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

/**
 * 功能 :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class TestMail{

    private List<Object> datas = Lists.newArrayList();

    @Before
    public void init(){
        for ( int i = 0; i< 101; i++ ) {
            Map<String,Object> data = Maps.newHashMap();
            data.put("name","测试测试测试"+i);
            data.put("params","https://blog.csdn.net/gaolu/article/details/52708177导入模板格式固定，不得删除标题行，不得对标题行做任何编辑"+i);
            data.put("paramType",i % 2);
            data.put("url","https://blog.csdn.net/gaolu/article/details/52708177"+i);
            data.put("requestType","POST"+i);
            data.put("expectCode","0"+i);
            data.put("result","展示的结果"+i);
            data.put("price",(3.45689 * i));
            datas.add(data);
        }
    }

    @Test
    public void sendSimpleMail() {
        mailAdapter.addTo("nichiai@163.com")
                .setSubject("倍全生活项目前端进度0325")
                .setSubject("京东地址处理记录")
                .setSubject("测试邮件")
                .appendToContent(false)
                .send();
    }

    @Test
    public void sendReportMail() {
        //要追加附件到邮件内容,如果不是excel文件,设置了本配置,则会报错.
        mailAdapter.addTo("nichiai@163.com")
                .setSubject("倍全生活项目前端进度0325")
                .addAttachment("倍全生活项目前端进度0325",System.getProperty("user.home")+"/Desktop/test5.xlsx")
                .appendToContent(true)
                .send();
    }

    @Test
    public void byteAttachmentsMail() {
        // 说明:导出的时候,不管data是List<Map>还是List<Class>,都能够用Class的方式导出
        byte[] bytes = Excels.Export()
                .config(Version.XLS)
                .addSheet(datas,Interface2.class,"前端进度","操作说明：\n" +
                        "1、导入模板格式固定，不得删除标题行，不得对标题行做任何编辑，不得插入、删除标题列，不得调整标题行和列的位置。\n" +
                        "2、导入模板内容仅包括：楼号、单元/楼层、房间号、房间面积、房东姓名、房东手机号、房东身份证号码，7项，不得任意添加其他导入项目，所有项均为必填项。\n" +
                        "3、【楼号】：支持中文、数字和英文，如果是社区，需要输入“11号楼”或“11栋”之类的内容，如果是写楼楼，可以输入“A座”之类的内容； 但无论输入什么内容，必须所有内容统一，否则为显示为2个楼号；\n" +
                        "4、【单元/楼层】【房间号】：仅支持数字和英文；   最终展示的结果为是“5#402”； 如果是社区，建议是“单元号#房间号”，如果是楼层，建议是“楼层#房间号”；\n" +
                        "4、【房间面职】：主要为了区分房间的户型，不需要精确输入房间面积；\n" +
                        "5、【房东姓名】：支持汉字、英文、数字；【房东手机号】：仅支持11位阿拉伯数字，数字前后、之间不得有空格或其他字符；   【房东身份证号】：身份证号码必须是正确的身份证号，错误的号码将无法上传；\n" +
                        "6、导入模板各项内容不符合编辑规则的，导入时，系统将自动跳过，并计为导入失败数据；")
                .toBytes();

        mailAdapter.addTo("nichiai@163.com")
                .setSubject("前端进度0325")
                .addByteAttachment("项目前端进度0325",bytes)
                .appendToContent(true)
                .send();
    }
}
