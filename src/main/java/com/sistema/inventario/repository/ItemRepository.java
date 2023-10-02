package com.sistema.inventario.repository;

import com.sistema.inventario.model.ItemModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository <ItemModel,Long> {
    //List<ItemModel>
}
