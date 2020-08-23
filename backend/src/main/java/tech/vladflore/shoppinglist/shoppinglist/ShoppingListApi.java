package tech.vladflore.shoppinglist.shoppinglist;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.vladflore.shoppinglist.item.Item;

import javax.validation.Valid;
import java.util.List;


@Validated
public interface ShoppingListApi {

    /**
     * GET /shopping-lists : Get all the shopping lists
     * Get all the shopping lists
     *
     * @return Successful operation (status code 200)
     */
    @RequestMapping(value = "/shopping-lists",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<ShoppingList>> allShoppingLists() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /shopping-lists : Creates a new shopping list resource
     * Creates a new shopping list resource
     *
     * @param shoppingList The body of this POST request to create a new shopping list (required)
     * @return The shopping list has been created (status code 201)
     */
    @RequestMapping(value = "/shopping-lists",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    default ResponseEntity<EntityModel<ShoppingList>> createShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /shopping-lists/{id}/items : Get all items from the shopping list
     * Get all items from the shopping list
     *
     * @param id The id of the shopping list (required)
     * @return successful operation (status code 200)
     */
    @RequestMapping(value = "/shopping-lists/{id}/items",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<Item>> getItemsForShoppingList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /shopping-lists/{id} : Get a shopping list
     * Get a shopping list
     *
     * @param id The id of the shopping list (required)
     * @return Successful operation (status code 200)
     */
    @RequestMapping(value = "/shopping-lists/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<ShoppingList>> getShoppingListById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
