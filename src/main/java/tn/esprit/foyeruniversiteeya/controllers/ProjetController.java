package tn.esprit.foyeruniversiteeya.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import tn.esprit.foyeruniversiteeya.entities.Projet;
import tn.esprit.foyeruniversiteeya.entities.Type;
import tn.esprit.foyeruniversiteeya.repositories.ProjetRepository;
import tn.esprit.foyeruniversiteeya.services.IProjetService;
import tn.esprit.foyeruniversiteeya.services.IProjetService;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/Projet")

public class ProjetController {
    IProjetService projetService;

    @PostMapping("/addProjet/{idDetail}")
    Projet addProjetDetailProjet(@RequestBody Projet projet, @PathVariable long idDetail) {
        return projetService.addProjetDetailProjet(projet,idDetail);
    }


    @GetMapping("/Projet/{id}")
    Projet retrieveProjet(@PathVariable Long id){
        return projetService.getProjet(id);
    }
    @GetMapping("/Projet")
    List<Projet> retrieveProjet(){
        return projetService.getAllProjet();
    }
    @DeleteMapping("/Projet/{id}")
    void deleteProjet(@PathVariable Long id){
        projetService.deleteProjet(id);
    }
    @PutMapping("/updateProjet")
    Projet updateProjet(@RequestBody Projet projet){
        return projetService.updateProjet(projet);
    }


    @Autowired
    private ProjetRepository projetRepository;

    @GetMapping("/projets/pdf")
    public ResponseEntity<byte[]> downloadProjetsPdf() throws IOException, DocumentException {
        List<Projet> projetsList = projetRepository.findAll(); // Use instance, not static method

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // création d'un flux de sortie pour stocker le PDF généré
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(5); // Modification : création d'une table avec 5 colonnes (corresponding to the fields in Projet entity)


        Stream.of("ID", "Nom Projet", "Durée Projet", "Type Projet", "Date Debut")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY); // Modification : Use BaseColor
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });


        for (Projet projet : projetsList) {
            table.addCell(String.valueOf(projet.getIdProjet()));
            table.addCell(projet.getNomProjet());
            table.addCell(projet.getDureProjet());
            table.addCell(projet.getTypeProjet().toString()); // Assuming Type is an enum
            table.addCell(projet.getDateDebutP().toString()); // You might need to format the date appropriately
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "projets.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

}
