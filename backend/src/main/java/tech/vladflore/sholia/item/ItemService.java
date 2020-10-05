package tech.vladflore.sholia.item;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto save(ItemDto item) {
        return ItemMapper.MAPPER.toDto(itemRepository.save(ItemMapper.MAPPER.toEntity(item)));
    }

    public List<ItemDto> findItems(String itemName) {
        if (itemName.isBlank()) {
            return ItemMapper.MAPPER.toDtos(itemRepository.findAll());
        }
        return ItemMapper.MAPPER.toDtos(itemRepository.findByNameContainingIgnoreCase(itemName));
    }

    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }
}
