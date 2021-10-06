package com.alex.framework.data;

public enum Warranty {
    WITHOUT_ADDITIONAL("Гарантия от производителя"),
    WITH_ADDITIONAL_12_MOUNTS("Доп. гарантия+ 12 мес."),
    WITH_ADDITIONAL_24_MOUNTS("Доп. гарантия+ 24 мес.");

    private final String value;

    private Warranty(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
