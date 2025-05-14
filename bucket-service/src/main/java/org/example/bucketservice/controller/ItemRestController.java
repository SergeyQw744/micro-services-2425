package org.example.bucketservice.controller;

import org.example.bucketservice.dto.ItemDto;
import org.example.bucketservice.model.AnalyticalFrame;
import org.example.bucketservice.model.Item;
import org.example.bucketservice.model.OperationEnum;
import org.example.bucketservice.model.RequestEnum;
import org.example.bucketservice.service.AnalyticService;
import org.example.bucketservice.service.ItemService;
import org.example.bucketservice.util.DtoConverterForItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

    private final ItemService itemService;
    private final DtoConverterForItem dtoConverterForItem;
    private final AnalyticService analyticService;

    @Autowired
    public ItemRestController(ItemService itemService,
                              DtoConverterForItem dtoConverterForItem,
                              AnalyticService analyticService) {
        this.itemService = itemService;
        this.dtoConverterForItem = dtoConverterForItem;
        this.analyticService = analyticService;
    }

    @GetMapping("/all")
    @Transactional
    public List<ItemDto> findAll(){
        analyticService.save(new AnalyticalFrame(RequestEnum.POST,
                OperationEnum.SAVE,
                "ALL",
                0.0)
        );
        return itemService.findAll()
                .stream()
                .map(this.dtoConverterForItem::convertToItemDto)
                .toList();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated ItemDto itemDto,
                                  BindingResult bindingResult, UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()){
            if (bindingResult instanceof BindException e){
                throw e;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Item item = itemService.save(dtoConverterForItem.convertToItem(itemDto));
            analyticService.save(new AnalyticalFrame(RequestEnum.POST,
                    OperationEnum.SAVE,
                    item.getProvider(),
                    item.getPrice())
            );
            return ResponseEntity.created(uriBuilder
                            .replacePath("/api/items")
                            .build()
                            .toUri())
                    .body(item);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        Item item = itemService.findById(id);
        itemService.delete(id);
        analyticService.save(new AnalyticalFrame(RequestEnum.DELETE,
                OperationEnum.DELETE,
                item.getProvider(),
                item.getPrice())
        );
        return ResponseEntity.noContent()
                .build();
    }
}