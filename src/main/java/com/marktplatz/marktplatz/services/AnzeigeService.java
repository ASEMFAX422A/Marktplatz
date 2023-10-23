package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.repository.AnzeigeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnzeigeService<T> {

    @Autowired
    AnzeigeRepo anzeigeRepo;

    public ResponseEntity<List<T>> getAnzeigen(){
        return ResponseEntity.ok(anzeigeRepo.findAll());
    }
    //Noch nicht fertig
    public ResponseEntity<List<T>> getAnzeigenByUserId(Long uId){
        return ResponseEntity.ok(anzeigeRepo.findAllAnzeigen(uId));
    }
    public ResponseEntity<T> getAnzeigenById(Long id){
        Optional<T> AnzeigeOptional = anzeigeRepo.findById(id);
        if (AnzeigeOptional.isPresent()) {
            T anzeige = AnzeigeOptional.get();
            return ResponseEntity.ok(anzeige);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<T> getAnzeigeByName(String username){
        if (username == null) {
            return null;
        }
        return ResponseEntity.ok((T) anzeigeRepo.findeByName(username));
    }
    public ResponseEntity<T> addAnzeige(Anzeige anzeige){
        if (anzeige.getName()==null) {
            return null;
        }
        return ResponseEntity.ok((T) anzeigeRepo.save(anzeige));
    }
    public void updateAnzeigeById(Anzeige anzeige){
        anzeigeRepo.updateAnzeigeById(anzeige.getId(),anzeige.getDescription(),
                anzeige.getName(),anzeige.getImage(),anzeige.getPreis());}
    public void deleteAnzeige(Long id){anzeigeRepo.deleteById(id);}
}
