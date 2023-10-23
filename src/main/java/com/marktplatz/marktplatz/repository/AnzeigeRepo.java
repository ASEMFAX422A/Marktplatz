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
    void updateAnzeigeById(@Param("id") Long id, @Param("description") String description, @Param("name") String name, @Param("image") String image, @Param("preis") double preis);


    @Modifying
    @Query("SELECT a FROM Anzeige a where a.name = :name")
    Anzeige findeByName(@Param("name") String name);

    @Modifying//noch nicht fertig
    @Query("SELECT a.name,a.description,a.image,a.preis FROM Anzeige a,User u,UserAnzeige ua where u.id = ua.user.id and a.id= ua.anzeige.id and u.id= : uId")
    List<Anzeige> findAllAnzeigen(@Param("uId") Long uId);
}
