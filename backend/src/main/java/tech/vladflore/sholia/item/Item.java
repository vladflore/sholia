package tech.vladflore.sholia.item;

import tech.vladflore.sholia.shoppinglist.ShoppingList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private MeasurementEnum measurement;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @ManyToMany(mappedBy = "items")
    private final Set<ShoppingList> shoppingLists = new HashSet<>();

    private Double unitPrice;

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Item setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public MeasurementEnum getMeasurement() {
        return measurement;
    }

    public Item setMeasurement(MeasurementEnum measurement) {
        this.measurement = measurement;
        return this;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public Item setLanguage(LanguageEnum language) {
        this.language = language;
        return this;
    }

    public Set<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Item setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
