package tech.vladflore.shoppinglist.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.shoppinglist.item.Item;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoppingListApi {

    @GetMapping("/shopping/lists")
    ResponseEntity<List<ShoppingList>> allShoppingLists() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/shopping/lists")
    ResponseEntity<ShoppingList> createShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/shopping/lists/{id}/items")
    ResponseEntity<List<Item>> getItemsForShoppingList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/shopping/lists/{id}")
    ResponseEntity<List<ShoppingList>> getShoppingListById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
