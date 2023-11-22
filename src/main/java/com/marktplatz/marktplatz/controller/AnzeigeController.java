package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.AnzeigeDto;
import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.entity.UserAnzeige;
import com.marktplatz.marktplatz.services.AnzeigeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@Controller
@RequestMapping(value = "/api/v1/anzeige", method = RequestMethod.POST)
@CrossOrigin
public class AnzeigeController {
    @Autowired
    AnzeigeService anzeigeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AnzeigeDto>> getAnzeigen(){return  ResponseEntity.ok(anzeigeService.getAnzeigen().getBody());}
    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<List<UserAnzeige>> getAnzeigenByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(anzeigeService.getAnzeigenByUserId(userId).getBody());
    }
    @GetMapping("/getAnzeige/{id}")
    public ResponseEntity<AnzeigeDto> getAnzeigenById(@PathVariable Long id){return ResponseEntity.ok( anzeigeService.getAnzeigenById(id).getBody());}

    @GetMapping("/getAnzeigeByName/{name}")
    public ResponseEntity<AnzeigeDto> getAnzeigeByName(@PathVariable String name){return ResponseEntity.ok(anzeigeService.getAnzeigeByName(name).getBody());}

    @PostMapping("/addAnzeige/{userId}")
    public ResponseEntity<AnzeigeDto> addAnzeige(@RequestBody Anzeige anzeige, @PathVariable Long userId) {
        return ResponseEntity.ok(anzeigeService.addAnzeige(anzeige,userId).getBody());}

    @PutMapping("/updateAnzeige")
    public void updateAnzeige(@RequestBody AnzeigeDto anzeige){
        anzeigeService.updateAnzeigeById(anzeige);
    }

    @DeleteMapping("/deleteAnzeige/{id}")
    public void deleteAnzeige(@PathVariable Long id){
        anzeigeService.deleteAnzeige(id);
    }

}
