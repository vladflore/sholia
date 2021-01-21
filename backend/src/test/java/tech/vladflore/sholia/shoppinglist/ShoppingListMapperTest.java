package tech.vladflore.sholia.shoppinglist;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingListMapperTest {

	private final ShoppingListMapper shoppingListMapper = new ShoppingListMapperImpl();

	@Test
	void toEntity() {
		ShoppingListDto shoppingListDto = createShoppingListDto();

		ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListDto);

		assertThat(shoppingList.getId()).isEqualTo(shoppingListDto.getId());
		assertThat(shoppingList.getName()).isEqualTo(shoppingListDto.getName());
	}

	@Test
	void toDto() {
		ShoppingList shoppingList = createShoppingList();
		ShoppingListDto shoppingListDto = shoppingListMapper.toDto(shoppingList);

		assertThat(shoppingListDto.getId()).isEqualTo(shoppingList.getId());
		assertThat(shoppingListDto.getName()).isEqualTo(shoppingList.getName());
	}

	@Test
	void toEntities() {
		List<ShoppingListDto> shoppingListDtos = List.of(createShoppingListDto());
		List<ShoppingList> shoppingLists = shoppingListMapper.toEntities(shoppingListDtos);

		assertThat(shoppingLists).isNotNull();
		assertThat(shoppingLists.size()).isEqualTo(1);
		assertThat(shoppingLists.get(0).getName()).isEqualTo(shoppingListDtos.get(0).getName());
	}

	@Test
	void toDtos() {
		List<ShoppingList> shoppingLists = List.of(createShoppingList());
		List<ShoppingListDto> shoppingListDtos = shoppingListMapper.toDtos(shoppingLists);

		assertThat(shoppingListDtos).isNotNull();
		assertThat(shoppingListDtos.size()).isEqualTo(1);
		assertThat(shoppingListDtos.get(0).getName()).isEqualTo(shoppingLists.get(0).getName());
	}

	private ShoppingListDto createShoppingListDto() {
		ShoppingListDto shoppingListDto = new ShoppingListDto();
		shoppingListDto.setId(1L);
		shoppingListDto.setName("shopping list");
		return shoppingListDto;
	}

	private ShoppingList createShoppingList() {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setId(1L);
		shoppingList.setName("shopping list");
		return shoppingList;
	}

}
