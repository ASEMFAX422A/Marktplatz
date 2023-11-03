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
    public ResponseEntity<List<AnzeigeDto>> getAnzeigenByUserId(Long uId){
        return ResponseEntity.ok(new AnzeigeDto().AllAnzeigentoDto(anzeigeRepo.findAllAnzeigen(uId)));
    }
    public ResponseEntity<AnzeigeDto> getAnzeigenById(Long id){
        Optional<AnzeigeDto> AnzeigeOptional = anzeigeRepo.findById(id);
        if (AnzeigeOptional.isPresent()) {
            return ResponseEntity.ok(new AnzeigeDto().OpntionalAnzeigeDto(anzeigeRepo.findById(id)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<AnzeigeDto> getAnzeigeByName(String username){
        if (username.isEmpty()) {return null;}
        return ResponseEntity.ok(new AnzeigeDto().anzeigeDto(anzeigeRepo.findeByName(username)));
    }
    public ResponseEntity<AnzeigeDto> addAnzeige(Anzeige anzeige){
        if (anzeige.getName().isEmpty()&&new AnzeigeDto().anzeigeDto(anzeigeRepo.findeByName(anzeige.getName()))!=null) {
            return (ResponseEntity<AnzeigeDto>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(new AnzeigeDto().anzeigeDto((Anzeige) anzeigeRepo.save(anzeige)));
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
