package com.mine.tool.common.enums;

import lombok.Getter;

/**
 * 功能 :
 * 参数分组:0温度,1水位,2继电器开关,3故障代码,4设备状态
 */
public enum KeyGroup {
    TEMPERATURE(0,"温度"),
    WATER_LEVEL(1,"水位"),
    RELAY_SWITCH(2,"继电器开关"),
    FAULT_CODE(3,"故障代码"),
    EQUIPMENT_STATE(4,"设备状态"),
    ELECTRICITY(5,"电流"),
    APERTURE(6,"开度"),

    ;
    @Getter
    private Integer code;
    @Getter
    private String name;

    KeyGroup(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static KeyGroup getByCode(Integer code){
        for (KeyGroup keyGroup : KeyGroup.values()) {
            if( keyGroup.getCode().equals(code) ){
                return keyGroup;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code){
        for (KeyGroup keyGroup : KeyGroup.values()) {
            if( keyGroup.getCode().equals(code) ){
                return keyGroup.getName();
            }
        }
        return null;
    }

    public static Integer getCodeByName(String name){
        for (KeyGroup keyGroup : KeyGroup.values()) {
            if( keyGroup.getName().equals(name) ){
                return keyGroup.getCode();
            }
        }
        return null;
    }
}
