package tech.vladflore.sholia.item;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemApi {

    private final ItemRepository itemRepository;

    public ItemApi(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    ResponseEntity<List<Item>> getItems(@RequestParam(required = false, name = "name") String itemName) {
        if (StringUtils.isEmpty(itemName)) {
            return ResponseEntity.ok(itemRepository.findAll());
        }
        return ResponseEntity.ok(itemRepository.findAllByName(itemName));
    }

    @PostMapping
    ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        return ResponseEntity.ok(itemRepository.save(item));
    }
}
