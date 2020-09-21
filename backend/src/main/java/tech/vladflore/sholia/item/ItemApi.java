package tech.vladflore.sholia.item;

import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<ItemDto>> getItems(@RequestParam(required = false, name = "name") String itemName) {
        if (itemName.isBlank()) {
            List<Item> items = itemRepository.findAll();
            return ResponseEntity.ok(ItemMapper.MAPPER.toDtos(items));
        }

        List<Item> items = itemRepository.findByNameContainingIgnoreCase(itemName);
        return ResponseEntity.ok(ItemMapper.MAPPER.toDtos(items));
    }

    @PostMapping
    ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        Item item = ItemMapper.MAPPER.toEntity(itemDto);
        Item savedItem = itemRepository.save(item);
        //TODO this should probably return a created status with Location header
        return ResponseEntity.ok(ItemMapper.MAPPER.toDto(savedItem));
    }
}
