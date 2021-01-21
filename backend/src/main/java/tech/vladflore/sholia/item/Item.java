package tech.vladflore.sholia.item;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import tech.vladflore.sholia.shoppinglist.ShoppingList;

@Entity
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Double quantity;

	@Enumerated(EnumType.STRING)
	private MeasurementEnum measurement;

	@Enumerated(EnumType.STRING)
	private LanguageEnum language;

	@ManyToMany(mappedBy = "items")
	private final Set<ShoppingList> shoppingLists = new HashSet<>();

	private Double pricePerQuantity;

	private String shop;

	private String notes;

	@Enumerated(EnumType.STRING)
	private CurrencyEnum currency;

}
