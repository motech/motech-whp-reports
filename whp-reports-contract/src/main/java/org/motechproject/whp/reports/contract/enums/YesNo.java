package org.motechproject.whp.reports.contract.enums;

public enum YesNo {
    Yes("Yes", "Y"), No("No", "N");

    private String text;

    private String code;

    YesNo(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public static YesNo value(Boolean bool) {
        return bool == null ?  null : (bool ? Yes : No);
    }

    public String getText() {
        return text;
    }

    public String code(){
        return code;
    }
}
