package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.Roles.Role;
import com.marktplatz.marktplatz.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Transactional
public interface UserReop extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password= :password, u.username = :username,u.profilePic = :profilePic, u.role = :role WHERE u.id = :id")
    void updateUserById(@Param("id") Long id,
                        @Param("name") String name,
                        @Param("email") String email,
                        @Param("password") String password,
                        @Param("username") String username,
                        @Param("profilePic") String profilePic,
                        @Param("role")Role role
    );
    @Query("SELECT u FROM User u where u.username = :username")
    User findeByUsername(@Param("username") String username);

}
