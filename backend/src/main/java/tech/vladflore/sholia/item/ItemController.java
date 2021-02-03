package tech.vladflore.sholia.item;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	@ApiOperation(value = "Get items",
			notes = "If name is not specified, get all items, otherwise only those which contain name in their names",
			responseContainer = "List", response = ItemDto.class)
	public ResponseEntity<List<ItemDto>> getItems(
			@ApiParam("The string by which items are filtered and returned, if not given, return all items") @RequestParam(
					required = false, name = "name") String itemName) {
		return ResponseEntity.ok(itemService.findItems(itemName));
	}

	@PostMapping
	@ApiOperation(value = "Create a new item", notes = "Create a new item", response = ItemDto.class)
	public ResponseEntity<ItemDto> createItem(
			@ApiParam("The payload representing an item to be created") @Valid @RequestBody ItemDto item) {
		return ResponseEntity.ok(itemService.save(item));
	}

}
