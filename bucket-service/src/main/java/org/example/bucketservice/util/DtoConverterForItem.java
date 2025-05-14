package org.example.bucketservice.util;

import org.example.bucketservice.dto.ItemDto;
import org.example.bucketservice.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverterForItem {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverterForItem(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ItemDto convertToItemDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }

    public Item convertToItem(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }
}
