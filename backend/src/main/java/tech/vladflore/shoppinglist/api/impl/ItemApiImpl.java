package tech.vladflore.shoppinglist.api.impl;

import org.springframework.http.ResponseEntity;
import tech.vladflore.shoppinglist.api.ItemApi;
import tech.vladflore.shoppinglist.model.Item;

import javax.validation.Valid;

public class ItemApiImpl implements ItemApi {
    @Override
    public ResponseEntity<Item> createItem(@Valid Item item) {
        return null;
    }
}
