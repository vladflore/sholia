package tech.vladflore.shoppinglist.item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Item {
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private Long quantity;
    @NotNull
    private MeasurementEnum measurement;
    @NotNull
    private LanguageEnum language;

    public Long getId() {
        return id;
    }

    public Item id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item name(String name) {
        this.name = name;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Item quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public MeasurementEnum getMeasurement() {
        return measurement;
    }

    public Item measurement(MeasurementEnum measurement) {
        this.measurement = measurement;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public Item language(LanguageEnum language) {
        this.language = language;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", measurement=" + measurement +
                ", language=" + language +
                '}';
    }
}
