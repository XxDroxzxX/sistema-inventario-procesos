package com.sistema.inventario.repository;

import com.sistema.inventario.model.ItemModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//interface
public interface ItemRepository extends CrudRepository <ItemModel,Long> {
    Optional<ItemModel> findByName(String name);
}
