package tech.vladflore.sholia.shoppinglist;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ShoppingListDto {

	private Long id;

	@NotEmpty
	private String name;

}
