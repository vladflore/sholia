package tech.vladflore.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Item
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-08-16T20:50:23.470994+02:00[Europe/Berlin]")

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private Long quantity;

    /**
     * Gets or Sets measurement
     */
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

    @JsonProperty("measurement")
    private MeasurementEnum measurement;

    /**
     * Gets or Sets language
     */
    public enum LanguageEnum {
        EN("en"),

        DE("de"),

        RO("ro");

        private String value;

        LanguageEnum(String value) {
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
        public static LanguageEnum fromValue(String value) {
            for (LanguageEnum b : LanguageEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("language")
    private LanguageEnum language;

    public Item id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @NotNull


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Get quantity
     *
     * @return quantity
     */
    @NotNull


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Item measurement(MeasurementEnum measurement) {
        this.measurement = measurement;
        return this;
    }

    /**
     * Get measurement
     *
     * @return measurement
     */
    @NotNull


    public MeasurementEnum getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementEnum measurement) {
        this.measurement = measurement;
    }

    public Item language(LanguageEnum language) {
        this.language = language;
        return this;
    }

    /**
     * Get language
     *
     * @return language
     */
    @NotNull


    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(this.id, item.id) &&
                Objects.equals(this.name, item.name) &&
                Objects.equals(this.quantity, item.quantity) &&
                Objects.equals(this.measurement, item.measurement) &&
                Objects.equals(this.language, item.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, measurement, language);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Item {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    measurement: ").append(toIndentedString(measurement)).append("\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

