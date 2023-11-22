package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.AnzeigeDto;
import com.marktplatz.marktplatz.DTOs.UserAnzeigeDto;
import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.entity.UserAnzeige;
import com.marktplatz.marktplatz.repository.AnzeigeRepo;
import com.marktplatz.marktplatz.repository.UserAnzeigeRepository;
import com.marktplatz.marktplatz.repository.UserReop;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnzeigeService {

    private final AnzeigeRepo anzeigeRepo;
    private final UserAnzeigeRepository userAnzeigeRepository;
    private final UserReop userReop;

    @Autowired
    public AnzeigeService(AnzeigeRepo anzeigeRepo, UserAnzeigeRepository userAnzeigeRepository, UserReop userReop) {
        this.anzeigeRepo = anzeigeRepo;
        this.userAnzeigeRepository = userAnzeigeRepository;
        this.userReop = userReop;
    }

    public ResponseEntity<List<AnzeigeDto>> getAnzeigen(){
        return ResponseEntity.ok(new AnzeigeDto().AllAnzeigentoDto(anzeigeRepo.findAll()));
    }

    //TODO: die methode gibt zurück alle anzeigen vom User. muss noch angepasst werden.

    public ResponseEntity<List<UserAnzeige>> getAnzeigenByUserId(Long userId) {
        return ResponseEntity.ok(userAnzeigeRepository.findAll());
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

    public ResponseEntity<AnzeigeDto> addAnzeige(Anzeige anzeige,Long userId){
        AnzeigeDto anzeigeDto = new AnzeigeDto().anzeigeDto((Anzeige) anzeigeRepo.save(anzeige));
        addUserAnzeige(anzeige,userId);
         return ResponseEntity.ok(anzeigeDto);
    }

    public void addUserAnzeige(Anzeige anzeige,Long userId){
        User user= new User().toUser(new UserDto().OpntionaluserDto(userReop.findById(userId)));
        UserAnzeige userAnzeige=new UserAnzeige(user,anzeige);
        user.getAnzeigen().add(userAnzeige);
        userAnzeigeRepository.save(userAnzeige);
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
