package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String login);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users set academic_degree = ?2, academic_title = ?3, birthday = ?4, date_update = now(), email = ?5, img_url = ?6, job_title = ?7, last_name = ?8, name = ?9, patronymic = ?10, orcid = ?11, phone = ?12, spin_code = ?13, is_state = ?14, department_id = ?15 where users.id = ?1", nativeQuery = true)
    void updateUserDataById(long userId, String academicDegree, String academicTitle, Date birthday, String email, String imgUrl, String jobTitle, String lastName, String name, String patronymic, String orcid, String phone, String spinCode, boolean isState, long departmentId);

    // получение всех пользователей кафедры кроме зав. кафедры | Запрос для модератора
    @Query(value = "select * from users u inner join user_roles ur on ur.user_id = u.id where u.department_id = ?1 and ur.role_id = 4 order by u.id desc", nativeQuery = true)
    List<User> findAllUsersDepartmentNoModerByDepartmentId(long departmentId);

    // получение всех пользователей кафедры | Запрос для админов, модератора и пользователя
    @Query(value = "select * from users u inner join user_roles ur on ur.user_id = u.id where u.department_id = ?1 and (ur.role_id = 3 or ur.role_id = 4) and u.is_active = true order by u.id desc", nativeQuery = true)
    List<User> findAllUsersDepartmentByDepartmentId(long departmentId);

    // получение всех пользователей факультета | Запрос для админов
    @Query(value = "select * from users u inner join user_roles ur on ur.user_id = u.id inner join departments d on u.department_id = d.id where d.faculty_id = ?1 and (ur.role_id = 3 or ur.role_id = 4) order by u.id desc", nativeQuery = true)
    List<User> findAllUsersFacultyByFacultyId(long facultyId);

    // получение всех пользователей института кроме админов | Запрос для админов
    @Query(value = "select * from users u inner join user_roles ur on u.id = ur.user_id where ur.role_id = 3 or ur.role_id = 4 order by u.id desc", nativeQuery = true)
    List<User> findAllUsersNoAdmins();

    // получение всех пользователей института кроме тех. админов | Запрос для тех. админов
    @Query(value = "select * from users u inner join user_roles ur on u.id = ur.user_id where ur.role_id <> 1 order by u.id desc", nativeQuery = true)
    List<User> findAllUsersNoSupAdmins();

    // получение всех пользователей института кроме текущего аккаунта тех. админа | Запрос для тех. админов
    @Query(value = "select * from users u where u.id <> ?1  order by u.id desc", nativeQuery = true)
    List<User> findAllUsersNoCurrentSupAdmins(long userId);
}
