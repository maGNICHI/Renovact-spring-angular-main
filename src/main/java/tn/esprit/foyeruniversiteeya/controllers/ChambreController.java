package tn.esprit.foyeruniversiteeya.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Chambre;
import tn.esprit.foyeruniversiteeya.entities.TypeChambre;
import tn.esprit.foyeruniversiteeya.services.ChambreServiceImpl;
import tn.esprit.foyeruniversiteeya.services.IChambreService;


import java.util.List;
@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/chambre")
@AllArgsConstructor
public class ChambreController {
    ChambreServiceImpl chambreService;
    @PostMapping("/chambre/ajout")
    Chambre addChambre(@RequestBody Chambre chambre){
        return chambreService.addChambre(chambre);
    }

   @PostMapping("/chambre/ajout/{id}")
    Chambre addChambreParBloc(@RequestBody Chambre chambre , @PathVariable long id){
        return chambreService.affecterChambreABloc(chambre,id);
    }
    @GetMapping("/chambre/{id}")
    Chambre retriveChambre(@PathVariable long id){
        return chambreService.getChambre(id);
    }

    @GetMapping("/chambre")
    List<Chambre> retriveChambres(){
        return chambreService.getAllChambre();
    }

    @DeleteMapping("/deletechambre/{id}")
    void deleteChambre(@PathVariable long id){
        chambreService.deleteChambre(id);
    }

    @PutMapping("/updatechambre")
    Chambre updateChambre(@RequestBody Chambre chambre){
        return chambreService.updateChambre(chambre);
    }
    /*@PutMapping("/chambre")
    public ResponseEntity<Chambre> updateChambre(@RequestBody Chambre chambre) {
        Chambre updatedChambre = chambreService.updateChambre(chambre);

        if (updatedChambre != null) {
            return new ResponseEntity<>(updatedChambre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    @GetMapping("/getChambresParBlocEtType/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable Long idBloc, @PathVariable TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }
}
