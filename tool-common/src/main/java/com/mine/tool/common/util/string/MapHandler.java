package com.mine.tool.common.util.string;

import java.util.HashMap;

/**
 * 功能 :
 *
 */
public class MapHandler extends HashMap<String,Object> {

    private MapHandler() {
    }

    public static MapHandler build(){
        return new MapHandler();
    }

    public MapHandler add(String key,Object value){
        this.put(key,value);
        return this;
    }

}
