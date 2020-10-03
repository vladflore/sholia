package tech.vladflore.sholia.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CurrencyEnum {
    EURO("euro"),
    DOLLAR("dollar"),
    RON("ron");

    private final String value;

    CurrencyEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static CurrencyEnum fromValue(String value) {
        for (CurrencyEnum c : CurrencyEnum.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
