package tech.vladflore.sholia.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final ItemRepository itemRepository;

	private final ItemMapper itemMapper;

	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	public ItemDto save(ItemDto item) {
		return itemMapper.toDto(itemRepository.save(itemMapper.toEntity(item)));
	}

	public List<ItemDto> findItems(String itemName) {
		if (itemName.isBlank()) {
			return itemMapper.toDtos(itemRepository.findAll());
		}
		return itemMapper.toDtos(itemRepository.findByNameContainingIgnoreCase(itemName));
	}

	public Optional<Item> findById(Long itemId) {
		return itemRepository.findById(itemId);
	}

}
