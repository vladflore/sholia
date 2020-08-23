package tech.vladflore.shoppinglist.shoppinglist;

import org.springframework.stereotype.Component;
import tech.vladflore.shoppinglist.item.Item;
import tech.vladflore.shoppinglist.item.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

@Component
public class ShoppingListRepository {
    private final List<ShoppingList> shoppingLists;
    private final Random random = new Random();

    public ShoppingListRepository(ItemRepository itemRepository) {

        shoppingLists = new ArrayList<>();
        ShoppingList shoppingList = new ShoppingList()
                .id(random.nextLong())
                .name("Shopping List 1")
                .items(Stream.of(itemRepository.findAll().get(0)).collect(Collectors.toList()));
        shoppingLists.add(shoppingList);
        shoppingList = new ShoppingList()
                .id(random.nextLong())
                .name("Shopping List 2")
                .items(Stream.of(itemRepository.findAll().get(1)).collect(Collectors.toList()));
        shoppingLists.add(shoppingList);
    }

    public List<ShoppingList> findAll() {
        return unmodifiableList(shoppingLists);
    }

    public ShoppingList save(ShoppingList newShoppingList) {
        newShoppingList.id(random.nextLong());
        shoppingLists.add(newShoppingList);
        return newShoppingList;
    }

    public List<ShoppingList> findAllByName(String name) {
        return shoppingLists.stream().filter(sl -> sl.getName().contains(name)).collect(Collectors.toList());
    }


    public List<Item> getItemsForShoppingList(Long shoppingListId) {
        return shoppingLists.stream()
                .filter(sl -> sl.getId().equals(shoppingListId))
                .findFirst()
                .map(ShoppingList::getItems)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Shopping list with id %d does not exist!", shoppingListId)));
    }

    public Optional<ShoppingList> findById(Long id) {
        return shoppingLists.stream().filter(sl -> sl.getId().equals(id)).findFirst();
    }
}
