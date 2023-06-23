# Курсовой проект по предмету "Сетевое программирование"

---

## Задача:
Разработать личную страницу пользователя. При этом реализовать систему входа и 
регистрации. При реализации использовать Сервлеты.  После входа в систему отображать 
главную страницу, на которой необходимо выводить различную информацию о 
пользователе, включая фотографию, ФИО, увлечения, ссылки на друзей, статистику 
посещений и прочее. Иметь возможность поиска зарегистрированных пользователей по 
разным критериям (ФИО, увлечения и прочее) и приглашать их в друзья. Всю 
информацию о пользователях сохранять в БД и иметь возможность редактирования. При 
регистрации производить проверку на наличие зарегистрированного пользователя с таким 
именем. Производить проверку длины имени пользователя и пароля (имя пользователя 
должно содержать не менее 5 символов, пароль должен включать не менее 6 символов). 
Для зарегистрированных пользователей вести счетчик посещений, который необходимо 
отображать на главной странице.

---

## Используемые технологии:

* ### Spring Boot
* ### PostgresSQL
* ### Spring Security
* ### Spring JPA 
* ### Spring MVC 
* ### Thymeleaf
* ### Spring Data JPA

---

## Примеры работы программы
* ## Окно авторизации 
 ![image](https://github.com/18cerf/course_project_1/assets/99914331/b525ca98-3f97-4d8e-bef5-75289a17fd6a)
 
---
 
* ## Окно регистрации:
 ![image](https://github.com/18cerf/course_project_1/assets/99914331/91a0c005-3f4f-4454-b333-8b14e6777914)
 
 ---

* ## После корректной авторизации происходит переход на главную страницу
![image](https://github.com/18cerf/course_project_1/assets/99914331/1d7083f8-e4c2-452f-a18f-943e705f90dd)

---

* ## Страница изменения данных пользователя
 ![image](https://github.com/18cerf/course_project_1/assets/99914331/39949aa4-5dc5-4065-9679-57f79ba67f0c)

 ---

* ## Страница ставок
 ![image](https://github.com/18cerf/course_project_1/assets/99914331/6c33e289-ff93-4a15-bc61-a3bdebcf0558)

 ---

* После внесения ставки сумма на балансе резервируется и отображается в таблице «Ставки в игре»
* После нажатия кнопки «Рассчитать!» генерируется результат события случайным образом
* После нажатия кнопки «Новые ставки!» генерируются новые ставки с уникальным ID
* При попытке поставить сумму больше, чем есть на балансе у пользователя генерируется ошибка

---

* ## Структура БД:
![image](https://github.com/18cerf/course_project_1/assets/99914331/7a8d2b1c-7c35-429e-81c2-9c96af41fd2a)

---


