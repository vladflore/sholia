package tech.vladflore.sholia.shoppinglist;

import java.util.List;
import java.util.Optional;

import tech.vladflore.sholia.item.Item;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;

	private final ShoppingListMapper shoppingListMapper;

	public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListMapper shoppingListMapper) {
		this.shoppingListRepository = shoppingListRepository;
		this.shoppingListMapper = shoppingListMapper;
	}

	public List<ShoppingListDto> findShoppingLists(String shoppingListName) {
		if (StringUtils.isEmpty(shoppingListName)) {
			return shoppingListMapper.toDtos(shoppingListRepository.findAll());
		}
		return shoppingListMapper.toDtos(shoppingListRepository.findByNameContainingIgnoreCase(shoppingListName));
	}

	public ShoppingListDto save(ShoppingListDto shoppingList) {
		return shoppingListMapper.toDto(shoppingListRepository.save(shoppingListMapper.toEntity(shoppingList)));
	}

	public Optional<ShoppingList> findById(Long id) {
		return shoppingListRepository.findById(id);
	}

	public void addItemToShoppingList(Item item, ShoppingList shoppingList) {
		shoppingList.getItems().add(item);
		save(shoppingListMapper.toDto(shoppingList));
	}

}
