package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.entity.Anzeige;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface AnzeigeRepo<T> extends JpaRepository<Anzeige,Long> {

    @Modifying
    @Query("UPDATE Anzeige a SET a.name = :name, a.image = :image, a.description = :description, a.preis = :preis WHERE a.id = :id")
    void updateAnzeigeById(@Param("id") Long id, @Param("description") String description, @Param("name") String name, @Param("image") byte[] image, @Param("preis") double preis);



    @Query("SELECT a FROM Anzeige a where a.name = :name")
    Anzeige findeByName(@Param("name") String name);



    @Query("SELECT a FROM Anzeige a, User u, UserAnzeige ua WHERE u.id = ua.user.id AND a.id = ua.anzeige.id AND u.id = :uId AND a.id = :aId")
    List<Anzeige> findAllUserAnzeigen(@Param("uId") Long uId, @Param("aId") Long aId);




}
