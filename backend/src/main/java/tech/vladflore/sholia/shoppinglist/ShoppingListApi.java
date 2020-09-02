package tech.vladflore.sholia.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.sholia.item.Item;
import tech.vladflore.sholia.item.ItemRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/lists")
public class ShoppingListApi {

    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;

    public ShoppingListApi(ShoppingListRepository shoppingListRepository, ItemRepository itemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    ResponseEntity<List<ShoppingList>> getShoppingLists(@RequestParam(required = false, name = "name") String shoppingListName) {
        if (StringUtils.isEmpty(shoppingListName)) {
            return ResponseEntity.ok(shoppingListRepository.findAll());
        }
        return ResponseEntity.ok(shoppingListRepository.findAllByName(shoppingListName));
    }

    @PostMapping
    ResponseEntity<ShoppingList> createShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
        return ResponseEntity.ok(shoppingListRepository.save(shoppingList));
    }

    @GetMapping("/{id}/items")
    ResponseEntity<Set<Item>> getItemsForShoppingList(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        return shoppingList.map(list -> ResponseEntity.ok(list.getItems())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{listId}/items/{itemId}")
    ResponseEntity<?> addItemOnShoppingList(@PathVariable Long itemId, @PathVariable Long listId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (!item.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(listId);
        if (!shoppingList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        shoppingList.get().getItems().add(item.get());
        shoppingListRepository.save(shoppingList.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
