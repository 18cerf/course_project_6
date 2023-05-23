package com.example.course_project_6.repository;

import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserImageRepository extends CrudRepository<UserImage, Long> {

    /*
     *
     * Возвращает фото пользователя
     *
     */
    Optional<UserImage> findFirstByUserId(Long userId);

    /*
     *
     * Удаляет все фото по id изображения
     *
     */
    void deleteById(Long id);


    /*
     *
     * Удаляет все фото по id пользователя
     *
     */
    void deleteAllByUserId(Long userId);
}
