package tech.vladflore.sholia.item;

import lombok.Data;
import tech.vladflore.sholia.shoppinglist.ShoppingList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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
}
