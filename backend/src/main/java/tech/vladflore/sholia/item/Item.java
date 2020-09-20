package tech.vladflore.sholia.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.vladflore.sholia.shoppinglist.ShoppingList;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Long quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MeasurementEnum measurement;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @ManyToMany(mappedBy = "items")
    private final Set<ShoppingList> shoppingLists = new HashSet<>();

    @JsonProperty("unit_price")
    private Double unitPrice;

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

    public Set<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public Item shoppingList(ShoppingList shoppingList) {
        this.shoppingLists.add(shoppingList);
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
