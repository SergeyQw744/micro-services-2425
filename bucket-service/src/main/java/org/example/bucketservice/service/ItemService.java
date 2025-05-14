package org.example.bucketservice.service;

import org.example.bucketservice.dao.ItemDao;
import org.example.bucketservice.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<Item> findAll(){
        return itemDao.findAll();
    }

    public Item save(Item item){
        return itemDao.saveItem(item);
    }

    public void delete(int id){
        itemDao.delete(id);
    }

    public Item findById(int id){
        return itemDao.findById(id);
    }
}
