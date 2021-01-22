package com.mine.tool.common.util.file;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 功能 :
 */
public enum FileTypeEnum {
    //
    jpg("FFD8FF", "jpg"),
    png("89504E47", "png"),
    gif("47494638", "gif"),
    tif("49492A00", "tif"),
    bmp("424D", "bmp"),
    // CAD
    dwg("41433130", "dwg"),
    psd("38425053", "psd"),
    // 日记本
    rtf("7B5C727466", "rtf"),
    xml("3C3F786D6C", "xml"),
    html("68746D6C3E", "html"),
    // 邮件
    eml("44656C69766572792D646174653A", "eml"),
    // word or xls
    doc_xls("D0CF11E0A1B11AE1", "doc|xls"),
    docx_xlsx("504B030414", "docx|xlsx"),
    mdb("5374616E64617264204A", "mdb"),
    ps("252150532D41646F6265", "ps"),
    pdf("255044462D312E", "pdf"),
    rar("52617221", "rar"),
    wav("57415645", "wav"),
    avi("41564920", "avi"),
    rm("2E524D46", "rm"),
    mpg("000001BA", "mpg"),
    mpg2("000001B3", "mpg"),
    mov("6D6F6F76", "mov"),
    asf("3026B2758E66CF11", "asf"),
    mid("4D546864", "mid"),
    gz("1F8B08", "gz"),
    exe("4D5A9000", "exe/dll"),
    txt("75736167", "txt")
    ;

    @Getter
    private String code;
    @Getter
    private String type;

    FileTypeEnum(String code,String type) {
        this.code = code;
        this.type = type;
    }

    /**根据字节码,获取文件类型**/
    public static FileTypeEnum getByCode(String code){
        if(StringUtils.isBlank(code)){ return null; }
        for(FileTypeEnum type : values()){
            if(code.startsWith(type.getCode())){
                return type;
            }
        }
        return null;
    }

}
