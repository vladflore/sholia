package tech.vladflore.sholia.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MeasurementEnum {
    G("g"),

    KG("kg"),

    L("l"),

    PC("pc");

    private String value;

    MeasurementEnum(String value) {
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
    public static MeasurementEnum fromValue(String value) {
        for (MeasurementEnum b : MeasurementEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
