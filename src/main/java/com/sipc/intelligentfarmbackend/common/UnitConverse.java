package com.sipc.intelligentfarmbackend.common;

import java.util.HashMap;
import java.util.Map;

public class UnitConverse {
    public static final Map<Integer,String> UNIT_MAP = new HashMap<>();

    static {
        UNIT_MAP.put(1,"mg/g");
        UNIT_MAP.put(2,"%");
        UNIT_MAP.put(3,"℃");
        UNIT_MAP.put(4,"");
        UNIT_MAP.put(5,"mg/g");
        UNIT_MAP.put(6,"tx");
    }
}
