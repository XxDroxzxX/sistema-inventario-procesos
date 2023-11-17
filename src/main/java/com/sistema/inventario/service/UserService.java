package com.sistema.inventario.service;

import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.UserRepository;
import com.sistema.inventario.util.ExceptionsConstants;
import com.sistema.inventario.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel createUser(UserModel userModelReq){
        Optional<UserModel> existingUserByEmail = userRepository.findByEmail(userModelReq.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(ExceptionsConstants.USER_EXISTE.getMessage());
        }
    
        Optional<UserModel> existingUserByDocument = userRepository.findByDocument(userModelReq.getDocument());
        if (existingUserByDocument.isPresent()) {
            throw new AlreadyExistsException(ExceptionsConstants.DOCUMENT_EXISTS.getMessage());
        }
    
        userModelReq.setPassword(passwordEncoder.encode(userModelReq.getPassword()));
        userModelReq.setRol(Rol.USER);
        return userRepository.save(userModelReq);
    }
    public UserModel getUserById(Long id){
        if(id == null)
            throw new RuntimeException(ExceptionsConstants.USER_VACIO.getMessage());
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionsConstants.USER_VACIO.getMessage()));
    }

  public List<UserModel> findAllUsers(){
        List<UserModel> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                                         .collect(Collectors.toList());
        if(users.isEmpty()){
            throw new NotFoundException(ExceptionsConstants.USERS_VACIOS.getMessage());
        }
    return users;   
    }

    public UserModel updateUser(Long id, UserModel userModelReq){
        Optional<UserModel> existingUser = userRepository.findByEmail(userModelReq.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new AlreadyExistsException(ExceptionsConstants.USER_EXISTE.getMessage());
        }
    
        Optional<UserModel> userToUpdateOpt = userRepository.findById(id);
        if (!userToUpdateOpt.isPresent()) {
            throw new NotFoundException(ExceptionsConstants.USER_VACIO.getMessage());
        }
    
        UserModel userToUpdate = userToUpdateOpt.get();
        userToUpdate.setEmail(userModelReq.getEmail());
        userToUpdate.setPassword(passwordEncoder.encode(userModelReq.getPassword()));
        userToUpdate.setRol(Rol.USER);
        return userRepository.save(userToUpdate);
    }

    public boolean deleteUser(Long id){
        UserModel userModelBd = this.getUserById(id);
        userRepository.deleteById(userModelBd.getId());
        return true;
    }

}
