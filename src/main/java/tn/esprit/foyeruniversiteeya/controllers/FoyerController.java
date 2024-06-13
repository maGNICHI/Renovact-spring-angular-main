package tn.esprit.foyeruniversiteeya.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.foyeruniversiteeya.entities.Foyer;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.services.FileStorageService;
import tn.esprit.foyeruniversiteeya.services.FoyerExcelExporter;
import tn.esprit.foyeruniversiteeya.services.IFoyerService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Foyer")

public class FoyerController {
    private final IFoyerService foyerService;
    private final FileStorageService fileStorageService;

    @PostMapping("/addfoyer")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }




    @GetMapping("/foyer/{id}")
    Foyer retrieveFoyer(@PathVariable Long id) {
        return foyerService.getFoyer(id);
    }

    @GetMapping("/foyer")
    List<Foyer> retrieveFoyers() {
        return foyerService.getAllFoyers();
    }

    @DeleteMapping("/deletefoyer/{id}")
    void deleteFoyer(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }

    @PutMapping("/updatefoyer")
    Foyer updateFoyer(@RequestBody Foyer foyer){
        return foyerService.updateFoyer(foyer);
    }

    /*Foyer updateFoyer(@PathVariable(value = "idFoyer") Long id,@RequestBody Foyer foyer){
         return foyerService.updateFoyer(id,foyer);
     }*/
   /* @PostMapping("/addfoyer")
    Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer,@PathVariable long idUniversity ){

    }*/
    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=foyer" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Foyer> listOfFoyers = foyerService.getTheListStudent();
        FoyerExcelExporter generator = new FoyerExcelExporter(listOfFoyers);
        generator.generateExcelFile(response);
    }

}
