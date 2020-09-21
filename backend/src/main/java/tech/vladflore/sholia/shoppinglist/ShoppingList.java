package tech.vladflore.sholia.shoppinglist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tech.vladflore.sholia.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoppinglist")
@Data
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "shoppinglist_item",
            joinColumns = @JoinColumn(name = "shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private final List<Item> items = new ArrayList<>();
}
