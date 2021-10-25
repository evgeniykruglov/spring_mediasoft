package app.repo;

import app.dto.User;

import java.util.List;

public interface UserRepositoryInterface {
    List<User> findUsersBy(Long id, String email, String name);
}
