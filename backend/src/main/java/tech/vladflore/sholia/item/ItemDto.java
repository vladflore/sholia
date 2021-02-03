package tech.vladflore.sholia.item;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tech.vladflore.sholia.shoppinglist.ShoppingListDto;

@Data
@ApiModel(description = "Details about ItemDto")
public class ItemDto {

	@ApiModelProperty(notes = "The unique id of the item")
	private Long id;

	@NotEmpty
	@ApiModelProperty(notes = "The name of the item")
	private String name;

	@NotNull
	@ApiModelProperty(notes = "The quantity of the item")
	private Double quantity;

	@NotNull
	@ApiModelProperty(notes = "The measurement for the quantity")
	private MeasurementEnum measurement;

	@NotNull
	@ApiModelProperty(notes = "The language of the item")
	private LanguageEnum language;

	@JsonProperty("shopping_lists")
	@ApiModelProperty(notes = "The shopping lists the item belongs to")
	private Set<ShoppingListDto> shoppingLists;

	@JsonProperty("price_per_quantity")
	@ApiModelProperty(notes = "The price per quantity of item")
	private Double pricePerQuantity;

	@NotEmpty
	@ApiModelProperty(notes = "The shop the item was bought from")
	private String shop;

	@ApiModelProperty(notes = "Notes about the item")
	private String notes;

	@NotNull
	@ApiModelProperty(notes = "The money currency the item was bought with")
	private CurrencyEnum currency;

}
