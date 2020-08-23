package tech.vladflore.shoppinglist.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.shoppinglist.item.Item;
import tech.vladflore.shoppinglist.item.ItemRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingListApi {

    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;

    public ShoppingListApi(ShoppingListRepository shoppingListRepository, ItemRepository itemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/shopping/lists")
    ResponseEntity<List<ShoppingList>> getShoppingLists(@RequestParam(required = false, name = "name") String shoppingListName) {
        if (StringUtils.isEmpty(shoppingListName)) {
            return ResponseEntity.ok(shoppingListRepository.findAll());
        }
        return ResponseEntity.ok(shoppingListRepository.findAllByName(shoppingListName));
    }

    @PostMapping("/shopping/lists")
    ResponseEntity<ShoppingList> createShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
        return ResponseEntity.ok(shoppingListRepository.save(shoppingList));
    }

    @GetMapping("/shopping/lists/{id}/items")
    ResponseEntity<List<Item>> getItemsForShoppingList(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shoppingListRepository.getItemsForShoppingList(id));
    }

    @PutMapping("/shopping/lists/{id}/items")
    ResponseEntity<?> addItemOnShoppingList(@PathVariable(name = "id") Long shoppingListId,
                                            @Valid @RequestBody Long itemId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
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
