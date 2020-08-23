package tech.vladflore.shoppinglist.item;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemApi {

    private final ItemRepository itemRepository;

    public ItemApi(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/shopping/items")
    ResponseEntity<List<Item>> getItems(@RequestParam(required = false, name = "name") String itemName) {
        if (StringUtils.isEmpty(itemName)) {
            return ResponseEntity.ok(itemRepository.findAll());
        }
        return ResponseEntity.ok(itemRepository.findAllByName(itemName));
    }

    @PostMapping("/shopping/items")
    ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        return ResponseEntity.ok(itemRepository.save(item));
    }
}
