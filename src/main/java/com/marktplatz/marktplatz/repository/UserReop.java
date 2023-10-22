package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface UserReop extends JpaRepository<User,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password= :password  WHERE u.id = :id")
    void updateUserById(@Param("id") Long id, @Param("name") String newFirstName, @Param("email") String newLastName, @Param("password") String password);
}
