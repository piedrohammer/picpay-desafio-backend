package com.example.paraestudo.services;

import com.example.paraestudo.dtos.UserDTO;
import com.example.paraestudo.entities.User;
import com.example.paraestudo.enums.UserType;
import com.example.paraestudo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validationTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiênte");
        }
    }

    public User findById(Long id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO);
        this.saveUser(user);
        return user;
    }

    public void saveUser(User user){
       this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
