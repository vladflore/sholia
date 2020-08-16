package tech.vladflore.shoppinglist.api.impl;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.vladflore.shoppinglist.api.ShoppingListApi;
import tech.vladflore.shoppinglist.model.Item;
import tech.vladflore.shoppinglist.model.ShoppingList;
import tech.vladflore.shoppinglist.model.assembler.ShoppingListModelAssembler;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
public class ShoppingListApiImpl implements ShoppingListApi {

    private final ShoppingListModelAssembler assembler;

    public ShoppingListApiImpl(ShoppingListModelAssembler assembler) {
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<ShoppingList>> allShoppingLists() {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<ShoppingList>> createShoppingList(@Valid ShoppingList shoppingList) {
        // TODO persist the shopping list
        ShoppingList newShoppingList = new ShoppingList().id(new Random().nextLong()).name(shoppingList.getName());
        EntityModel<ShoppingList> model = assembler.toModel(newShoppingList);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @Override
    public ResponseEntity<List<Item>> getItemsForShoppingList(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ShoppingList>> getShoppingListById(Long id) {
        return null;
    }
}
