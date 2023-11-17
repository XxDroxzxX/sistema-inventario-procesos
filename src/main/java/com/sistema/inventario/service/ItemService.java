package com.sistema.inventario.service;

import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemModel createItem (ItemModel item){
        if (itemRepository.findByName(item.getName()).isPresent()) {
            throw new AlreadyExistsException("Item with name " + item.getName() + " already exists");
        }
        return itemRepository.save(item);
    }

    public ItemModel getItemById(Long id){
        Optional<ItemModel> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new NotFoundException("Item not found");
        }
        return item.get();
    }

    public ItemModel updateItem(ItemModel item, Long id){
        if(!itemRepository.existsById(id)){
            throw new NotFoundException("Item not found");
        }
        Optional<ItemModel> existingItemOptional = itemRepository.findByName(item.getName());
        if (existingItemOptional.isPresent() && !existingItemOptional.get().getId().equals(id)) {
            throw new AlreadyExistsException("Item with name " + item.getName() + " already exists");
        }
        ItemModel itemDB = itemRepository.findById(id).get();
        itemDB.setName(item.getName());
        itemDB.setDescription(item.getDescription());
        itemDB.setQuantity(item.getQuantity());
        itemDB.setPrice(item.getPrice());
        itemDB.setProvider(item.getProvider());
        itemDB.setStatus(item.getStatus());
        return itemRepository.save(itemDB);
    }
    public Boolean deleteItemById(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return true;
        }else{
            throw new NotFoundException("Item not found");
        }

    }

    public List<ItemModel> findAllItems(){
        List<ItemModel> items = itemRepository.findAll();
        if(items.isEmpty()){
            throw new NotFoundException("No items found");
        }
        return items;
    }

}
