package tech.vladflore.sholia.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.sholia.item.Item;
import tech.vladflore.sholia.item.ItemDto;
import tech.vladflore.sholia.item.ItemMapper;
import tech.vladflore.sholia.item.ItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ItemService itemService;

    public ShoppingListController(ShoppingListService shoppingListService, ItemService itemService) {
        this.shoppingListService = shoppingListService;
        this.itemService = itemService;
    }

    @GetMapping
    ResponseEntity<List<ShoppingListDto>> getShoppingLists(@RequestParam(required = false, name = "name") String shoppingListName) {
        return ResponseEntity.ok(shoppingListService.findShoppingLists(shoppingListName));
    }

    @PostMapping
    ResponseEntity<ShoppingListDto> createShoppingList(@Valid @RequestBody ShoppingListDto shoppingList) {
        return ResponseEntity.ok(shoppingListService.save(shoppingList));
    }

    @GetMapping("/{id}/items")
    ResponseEntity<List<ItemDto>> getItemsForShoppingList(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListService.findById(id);
        return shoppingList
                .map(list -> ResponseEntity.ok(ItemMapper.MAPPER.toDtos(list.getItems())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{listId}/items/{itemId}")
    ResponseEntity<?> addItemOnShoppingList(@PathVariable Long itemId, @PathVariable Long listId) {
        Optional<Item> item = itemService.findById(itemId);
        if (item.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ShoppingList> shoppingList = shoppingListService.findById(listId);
        if (shoppingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        shoppingListService.addItemToShoppingList(item.get(), shoppingList.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
