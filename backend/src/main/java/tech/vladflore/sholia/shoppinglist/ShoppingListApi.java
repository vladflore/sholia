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
    ResponseEntity<List<Item>> getItemsForShoppingList(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shoppingListRepository.getItemsForShoppingList(id));
    }

    @PutMapping("/{listId}/items/{itemId}")
    ResponseEntity<?> addItemOnShoppingList(@PathVariable Long listId, @PathVariable Long itemId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(listId);
        if (!shoppingList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Item> item = itemRepository.findById(itemId);
        if (!item.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shoppingList.get().addItem(item.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
