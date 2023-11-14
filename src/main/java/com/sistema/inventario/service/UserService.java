package com.sistema.inventario.service;

import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(UserModel user){
        List<UserModel> existingUsers = userRepository.findByDocument(user.getDocument());
        if(!existingUsers.isEmpty()){
            throw new AlreadyExistsException("User  with name " + user.getFirstName()+" "+user.getLastName() +" and Document "+user.getDocument()+ " already exists");
        };
        return userRepository.save(user);
    }

    public UserModel getUserById(Long id){
        if( id==0){
            throw  new NotFoundException("User id is null");
        }
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw  new NotFoundException("User not found");
        }
        return  user.get();
    }


    public UserModel updateUser(UserModel user, Long id){
        if(!userRepository.existsById(id)){
            throw new NotFoundException("User not Found");
        }
        List<UserModel> existingUsers = userRepository.findByDocument(user.getDocument());
        if(!existingUsers.isEmpty()){
            throw new AlreadyExistsException("User  with name " + user.getFirstName()+" "+user.getLastName() +" and Document "+user.getDocument()+ " already exists");
        };
        UserModel userBd = userRepository.findById(id).get();
        userBd.setFirstName(user.getFirstName());
        userBd.setLastName(user.getLastName());
        userBd.setAddress(user.getAddress());
        userBd.setEmail(user.getEmail());
        userBd.setPhone(user.getPhone());
        return userRepository.save(userBd);
    }

    public Boolean deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }else{
            throw new NotFoundException("User not Found");
        }

    }

    public List<UserModel> findAllUsers(){
        List<UserModel> users = userRepository.findAll();
        if(users.isEmpty()){
            throw  new NotFoundException("User not found");

        }
        return users;
    }
}


