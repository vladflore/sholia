package tech.vladflore.sholia.shoppinglist;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    ShoppingList toEntity(ShoppingListDto shoppingListDto);

    ShoppingListDto toDto(ShoppingList shoppingList);

    List<ShoppingList> toEntities(List<ShoppingListDto> shoppingListDtos);

    List<ShoppingListDto> toDtos(List<ShoppingList> shoppingLists);
}
