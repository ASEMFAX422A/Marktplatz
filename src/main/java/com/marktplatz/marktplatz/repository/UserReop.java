package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface UserReop extends JpaRepository<User,Long> {
    // Define a method to update a user by ID
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password= :password  WHERE u.id = :id")
    Optional<User> updateUserById(@Param("id") Long id, @Param("name") String newFirstName, @Param("email") String newLastName, @Param("password") String password);
}
