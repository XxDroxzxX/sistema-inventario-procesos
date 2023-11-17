package com.sistema.inventario.repository;


import com.sistema.inventario.model.AddressModel;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressModel,Long> {
}
