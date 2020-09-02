package tech.vladflore.sholia.shoppinglist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tech.vladflore.sholia.item.Item;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shoppinglist")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "shoppinglist_item",
            joinColumns = @JoinColumn(name = "shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private final Set<Item> items = new HashSet<>();

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

    public Set<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
