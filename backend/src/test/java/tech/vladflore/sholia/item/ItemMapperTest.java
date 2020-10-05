package tech.vladflore.sholia.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemMapperTest {

    //TODO does is make any sense to test the convertion for Set<ShoppingList> shoppingLists ? Do I actually need that ?

    @Test
    @DisplayName("should map an item dto to an item entity")
    void toEntity() {
        ItemDto itemDto = createItemDto();

        Item item = ItemMapper.MAPPER.toEntity(itemDto);

        assertThat(item.getId()).isEqualTo(itemDto.getId());
        assertThat(item.getName()).isEqualTo(itemDto.getName());
        assertThat(item.getQuantity()).isEqualTo(itemDto.getQuantity());
        assertThat(item.getPricePerQuantity()).isEqualTo(itemDto.getPricePerQuantity());
        assertThat(item.getMeasurement()).isEqualByComparingTo(itemDto.getMeasurement());
        assertThat(item.getLanguage()).isEqualByComparingTo(itemDto.getLanguage());
        assertThat(item.getCurrency()).isEqualByComparingTo(itemDto.getCurrency());
        assertThat(item.getShop()).isEqualTo(itemDto.getShop());
        assertThat(item.getNotes()).isEqualTo(itemDto.getNotes());
    }

    @Test
    @DisplayName("should map an item entity to an item dto")
    void toDto() {
        Item item = createItem();
        ItemDto itemDto = ItemMapper.MAPPER.toDto(item);

        assertThat(itemDto.getId()).isEqualTo(item.getId());
        assertThat(itemDto.getName()).isEqualTo(item.getName());
        assertThat(itemDto.getQuantity()).isEqualTo(item.getQuantity());
        assertThat(itemDto.getPricePerQuantity()).isEqualTo(item.getPricePerQuantity());
        assertThat(itemDto.getMeasurement()).isEqualByComparingTo(item.getMeasurement());
        assertThat(itemDto.getLanguage()).isEqualByComparingTo(item.getLanguage());
        assertThat(itemDto.getCurrency()).isEqualByComparingTo(item.getCurrency());
        assertThat(itemDto.getShop()).isEqualTo(item.getShop());
        assertThat(itemDto.getNotes()).isEqualTo(item.getNotes());
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

    @Test
    @DisplayName("should map an empty list of item entities to an empty list of item dtos")
    void toEmptyDtos() {
        List<Item> items = Collections.emptyList();
        List<ItemDto> itemDtos = ItemMapper.MAPPER.toDtos(items);

        assertThat(itemDtos).isNotNull();
        assertThat(itemDtos.size()).isEqualTo(0);
    }

    private ItemDto createItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("item");
        itemDto.setQuantity(1.0);
        itemDto.setPricePerQuantity(1.0);
        itemDto.setMeasurement(MeasurementEnum.PC);
        itemDto.setLanguage(LanguageEnum.EN);
        itemDto.setShop("shop");
        itemDto.setNotes("only the best");
        itemDto.setCurrency(CurrencyEnum.EURO);
        return itemDto;
    }

    private Item createItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("item");
        item.setQuantity(1.0);
        item.setPricePerQuantity(1.0);
        item.setMeasurement(MeasurementEnum.PC);
        item.setLanguage(LanguageEnum.EN);
        item.setShop("shop");
        item.setNotes("only the best");
        item.setCurrency(CurrencyEnum.EURO);

        return item;
    }
}
