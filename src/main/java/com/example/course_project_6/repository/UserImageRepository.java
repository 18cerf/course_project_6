package com.example.course_project_6.repository;

import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserImageRepository extends CrudRepository<UserImage, Long> {

    Optional<UserImage> findFirstByUserId(Long userId);
    void deleteById(Long id);
    void deleteAllByUserId(Long userId);
}
