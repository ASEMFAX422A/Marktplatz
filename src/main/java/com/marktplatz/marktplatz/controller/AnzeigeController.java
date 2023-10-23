package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.services.AnzeigeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Controller
@RequestMapping(value = "/anzeige", method = RequestMethod.POST)
public class AnzeigeController<T> {
    @Autowired
    AnzeigeService anzeigeService;

    @GetMapping("/getAll")
    public ResponseEntity<T> getAnzeigen(){return  ResponseEntity.ok((T) anzeigeService.getAnzeigen().getBody());}

    @GetMapping("/getAll/{uId}")// Noch nicht fertig
    public ResponseEntity<T> getAnzeigen(@PathVariable Long uId){return  ResponseEntity.ok((T) anzeigeService.getAnzeigenByUserId(uId).getBody());}
    @GetMapping("/getAnzeige/{id}")
    public ResponseEntity<T> getAnzeigenById(@PathVariable Long id){return ResponseEntity.ok((T) anzeigeService.getAnzeigenById(id).getBody());}
    @GetMapping("/getAnzeigeByName/{name}")
    public ResponseEntity<T> getAnzeigeByName(@PathVariable String name){return ResponseEntity.ok((T)anzeigeService.getAnzeigeByName(name).getBody());}
    @PostMapping("/addAnzeige")
    public ResponseEntity<T> addAnzeige(@RequestBody Anzeige anzeige){return ResponseEntity.ok((T)anzeigeService.addAnzeige(anzeige).getBody());}
    @PutMapping("/updateAnzeige")
    public void updateAnzeige(@RequestBody Anzeige anzeige){
        anzeigeService.updateAnzeigeById(anzeige);
    }
    @DeleteMapping("/deleteAnzeige")
    public void deleteAnzeige(@RequestBody Anzeige anzeige){
        anzeigeService.deleteAnzeige(anzeige);
    }

}
