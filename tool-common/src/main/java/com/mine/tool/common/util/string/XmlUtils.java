package com.mine.tool.common.util.string;

import com.mine.tool.common.exception.ExceptionUtils;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

/**
 * 功能 :
 * 1.把xml字符串,转成map集合
 * 2.把map集合转成xml字符串
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XmlUtils {

    /**
     * xml转成map集合
     **/
    public static Map<String, Object> toMap(String xml, String charset) {
        try {
            SAXReader reader = new SAXReader(false);
            InputSource source = new InputSource(new ByteArrayInputStream(xml.getBytes()));
            source.setEncoding(charset);
            Document doc = reader.read(source);
            return XmlUtils.toMap(doc.getRootElement());
        } catch (Exception e) {
            ExceptionUtils.printErrorInfo(e);
        }
        return null;
    }

    /**
     * 集合转xml,字段无顺序
     **/
    public static String toXml(Map<?, ?> params) {
        StringBuilder buf = new StringBuilder();
        buf.append("<xml>");
        for (Map.Entry<?, ?> entry : params.entrySet()) {
            buf.append("<").append(entry.getKey()).append(">");
            buf.append("<![CDATA[").append(entry.getValue()).append("]]>");
            buf.append("</").append(entry.getKey()).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }


    /**
     * xml转成map集合
     **/
    private static Map<String, Object> toMap(Element element) {
        Map<String, Object> rest = Maps.newConcurrentMap();
        List<Element> els = element.elements();
        for (Element el : els) {
            rest.put(el.getName(), el.getTextTrim());
        }
        return rest;
    }

}
