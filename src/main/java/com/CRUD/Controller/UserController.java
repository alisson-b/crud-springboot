package com.CRUD.Controller;

import com.CRUD.Entity.User;
import com.CRUD.Repository.UserRepository;
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
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return userRepository.findById(id)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userRepository.findById(id).map( usuario -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
         }).orElse(ResponseEntity.notFound().build());
    }





}
