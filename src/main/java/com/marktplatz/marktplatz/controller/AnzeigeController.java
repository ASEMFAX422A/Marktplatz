package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.AnzeigeDto;
import com.marktplatz.marktplatz.entity.Anzeige;
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

    @CrossOrigin
    @GetMapping("/getAll")
    public ResponseEntity<List<AnzeigeDto>> getAnzeigen(){return  ResponseEntity.ok(anzeigeService.getAnzeigen().getBody());}
    @CrossOrigin
    @GetMapping("/getAll/{uId}")// Noch nicht fertig
    public ResponseEntity<AnzeigeDto> getAnzeigen(@PathVariable Long uId){return  ResponseEntity.ok((AnzeigeDto) anzeigeService.getAnzeigenByUserId(uId).getBody());}
    @CrossOrigin
    @GetMapping("/getAnzeige/{id}")
    public ResponseEntity<AnzeigeDto> getAnzeigenById(@PathVariable Long id){return ResponseEntity.ok( anzeigeService.getAnzeigenById(id).getBody());}
    @CrossOrigin
    @GetMapping("/getAnzeigeByName/{name}")
    public ResponseEntity<AnzeigeDto> getAnzeigeByName(@PathVariable String name){return ResponseEntity.ok(anzeigeService.getAnzeigeByName(name).getBody());}
    @CrossOrigin
    @PostMapping("/addAnzeige")
    public ResponseEntity<AnzeigeDto> addAnzeige(@RequestBody Anzeige anzeige){return ResponseEntity.ok(anzeigeService.addAnzeige(anzeige).getBody());}
    @CrossOrigin
    @PutMapping("/updateAnzeige")
    public void updateAnzeige(@RequestBody AnzeigeDto anzeige){
        anzeigeService.updateAnzeigeById(anzeige);
    }
    @CrossOrigin
    @DeleteMapping("/deleteAnzeige/{id}")
    public void deleteAnzeige(@PathVariable Long id){
        anzeigeService.deleteAnzeige(id);
    }

}
