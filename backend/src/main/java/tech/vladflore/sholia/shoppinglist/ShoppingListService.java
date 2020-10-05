package tech.vladflore.sholia.shoppinglist;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.vladflore.sholia.item.Item;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingListDto> findShoppingLists(String shoppingListName) {
        if (StringUtils.isEmpty(shoppingListName)) {
            return ShoppingListMapper.MAPPER.toDtos(shoppingListRepository.findAll());
        }
        return ShoppingListMapper.MAPPER.toDtos(shoppingListRepository.findByNameContainingIgnoreCase(shoppingListName));
    }

    public ShoppingListDto save(ShoppingListDto shoppingList) {
        return ShoppingListMapper.MAPPER.toDto(shoppingListRepository.save(ShoppingListMapper.MAPPER.toEntity(shoppingList)));
    }

    public Optional<ShoppingList> findById(Long id) {
        return shoppingListRepository.findById(id);
    }

    public void addItemToShoppingList(Item item, ShoppingList shoppingList) {
        shoppingList.getItems().add(item);
        save(ShoppingListMapper.MAPPER.toDto(shoppingList));
    }
}
