package tech.vladflore.sholia.item;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public ResponseEntity<List<ItemDto>> getItems(@RequestParam(required = false, name = "name") String itemName) {
		return ResponseEntity.ok(itemService.findItems(itemName));
	}

	@PostMapping
	public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto item) {
		return ResponseEntity.ok(itemService.save(item));
	}

}
