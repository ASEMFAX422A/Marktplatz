package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Transactional
public interface UserReop extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password= :password, u.username = :username,u.profilePic = :profilePic WHERE u.id = :id")
    void updateUserById(@Param("id") Long id,
                        @Param("name") String newFirstName,
                        @Param("email") String newLastName,
                        @Param("password") String password,
                        @Param("username") String username,
                        @Param("profilePic") String profilePic);

    @Query("SELECT u FROM User u where u.username = :username")
    User findeByUsername(@Param("username") String username);

}
