package tech.vladflore.sholia.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemApi.class)
class ItemApiTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<Item> argumentCaptor;

    @Test
    @DisplayName("fetch all items when no name parameter is given")
    void fetchAllItems() throws Exception {
        Item item1 = new Item();
        item1.setName("apples");

        Item item2 = new Item();
        item2.setName("bananas");
        when(itemRepository.findAll()).thenReturn(List.of(item1, item2));

        mockMvc.perform(get("/api/v1/items?name="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @DisplayName("fetch only items that contain the given name parameter")
    void fetchSpecificItems() throws Exception {
        Item item = new Item();
        item.setName("bananas");
        when(itemRepository.findByNameContainingIgnoreCase("bananas")).thenReturn(List.of(item));

        mockMvc.perform(get("/api/v1/items?name=bananas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    @DisplayName("should create item")
    void createItem() throws Exception {
        String requestBody = "{\n" +
                "  \"name\": \"apples\",\n" +
                "  \"quantity\": 1,\n" +
                "  \"measurement\": \"kg\",\n" +
                "  \"language\": \"en\",\n" +
                "  \"unit_price\": 1.99\n" +
                "}\n";

        Item savedItem = new Item();
        savedItem.setId(1L);
        savedItem.setName("apples");
        when(itemRepository.save(any(Item.class))).thenReturn(savedItem);

        mockMvc.perform(post("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("apples")));

        verify(itemRepository).save(argumentCaptor.capture());
        Item saveArgument = argumentCaptor.getValue();

        assertThat(saveArgument.getName()).isEqualTo("apples");
        assertThat(saveArgument.getId()).isNull();
    }
}
