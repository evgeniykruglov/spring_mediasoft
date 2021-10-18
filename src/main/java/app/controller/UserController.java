package app.controller;

import app.consts.ExceptionMessages;
import app.dto.User;
import app.repo.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import static app.consts.ExceptionMessages.USER_NOT_FOUND;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Retryable(IllegalArgumentException.class)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) throws NotFoundException {
        if (id < 1) throw new IllegalArgumentException(ExceptionMessages.ID_SHOULD_NOT_BE_ZERO_OR_NEGATIVE);
        Optional<User> user = userRepository.findById(id);
        if (userRepository.findById(id).isPresent()) {
            return user;
        } else {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }


    @PostMapping(value = "", consumes = {"application/json"})
    public User createUser(@RequestBody User user) {
        if (user.getName() == null || !user.getName().equals(""))
            throw new IllegalArgumentException(ExceptionMessages.USERNAME_SHOULD_NOT_BE_NULL_OR_EMPTY);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping(value = "/user/{id}", consumes = {"application/json"})
    public void deleteUser(@PathVariable Long id) {
        if (id < 1) throw new IllegalArgumentException(ExceptionMessages.ID_SHOULD_NOT_BE_ZERO_OR_NEGATIVE);
        userRepository.deleteById(id);
    }

    @PutMapping(value = "/user/{id}", consumes = {"application/json"})
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws NotFoundException {
        if (id < 1) throw new IllegalArgumentException(ExceptionMessages.ID_SHOULD_NOT_BE_ZERO_OR_NEGATIVE);
        try {
            User _user = userRepository.findById(id).orElseThrow();
            _user.setInfo(user.getInfo());
            _user.setName(user.getName());
            userRepository.saveAndFlush(_user);
            return user;
        } catch (NoSuchElementException e) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }
}

