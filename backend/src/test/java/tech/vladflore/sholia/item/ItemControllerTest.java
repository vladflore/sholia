package tech.vladflore.sholia.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ItemController.class)
class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<ItemDto> itemDtoArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @ParameterizedTest
    @DisplayName("should fetch items when name parameter has following values:")
    @EmptySource
    @ValueSource(strings = {"item name"})
    void fetchAllItems(String name) throws Exception {
        mockMvc.perform(get("/api/v1/items?name=" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(itemService, times(1)).findItems(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualTo(name);
    }

    @Test
    @DisplayName("should fetch items when name parameter is missing")
    void fetchAllItems() throws Exception {
        mockMvc.perform(get("/api/v1/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(itemService, times(1)).findItems(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isNull();
    }

    @Test
    @DisplayName("should create item")
    void createItem() throws Exception {
        String requestBody = "{\n" +
                "  \"name\": \"apples\",\n" +
                "  \"quantity\": 1,\n" +
                "  \"measurement\": \"kg\",\n" +
                "  \"language\": \"en\",\n" +
                "  \"price_per_quantity\": 1.99,\n" +
                "  \"shop\": \"shop\",\n" +
                "  \"notes\": \"notes\",\n" +
                "  \"currency\": \"euro\"\n" +
                "}\n";

        ItemDto savedItem = new ItemDto();
        savedItem.setId(1L);
        savedItem.setName("apples");
        when(itemService.save(any(ItemDto.class))).thenReturn(savedItem);

        mockMvc.perform(post("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("apples")));

        verify(itemService, times(1)).save(itemDtoArgumentCaptor.capture());
        assertThat(itemDtoArgumentCaptor.getValue().getName()).isEqualTo(savedItem.getName());
    }
}
