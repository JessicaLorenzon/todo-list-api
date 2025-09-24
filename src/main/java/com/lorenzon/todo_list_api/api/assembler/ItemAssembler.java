package com.lorenzon.todo_list_api.api.assembler;

import com.lorenzon.todo_list_api.api.model.ItemModel;
import com.lorenzon.todo_list_api.api.model.input.ItemInput;
import com.lorenzon.todo_list_api.domain.model.Item;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ItemAssembler {

    private final ModelMapper modelMapper;

    public Item toEntity(ItemInput itemInput) {
        return modelMapper.map(itemInput, Item.class);
    }

    public ItemModel toModel(Item item) {
        return modelMapper.map(item, ItemModel.class);
    }

    public List<ItemModel> toCollection(List<Item> items) {
        return items.stream().map(this::toModel).toList();
    }
}
