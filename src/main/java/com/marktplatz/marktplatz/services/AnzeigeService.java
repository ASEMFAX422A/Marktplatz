package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.AnzeigeDto;
import com.marktplatz.marktplatz.DTOs.UserDto;
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

    public ResponseEntity<List<AnzeigeDto>> getAnzeigen(){
        return ResponseEntity.ok(new AnzeigeDto().AllAnzeigentoDto(anzeigeRepo.findAll()));
    }
    //Noch nicht fertig
    public ResponseEntity<List<T>> getAnzeigenByUserId(Long uId){
        return ResponseEntity.ok(new AnzeigeDto().AllAnzeigentoDto(anzeigeRepo.findAllAnzeigen(uId)));
    }
    public ResponseEntity<T> getAnzeigenById(Long id){
        Optional<T> AnzeigeOptional = anzeigeRepo.findById(id);
        if (AnzeigeOptional.isPresent()) {
            return (ResponseEntity<T>) ResponseEntity.ok(new AnzeigeDto().OpntionalAnzeigeDto(anzeigeRepo.findById(id)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<T> getAnzeigeByName(String username){
        if (username.isEmpty()) {return null;}
        return ResponseEntity.ok((T) new AnzeigeDto().anzeigeDto(anzeigeRepo.findeByName(username)));
    }
    public ResponseEntity<T> addAnzeige(Anzeige anzeige){
        if (anzeige.getName().isEmpty()&&new AnzeigeDto().anzeigeDto(anzeigeRepo.findeByName(anzeige.getName()))!=null) {
            return (ResponseEntity<T>) ResponseEntity.badRequest();
        }
        return (ResponseEntity<T>) ResponseEntity.ok(new AnzeigeDto().anzeigeDto((Anzeige) anzeigeRepo.save(anzeige)));
    }
    public void updateAnzeigeById(AnzeigeDto anzeige){
        anzeigeRepo.updateAnzeigeById(
                anzeige.getId(),
                anzeige.getDescription(),
                anzeige.getName(),
                anzeige.getImage(),
                anzeige.getPreis());}
    public void deleteAnzeige(Long id){anzeigeRepo.deleteById(id);}
}
