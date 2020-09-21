package tech.vladflore.sholia.shoppinglist;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShoppingListMapper {

    ShoppingListMapper MAPPER = Mappers.getMapper(ShoppingListMapper.class);

    ShoppingList toEntity(ShoppingListDto shoppingListDto);

    ShoppingListDto toDto(ShoppingList shoppingList);

    List<ShoppingList> toEntities(List<ShoppingListDto> shoppingListDtos);

    List<ShoppingListDto> toDtos(List<ShoppingList> shoppingLists);
}
