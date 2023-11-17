package com.sistema.inventario.service;

import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.AddressModel;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    public AddressModel createAddress(AddressModel address, Long idUser){
        UserModel user = userService.getUserById(idUser);
        address.setUser(user);
        return addressRepository.save(address);
    }

    public AddressModel disabledAddress(Long id){
        if(id==0){
            throw  new NotFoundException("Address id is null");
        }
        Optional<AddressModel> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw  new NotFoundException("Address not found");
        }
        address.get().setStatus(Boolean.FALSE);
        return addressRepository.save(address.get());
    }

    public  AddressModel getAddressById(Long id){
        Optional<AddressModel> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw  new NotFoundException("Address not found");
        }
        return address.get();
    }

    public  List<AddressModel> getAllAddress(){
        List<AddressModel> address = (List<AddressModel>) addressRepository.findAll();
        if(address.isEmpty()){
            throw  new NotFoundException("Addresses not found");
        }
        return address;
    }
}
