package com.rhms.hms_backend.Repositories;

import com.rhms.hms_backend.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    @Query("SELECT u FROM Users u WHERE u.user_index = :user_index")
    Optional<Users> findByIndex(String user_index);




    @Query(nativeQuery = true, value = "SELECT * FROM warden_view")
    List<Object[]>  findAllWarden();



    @Query(nativeQuery = true, value = "SELECT * FROM student_view")
    List<Object[]> findStudentsData();







    List<Users> findAll();


}
