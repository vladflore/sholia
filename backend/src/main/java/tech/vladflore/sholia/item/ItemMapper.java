package tech.vladflore.sholia.item;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemDto itemDto);

    ItemDto toDto(Item item);

    List<Item> toEntities(List<ItemDto> itemDtos);

    List<ItemDto> toDtos(List<Item> items);
}
