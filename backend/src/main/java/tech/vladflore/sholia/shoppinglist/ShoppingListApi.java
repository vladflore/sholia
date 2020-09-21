package tech.vladflore.sholia.shoppinglist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tech.vladflore.sholia.item.Item;
import tech.vladflore.sholia.item.ItemDto;
import tech.vladflore.sholia.item.ItemMapper;
import tech.vladflore.sholia.item.ItemRepository;

import javax.validation.Valid;
import java.util.Collections;
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
    ResponseEntity<List<ShoppingListDto>> getShoppingLists(@RequestParam(required = false, name = "name") String shoppingListName) {
        if (StringUtils.isEmpty(shoppingListName)) {
            List<ShoppingList> shoppingLists = shoppingListRepository.findAll();
            return ResponseEntity.ok(ShoppingListMapper.MAPPER.toDtos(shoppingLists));
        }
        List<ShoppingList> shoppingLists = shoppingListRepository.findByNameContainingIgnoreCase(shoppingListName);
        return ResponseEntity.ok(ShoppingListMapper.MAPPER.toDtos(shoppingLists));
    }

    @PostMapping
    ResponseEntity<ShoppingListDto> createShoppingList(@Valid @RequestBody ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = ShoppingListMapper.MAPPER.toEntity(shoppingListDto);
        ShoppingList savedShoppingList = shoppingListRepository.save(shoppingList);
        return ResponseEntity.ok(ShoppingListMapper.MAPPER.toDto(savedShoppingList));
    }

    @GetMapping("/{id}/items")
    ResponseEntity<List<ItemDto>> getItemsForShoppingList(@PathVariable("id") Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);
        if (shoppingList.isPresent()) {
            List<Item> items = shoppingList.get().getItems();
            if (items.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }
            return ResponseEntity.ok(ItemMapper.MAPPER.toDtos(items));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{listId}/items/{itemId}")
    ResponseEntity<?> addItemOnShoppingList(@PathVariable Long itemId, @PathVariable Long listId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(listId);
        if (shoppingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        shoppingList.get().getItems().add(item.get());
        shoppingListRepository.save(shoppingList.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
