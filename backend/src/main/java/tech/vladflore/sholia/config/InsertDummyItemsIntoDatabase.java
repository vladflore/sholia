package tech.vladflore.sholia.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tech.vladflore.sholia.item.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class InsertDummyItemsIntoDatabase implements CommandLineRunner {
    private final ItemRepository itemRepository;

    InsertDummyItemsIntoDatabase(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) {
        Resource resource = new ClassPathResource("raw-data-for-sholia.csv");
        try (Stream<String> csvLines = Files.lines(resource.getFile().toPath())) {
            csvLines.skip(1).forEach(line -> {
                String[] data = line.split(",");
                Map<Integer, String> dataMap = IntStream.range(0, data.length)
                        .boxed()
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
