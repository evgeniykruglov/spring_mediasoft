package app;

import dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

