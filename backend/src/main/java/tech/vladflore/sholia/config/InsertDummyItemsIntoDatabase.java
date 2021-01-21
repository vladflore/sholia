package tech.vladflore.sholia.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import tech.vladflore.sholia.item.CurrencyEnum;
import tech.vladflore.sholia.item.Item;
import tech.vladflore.sholia.item.ItemRepository;
import tech.vladflore.sholia.item.LanguageEnum;
import tech.vladflore.sholia.item.MeasurementEnum;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class InsertDummyItemsIntoDatabase implements CommandLineRunner {

	private final ItemRepository itemRepository;

	InsertDummyItemsIntoDatabase(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public void run(String... args) {
		Resource resource = new ClassPathResource("raw-data-for-sholia.csv");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			Stream<String> csvLines = reader.lines();
			csvLines.skip(1).forEach(line -> {
				String[] data = line.split(",");
				Map<Integer, String> dataMap = IntStream.range(0, data.length).boxed()
						.collect(Collectors.toMap(Function.identity(), key -> data[key]));
				Item item = new Item();
				item.setName(dataMap.get(0));
				item.setQuantity(Double.valueOf(dataMap.get(1)));
				item.setMeasurement(MeasurementEnum.fromValue(dataMap.get(2)));
				item.setLanguage(LanguageEnum.fromValue(dataMap.get(3)));
				item.setPricePerQuantity(Double.valueOf(dataMap.get(4)));
				item.setCurrency(CurrencyEnum.fromValue(dataMap.get(5)));
				item.setShop(dataMap.get(6));
				item.setNotes(dataMap.getOrDefault(7, ""));

				itemRepository.save(item);
			});
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
