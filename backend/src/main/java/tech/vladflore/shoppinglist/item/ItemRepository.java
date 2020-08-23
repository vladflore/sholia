package tech.vladflore.shoppinglist.item;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

@Component
public class ItemRepository {
    private final List<Item> items;
    private final Random random = new Random();

    public ItemRepository() {
        items = new ArrayList<>();
        Item item = new Item()
                .id(random.nextLong())
                .name("item 1")
                .quantity(2L)
                .measurement(MeasurementEnum.PC)
                .language(LanguageEnum.EN);
        items.add(item);
        item = new Item()
                .id(random.nextLong())
                .name("item 2")
                .quantity(1L)
                .measurement(MeasurementEnum.KG)
                .language(LanguageEnum.EN);
        items.add(item);
    }

    public List<Item> findAll() {
        return unmodifiableList(items);
    }

    public Optional<Item> findById(Long id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    public List<Item> findAllByName(String name) {
        return items.stream().filter(item -> item.getName().contains(name)).collect(Collectors.toList());
    }

    public Item save(Item newItem) {
        newItem.id(random.nextLong());
        items.add(newItem);
        return newItem;
    }
}
