package com.example.course_project_6.service;

import com.example.course_project_6.entity.User;
import com.example.course_project_6.entity.UserImage;
import com.example.course_project_6.repository.UserImageRepository;
import com.example.course_project_6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;


/*
 * Сервис-класс для работы с UserImageRepository
 */
@Service
@Slf4j
public class UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    public UserImageService(UserImageRepository userImageRepository, UserRepository userRepository) {
        this.userImageRepository = userImageRepository;
        this.userRepository = userRepository;
    }

    /*
     * Метод сохраняет картинку пользователя в базу
     */
    public void saveUserImage(MultipartFile imageData, User user) {
        deleteLatestUserImage(user);

        UserImage userImage = new UserImage();
        userImage.setUser(user);
        try {
            userImage.setImageData(imageData.getBytes());
            userImageRepository.save(userImage);
        } catch (IOException e) {
            log.info(e.toString());
        }
    }

    public void deleteLatestUserImage(User user) {
        try {
            userImageRepository.delete(userImageRepository.findFirstByUserId(user.getId()).get());
        } catch (Exception e) {
            log.info(e.toString());
        }
    }

    /*
     * Метод возвращает картинку по ID пользователя
     */
    public String getUserImage(Long userId) {
        Optional<UserImage> userImageOptional = userImageRepository.findFirstByUserId(userId);
        if (userImageOptional.isPresent()) {
            String base64ImageData = Base64.getEncoder().encodeToString(userImageOptional.get().getImageData());
            return base64ImageData;
        } else {
            return null;
        }
    }
}
