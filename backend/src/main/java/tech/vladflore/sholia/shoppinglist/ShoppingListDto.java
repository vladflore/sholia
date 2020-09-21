package tech.vladflore.sholia.shoppinglist;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ShoppingListDto {
    private Long id;

    @NotEmpty
    private String name;
}
