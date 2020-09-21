package tech.vladflore.sholia.item;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemMapper MAPPER = Mappers.getMapper(ItemMapper.class);

    Item toEntity(ItemDto itemDto);

    ItemDto toDto(Item item);

    List<Item> toEntities(List<ItemDto> itemDtos);

    List<ItemDto> toDtos(List<Item> items);
}
