package tech.vladflore.sholia.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByNameContainingIgnoreCase(String itemName);

}
