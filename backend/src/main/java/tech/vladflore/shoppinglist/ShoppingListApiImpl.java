package tech.vladflore.shoppinglist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.vladflore.shoppinglist.api.ShoppingListApi;
import tech.vladflore.shoppinglist.model.Item;
import tech.vladflore.shoppinglist.model.ShoppingList;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoppingListApiImpl implements ShoppingListApi {

//    private final ShoppingListModelAssembler assembler;
//
//    public ShoppingListApiImpl(ShoppingListModelAssembler assembler) {
//        this.assembler = assembler;
//    }

    @Override
    public ResponseEntity<List<ShoppingList>> allShoppingLists() {
        return null;
    }

    @Override
    public ResponseEntity<Object> createShoppingList(@Valid ShoppingList shoppingList) {
        // TODO persist the shopping list
//        ShoppingList newShoppingList = new ShoppingList().id(new Random().nextLong()).name(shoppingList.getName());
//        EntityModel<ShoppingList> model = assembler.toModel(newShoppingList);
//        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
        return null;
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
