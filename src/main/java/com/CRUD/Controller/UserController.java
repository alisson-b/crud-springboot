package com.CRUD.Controller;

import com.CRUD.Entity.User;
import com.CRUD.Repository.UserRepository;
import com.CRUD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(usuario -> {
                    usuario.setName(user.getName());
                    usuario.setCpf(user.getCpf());
                    usuario.setEmail(user.getEmail());
                    User updated = userRepository.save(usuario);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }





}
