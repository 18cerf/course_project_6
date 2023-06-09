package com.example.course_project_6.repository;

import com.example.course_project_6.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);

    List<User> findAll();

    boolean existsUserByUsername(String username);

    List<User> findUsersByIdNotIn(List<Long> users);

    List<User> findByUsernameContaining(String partialNickname);

    List<User> findByNameContaining(String partialNickname);

    List<User> findByLastnameContaining(String partialNickname);

    List<User> findByPhoneNumberContaining(String partialNickname);
}
