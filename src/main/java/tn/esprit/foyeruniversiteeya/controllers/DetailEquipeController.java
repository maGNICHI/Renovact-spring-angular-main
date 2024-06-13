package tn.esprit.foyeruniversiteeya.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.DetailEquipe;
import tn.esprit.foyeruniversiteeya.entities.Equipe;
import tn.esprit.foyeruniversiteeya.services.DetailEquipeServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/DetailEquipe")
public class DetailEquipeController {
    DetailEquipeServiceImp detailEquipeServiceImp;

    @PostMapping("/addDetailEquipe")
    DetailEquipe addEquipe(@RequestBody DetailEquipe detailEquipe){
        return detailEquipeServiceImp.addDetailEquipe(detailEquipe);
    }
    @GetMapping("/detailEquipe/{id}")
    DetailEquipe retrieveEquipe(@PathVariable int id){
        return detailEquipeServiceImp.getDetailEquipe(id);
    }
    @GetMapping("/detailEquipe")
    List<DetailEquipe> retrieveEquipes(){
        return detailEquipeServiceImp.getAllDetailEquipes();
    }
    @DeleteMapping("/detailEquipe/{id}")
    void deleteEquipe(@PathVariable int id){
        detailEquipeServiceImp.deleteDetailEquipe(id);
    }
    @PutMapping("/updateDetailEquipe")
    DetailEquipe updateEquipe(@RequestBody DetailEquipe detailEquipe){
        return detailEquipeServiceImp.updateDetailEquipe(detailEquipe);
    }

}
