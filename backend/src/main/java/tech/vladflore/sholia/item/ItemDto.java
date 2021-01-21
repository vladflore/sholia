package tech.vladflore.sholia.item;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tech.vladflore.sholia.shoppinglist.ShoppingListDto;

@Data
public class ItemDto {

	private Long id;

	@NotEmpty
	private String name;

	@NotNull
	private Double quantity;

	@NotNull
	private MeasurementEnum measurement;

	@NotNull
	private LanguageEnum language;

	@JsonProperty("shopping_lists")
	private Set<ShoppingListDto> shoppingLists;

	@JsonProperty("price_per_quantity")
	private Double pricePerQuantity;

	@NotEmpty
	private String shop;

	private String notes;

	@NotNull
	private CurrencyEnum currency;

}
