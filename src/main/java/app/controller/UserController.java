package app.controller;

import app.dto.User;
import app.repo.UserRepository;
import app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping(value = "/user/{id}", consumes = {"application/json"})
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping(value = "/user/{id}", consumes = {"application/json"})
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User _user = userRepository.findById(id).orElseThrow();
        _user.setInfo(user.getInfo());
        _user.setName(user.getName());
        userRepository.saveAndFlush(_user);
        return user;
    }
}

