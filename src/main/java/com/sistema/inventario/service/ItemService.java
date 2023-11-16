package com.sistema.inventario.service;

import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
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
    public ItemModel createItemModel (ItemModel item){
        if (itemRepository.findByName(item.getNombre()).isPresent()){
            throw new AlreadyExistsException("Item with name " + item.getNombre()+" already exists");
        }
        return itemRepository.save(item);
    }
    public ItemModel getItemModelById (Long id){
        Optional<ItemModel> item = itemRepository.findById(id);
        if (item.isEmpty()){
            throw new NotFoundException("Item not found");
        }
        return item.get();
    }

    public ItemModel updateItemModel(ItemModel item, Long id) {
        if (!itemRepository.existsById(id)){
            throw new NotFoundException("Item not found");
        }
        Optional<ItemModel> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent() && !existingItem.get().getId().equals(id)) {
            throw new AlreadyExistsException("Item with name " + item.getNombre() + "already exists");
        }

        ItemModel updatedItem = existingItem.get();
        updatedItem.setNombre(item.getNombre());
        updatedItem.setDescripcion(item.getDescripcion());
        updatedItem.setCantidad(item.getCantidad());
        updatedItem.setPrecio(item.getPrecio());
        updatedItem.setProveedor(item.getProveedor());
        updatedItem.setEstado(item.getEstado());

        return itemRepository.save(updatedItem);
    }

    public Boolean deleteItemById(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return true;
        }
        else{
            throw new NotFoundException("Item not found");
        }

    }
    public List<ItemModel> findAllItems(){
        List<ItemModel> items = (List<ItemModel>) itemRepository.findAll();
        if (items.isEmpty()){
            throw new NotFoundException("No items found");
        }
        return items;
    }
}
