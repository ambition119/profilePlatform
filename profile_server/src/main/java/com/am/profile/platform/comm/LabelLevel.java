package com.am.profile.platform.comm;

/**
 * @author wpl
 */
public enum LabelLevel {
    Level_1("01"),
    Level_2("02"),
    Level_3("03"),
    Level_4("04"),
    Level_5("05")
    ;

    private String level;
    LabelLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static LabelLevel getLabelLevel(String level) {
        switch (level){
            case "02":
                return Level_2;
            case "03":
                return Level_3;
            case "04":
                return Level_4;
            case "05":
                return Level_5;
            default:
                return Level_1;
        }
    }
}
