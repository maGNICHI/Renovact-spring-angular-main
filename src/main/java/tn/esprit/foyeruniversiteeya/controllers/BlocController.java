package tn.esprit.foyeruniversiteeya.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Bloc;
import tn.esprit.foyeruniversiteeya.services.BlocServiceImpl;
import tn.esprit.foyeruniversiteeya.services.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Bloc")
public class BlocController {
    IBlocService blocService;
    @PostMapping("/addbloc/{idFoyer}")
    public Bloc addBloc(@RequestBody Bloc bloc, @PathVariable long idFoyer){
        return blocService.addBloc(bloc, idFoyer);
    }
    @GetMapping("/bloc/{id}")
    Bloc retrieveBloc(@PathVariable Long id){
        return blocService.getBloc(id);
    }
    @GetMapping("/bloc")
    List<Bloc> retrieveBlocs(){
        return blocService.getAllBlocs();
    }
    @DeleteMapping("/deletebloc/{id}")
    void deleteBloc(@PathVariable Long id){
        blocService.deleteBloc(id);
    }
    /* @PutMapping("/updatebloc")
     Bloc updateBloc(@PathVariable(value = "idBloc") Long idBloc,@RequestBody Bloc bloc){
         return blocService.updateBloc(idBloc,bloc);
     }

     */
    @PutMapping("/updatebloc")
    Bloc updateBloc(@RequestBody Bloc bloc){
        return blocService.updateBloc(bloc);
    }
}
