package com.sistema.inventario.service;

import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//metodos
@Service
public class ItemService {

    @Autowired

    private ItemRepository itemRepository;
    public ItemModel createItemModel (ItemModel item){
        return itemRepository.save(item);

    }
    public ItemModel getItemModelById (Long id){
        return itemRepository.findById(id).get();
    }
    public ItemModel updateItemModel (ItemModel item,Long id){
        if(itemRepository.existsById(id)){
            ItemModel itemBd = itemRepository.findById(id).get();
            itemBd.setName(itemBd.getName());
            itemBd.setDescription(itemBd.getDescription());
            itemBd.setQuantity(itemBd.getQuantity());
            itemBd.setPrice(itemBd.getPrice());
            itemBd.setProvider(itemBd.getProvider());
            itemBd.setStatus(itemBd.getStatus());
            return itemRepository.save(itemBd);
        }
        return null;
    }
    public Boolean deleteItemById(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<ItemModel> findAllItems(){
        return (List<ItemModel>) itemRepository.findAll();
    }
}
