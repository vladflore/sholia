package tech.vladflore.shoppinglist.shoppinglist;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.shoppinglist.item.Item;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoppingListApi {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListApi(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
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
}
