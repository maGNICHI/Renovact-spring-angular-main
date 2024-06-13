package tn.esprit.foyeruniversiteeya.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Classe;
import tn.esprit.foyeruniversiteeya.services.IClasseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Classe")
public class ClasseController {


   IClasseService classeService;

    @PostMapping("/addclasse")
    Classe addClasse(@RequestBody Classe classe){
        return classeService.addClasse(classe);
    }

    @GetMapping("/Classe/{id}")
    Classe retriveClasse(@PathVariable long id){
        return classeService.getClasse(id);
    }

    @GetMapping("/Classe")
    List<Classe> retriveClasses(){
        return classeService.getAllClasse();
    }

    @DeleteMapping("/deleteClasse/{id}")
    void deleteClasse(@PathVariable long id){
        classeService.deleteClasse(id);
    }

    @PutMapping("/updateClasse")
    Classe updateBloc(@RequestBody Classe classe){

        return classeService.updateClasse(classe);
    }

    // Avancés

    @GetMapping("/ClasseAndDepartment")
    List<Object[]> getAllClassesWithDepartments(){
        return classeService.getAllClassesWithDepartments();
    }


    @GetMapping("/NomClasse/{nom}")
    List<Classe> getClassesByNom(@PathVariable String nom){
        return classeService.getClassesByNom(nom);
    }

    @GetMapping("/CountClasse")
    Long statNumberofClasses(){
        return classeService.statNumberofClasses();
    }

    @GetMapping("/orderclasseCapacity")
    List<Classe> getClasseOrderByCapacity(){
        return classeService.getClasseOrderByCapacity();
    }


    @GetMapping("/CountClasse/{etat}")
    Long statNumberofClassesDispo(@PathVariable boolean etat){
        return classeService.statNumberofClassesDispo(etat);
    }

    @GetMapping("/classesByDispo/{etat}")
    List<Classe> getClassesByAvailability(@PathVariable boolean etat){
        return classeService.getClassesByAvailability(etat);
    }

    @PostMapping("/addDepartmentWithDepartment/{idDepartment}")
    Classe addDepartmentClasse(@RequestBody Classe classe , @PathVariable Long idDepartment){
        return classeService.addDepartmentClasse(classe,idDepartment);
    }

    @GetMapping("/AvailableClasse")
    Double statNumberofClassesDispo(){
        return classeService.getPercentageAvailableClassrooms();
    }

    @GetMapping("/UnavailableClasse")
    Double statNumberofUnavailableClassesDispo(){
        return classeService.getPercentageUnavailableClassrooms();
    }






}
