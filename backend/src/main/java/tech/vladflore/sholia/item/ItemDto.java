package tech.vladflore.sholia.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tech.vladflore.sholia.shoppinglist.ShoppingListDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ItemDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Long quantity;

    @NotNull
    private MeasurementEnum measurement;

    @NotNull
    private LanguageEnum language;

    @JsonProperty("shopping_lists")
    private Set<ShoppingListDto> shoppingLists;

    @JsonProperty("unit_price")
    private Double unitPrice;
}
