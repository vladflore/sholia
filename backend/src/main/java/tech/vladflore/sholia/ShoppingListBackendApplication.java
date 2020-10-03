package tech.vladflore.sholia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tech.vladflore.sholia.item.*;

import java.nio.file.Files;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class ShoppingListBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingListBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(ItemRepository itemRepository) {
        return args -> {
            Resource resource = new ClassPathResource("raw-data-for-sholia.csv");
            Stream<String> csvLines = Files.lines(resource.getFile().toPath());
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
        };
    }
}
