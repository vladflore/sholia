package tech.vladflore.sholia.item;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	Item toEntity(ItemDto itemDto);

	ItemDto toDto(Item item);

	List<Item> toEntities(List<ItemDto> itemDtos);

	List<ItemDto> toDtos(List<Item> items);

}
