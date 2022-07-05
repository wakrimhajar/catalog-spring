package com.example.demo.web;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData){
        Optional<User> user = userRepository.findByUsernameAndPassword(userData.getUsername(), userData.getPassword());
        if(user.isPresent()){
            //if(user.get().getPassword().equals(userData.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body(user);
            //}else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'avez pas accés à cette application");
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'avez pas accés à cette application");

    }

}
