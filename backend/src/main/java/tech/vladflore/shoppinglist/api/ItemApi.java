package tech.vladflore.shoppinglist.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.vladflore.shoppinglist.model.Item;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ItemApi {

    /**
     * GET /items : Get all the items
     * Get all the items
     *
     * @return Successful operation (status code 200)
     */
    @RequestMapping(value = "/items",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<Item>> allItems() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /items : Createa a new item resource
     * Createa a new item resource
     *
     * @param item The body of this POST request to create a new item (required)
     * @return The item has been created (status code 201)
     */
    @RequestMapping(value = "/items",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    default ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /items/{id} : Get an item
     * Get an item
     *
     * @param id The id of the item (required)
     * @return Successful operation (status code 200)
     */
    @RequestMapping(value = "/items/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<Item>> getItemById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
