package tech.vladflore.sholia.shoppinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

	List<ShoppingList> findByNameContainingIgnoreCase(String shoppingListName);

}
