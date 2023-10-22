package com.marktplatz.marktplatz.repository;

import com.marktplatz.marktplatz.entity.Anzeige;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnzeigeRepo extends JpaRepository<Anzeige,Long> {
}
