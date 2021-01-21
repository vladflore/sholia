package tech.vladflore.sholia.shoppinglist;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import tech.vladflore.sholia.item.Item;
import tech.vladflore.sholia.item.ItemDto;
import tech.vladflore.sholia.item.ItemMapper;
import tech.vladflore.sholia.item.ItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lists")
public class ShoppingListController {

	private final ShoppingListService shoppingListService;

	private final ItemService itemService;

	private final ItemMapper itemMapper;

	public ShoppingListController(ShoppingListService shoppingListService, ItemService itemService,
			ItemMapper itemMapper) {
		this.shoppingListService = shoppingListService;
		this.itemService = itemService;
		this.itemMapper = itemMapper;
	}

	@GetMapping
	public ResponseEntity<List<ShoppingListDto>> getShoppingLists(
			@RequestParam(required = false, name = "name") String shoppingListName) {
		return ResponseEntity.ok(shoppingListService.findShoppingLists(shoppingListName));
	}

	@PostMapping
	public ResponseEntity<ShoppingListDto> createShoppingList(@Valid @RequestBody ShoppingListDto shoppingList) {
		return ResponseEntity.ok(shoppingListService.save(shoppingList));
	}

	@GetMapping("/{id}/items")
	public ResponseEntity<List<ItemDto>> getItemsForShoppingList(@PathVariable("id") Long id) {
		Optional<ShoppingList> shoppingList = shoppingListService.findById(id);
		return shoppingList.map(list -> ResponseEntity.ok(itemMapper.toDtos(list.getItems())))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{listId}/items/{itemId}")
	@SuppressWarnings("java:S1452")
	public ResponseEntity<?> addItemOnShoppingList(@PathVariable Long itemId, @PathVariable Long listId) {
		Optional<Item> item = itemService.findById(itemId);
		if (item.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		Optional<ShoppingList> shoppingList = shoppingListService.findById(listId);
		if (shoppingList.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		shoppingListService.addItemToShoppingList(item.get(), shoppingList.get());

		return ResponseEntity.ok().build();
	}

}
