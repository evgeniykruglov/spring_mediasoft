package app;

import dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return List.of(
                new UserDto("testName1", "testInfo1"),
                new UserDto("testName2", "testInfo2"));
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable long id) {
        return new UserDto(id, "testName3", "testInfo3");
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto user) {
        return new UserDto(user.getName(), user.getInfo());
    }

    @PutMapping("/user/{id}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable long id) {
        return new UserDto(id, user.getName(), user.getInfo());
    }
}

