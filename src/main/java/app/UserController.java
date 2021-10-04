package app;

import dto.UserDto;
import org.springframework.web.bind.annotation.*;
import temp.Lists;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return Lists.userDtoList;
    }

    @GetMapping("/user/{uuid}")
    public UserDto getUser(@PathVariable UUID uuid) {
        return Lists.userDtoList.stream().filter(it-> it.getUuid().equals(uuid)).findFirst().get();
    }

    @PostMapping(value = "/user", consumes = {"application/json"})
    public UserDto createUser(@RequestBody UserDto user) {
        UserDto newUser = new UserDto(UUID.randomUUID(), user.getName(), user.getInfo());
        Lists.userDtoList.add(newUser);
        return getUser(newUser.getUuid());
    }

    @PutMapping(value = "/user/{uuid}", consumes = {"application/json"})
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable UUID uuid) {
        UserDto originUser = getUser(uuid);
        UserDto updatedUser = new UserDto(originUser.getUuid(), user.getName(), user.getInfo());
        Lists.userDtoList.remove(originUser);
        Lists.userDtoList.add(updatedUser);
        return updatedUser;
    }
}

