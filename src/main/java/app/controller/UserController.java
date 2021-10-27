package app.controller;

import app.consts.ExceptionMessages;
import app.dto.User;
import app.repo.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import static app.consts.ExceptionMessages.USER_NOT_FOUND;

@RestController
@RequestMapping("/user")
public class UserController implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Throwable.class)
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
        if (user.getUsername() == null || !user.getUsername().equals(""))
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
            _user.setUsername(user.getUsername());
            userRepository.saveAndFlush(_user);
            return user;
        } catch (NoSuchElementException e) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }

    @GetMapping("/findByEmail")
    public Iterable<User> getUsersByEmail(@RequestParam("email") String email) {
        if (email == null || email.equals(""))
            throw new IllegalArgumentException(ExceptionMessages.EMAIL_SHOULD_NOT_BE_NULL_OR_EMPTY);
        else if (!email.contains("@."))
            throw new IllegalArgumentException(ExceptionMessages.EMAIL_IS_NOT_VALID);
        return userRepository.findUsersByUserEmail(email);
    }

    @GetMapping("/findByName/partialMatch")
    public Iterable<User> getUsersByName(@RequestParam("name") String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException(ExceptionMessages.USERNAME_SHOULD_NOT_BE_NULL_OR_EMPTY);
        return userRepository.findUsersByUserName(name);
    }

    @GetMapping("/findBy")
    public Iterable<User> getUsersBy(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email) {

        return userRepository.findUsersBy(id, name, email);
    }

    @Override
    @GetMapping("/findByName/fullMatch")
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
