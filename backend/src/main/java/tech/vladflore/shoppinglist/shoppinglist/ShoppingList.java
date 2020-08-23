package tech.vladflore.shoppinglist.shoppinglist;

import tech.vladflore.shoppinglist.item.Item;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class ShoppingList {
    private Long id;
    @NotEmpty
    private String name;
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public ShoppingList id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingList name(String name) {
        this.name = name;
        return this;
    }

    public List<Item> getItems() {
        return unmodifiableList(items);
    }

    public ShoppingList items(List<Item> items) {
        this.items = items;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
