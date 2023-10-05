package com.sistema.inventario.service;

import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ItemModel updateItemModel(ItemModel item, Long id) {
        Optional<ItemModel> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            ItemModel updatedItem = existingItem.get();
            updatedItem.setNombre(item.getNombre());
            updatedItem.setDescripcion(item.getDescripcion());
            updatedItem.setCantidad(item.getCantidad());
            updatedItem.setPrecio(item.getPrecio());
            updatedItem.setProveedor(item.getProveedor());
            updatedItem.setEstado(item.getEstado());
            return itemRepository.save(updatedItem);
        } else {
            return null;
        }
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
