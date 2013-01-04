package org.motechproject.whp.reports.contract.enums;

public enum YesNo {
    Yes("Yes"), No("No");

    private String text;

    YesNo(String text) {
        this.text = text;
    }

    public static YesNo value(Boolean bool) {
        return bool == null ?  null : (bool ? Yes : No);
    }

    public String getText() {
        return text;
    }
}
