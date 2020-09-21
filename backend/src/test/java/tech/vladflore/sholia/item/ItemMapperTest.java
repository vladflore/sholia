package tech.vladflore.sholia.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.vladflore.sholia.shoppinglist.ShoppingList;
import tech.vladflore.sholia.shoppinglist.ShoppingListDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ItemMapperTest {

    @Test
    @DisplayName("should map an item dto to an item entity")
    void toEntity() {
        ItemDto itemDto = createItemDto();

        Item item = ItemMapper.MAPPER.toEntity(itemDto);

        assertThat(item.getId()).isEqualTo(itemDto.getId());
        assertThat(item.getName()).isEqualTo(itemDto.getName());
        assertThat(item.getQuantity()).isEqualTo(itemDto.getQuantity());
        assertThat(item.getUnitPrice()).isEqualTo(itemDto.getUnitPrice());
        assertThat(item.getMeasurement()).isEqualByComparingTo(itemDto.getMeasurement());
        assertThat(item.getLanguage()).isEqualByComparingTo(itemDto.getLanguage());
        assertThat(item.getShoppingLists()).isNotNull();
        assertThat(item.getShoppingLists().size()).isEqualTo(1);

        List<String> expectedListsNames = item.getShoppingLists().stream().map(ShoppingList::getName).collect(Collectors.toList());
        List<String> actualListsNames = itemDto.getShoppingLists().stream().map(ShoppingListDto::getName).collect(Collectors.toList());

        assertThat(expectedListsNames).containsAll(actualListsNames);
    }

    @Test
    @DisplayName("should map an item entity to an item dto")
    void toDto() {
        Item item = createItem();
        ItemDto itemDto = ItemMapper.MAPPER.toDto(item);

        assertThat(itemDto.getId()).isEqualTo(item.getId());
        assertThat(itemDto.getName()).isEqualTo(item.getName());
        assertThat(itemDto.getQuantity()).isEqualTo(item.getQuantity());
        assertThat(itemDto.getUnitPrice()).isEqualTo(item.getUnitPrice());
        assertThat(itemDto.getMeasurement()).isEqualByComparingTo(item.getMeasurement());
        assertThat(itemDto.getLanguage()).isEqualByComparingTo(item.getLanguage());
    }

    @Test
    @DisplayName("should map a list of item dtos to a list of item entities")
    void toEntities() {
        List<ItemDto> itemDtos = List.of(createItemDto());
        List<Item> items = ItemMapper.MAPPER.toEntities(itemDtos);

        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getName()).isEqualTo(itemDtos.get(0).getName());
    }

    @Test
    @DisplayName("should map a list of item entities to a list of item dtos")
    void toDtos() {
        List<Item> items = List.of(createItem());
        List<ItemDto> itemDtos = ItemMapper.MAPPER.toDtos(items);

        assertThat(itemDtos).isNotNull();
        assertThat(itemDtos.size()).isEqualTo(1);
        assertThat(itemDtos.get(0).getName()).isEqualTo(items.get(0).getName());

    }

    private ItemDto createItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("item");
        itemDto.setQuantity(1L);
        itemDto.setUnitPrice(1.0);
        itemDto.setMeasurement(MeasurementEnum.PC);
        itemDto.setLanguage(LanguageEnum.EN);
        itemDto.setShoppingLists(Set.of(createShoppingListDto()));
        return itemDto;
    }

    private ShoppingListDto createShoppingListDto() {
        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setId(1L);
        shoppingListDto.setName("shopping list");
        return shoppingListDto;
    }

    private Item createItem() {
        Item item = new Item();
        item.setId(1L).setName("item").setQuantity(1L).setUnitPrice(1.0).setMeasurement(MeasurementEnum.PC).setLanguage(LanguageEnum.EN);
        return item;
    }
}
