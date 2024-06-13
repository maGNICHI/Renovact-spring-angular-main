package tn.esprit.foyeruniversiteeya.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Equipe;
import tn.esprit.foyeruniversiteeya.services.EquipeServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Equipe")
public class EquipeController {
    EquipeServiceImp equipeServiceImp;

    @PostMapping("/addEquipe")
    Equipe addEquipe(@RequestBody Equipe equipe){
        return equipeServiceImp.addEquipe(equipe);
    }
    @GetMapping("/equipe/{id}")
    Equipe retrieveEquipe(@PathVariable int id){
        return equipeServiceImp.getEquipe(id);
    }
    @GetMapping("/equipe")
    List<Equipe> retrieveEquipes(){
        return equipeServiceImp.getAllEquipes();
    }
    @DeleteMapping("/equipe/{id}")
    void deleteEquipe(@PathVariable int id){
        equipeServiceImp.deleteEquipe(id);
    }
    @PutMapping("/updateEquipe")
    Equipe updateEquipe(@RequestBody Equipe equipe){
        return equipeServiceImp.updateEquipe(equipe);
    }
}
