package tech.vladflore.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingListBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListBackendApplication.class, args);
    }

//    @Bean
//    public LinkDiscoverers discovers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//    }
}
