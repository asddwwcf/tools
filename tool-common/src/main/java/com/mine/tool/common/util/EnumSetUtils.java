package com.mine.tool.common.util;

import java.util.EnumSet;

/**
 * Title: EnumSetUtils
 * Description:
 * EnumSet内部实现是bitwise
 * 如果要把EnumSet直接存到数据库，看看EnumSet的源码，就知道其实它是用一个long elements来represent这个set的，
 * 那么我们需要写个工具类能在long和EnumSet之间做转化，这样就可以存取数据库了
 */
public class EnumSetUtils {

    public enum Week { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY , SATURDAY, SUNDAY }

    public static <T extends Enum<T>> EnumSet<T> parseEnumSet(Class<T> originalEnumType, long elements){
        EnumSet<T> resultEnumSet = EnumSet.allOf(originalEnumType);
        //resultEnumSet.removeIf(element -> (elements & (1L << element.ordinal())) == 0);
        for (T element : resultEnumSet){
            if ((elements & (1L << element.ordinal())) == 0){
                resultEnumSet.remove(element);
            }
        }
        return resultEnumSet;
    }

    public static <T extends Enum<T>> long getElementsValue(EnumSet<T> originalEnumSet){
        if (null == originalEnumSet){
            return 0;
        }
        long elements = 0;
        for (T element : originalEnumSet){
            elements |= (1L << element.ordinal());
        }
        return elements;
    }

    public static void main(String[] args) {
        EnumSet<Week> days = EnumSet.of(Week.MONDAY, Week.THURSDAY);
        long value = getElementsValue(days);
        System.out.println(value);
        EnumSet<Week> enumSet = parseEnumSet(Week.class, value);
        System.out.println(enumSet);
    }

}
