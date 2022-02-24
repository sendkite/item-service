package com.hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        String itemName = "macbook";
        Integer price = 50000;
        Integer quantity = 7;

        Item testItem = new Item(itemName, price, quantity);

        // when
        Item savedItem = itemRepository.save(testItem);

        // then
        Item findItem = itemRepository.findById(testItem.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("시디즈 의자", 150000, 3);
        Item item2 = new Item("조던1", 190000, 1);

        // when
        itemRepository.save(item1);
        itemRepository.save(item2);

        // then
        List<Item> allItems = itemRepository.findAll();

        assertThat(allItems.size()).isEqualTo(2);
        assertThat(allItems).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("나이키 신발", 5000, 1);
        Item saveditem = itemRepository.save(item);

        // when
        Item newItem = new Item("아디다스", 8000, 2);
        itemRepository.update(saveditem.getId(), newItem);

        // then
        Item result = itemRepository.findById(saveditem.getId());

        assertThat(result.getItemName()).isEqualTo(newItem.getItemName());
        assertThat(result.getPrice()).isEqualTo(newItem.getPrice());
        assertThat(result.getQuantity()).isEqualTo(newItem.getQuantity());
    }
}