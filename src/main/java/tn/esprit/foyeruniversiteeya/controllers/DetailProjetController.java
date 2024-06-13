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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.foyeruniversiteeya.repositories.DetailProjetRepository;
import tn.esprit.foyeruniversiteeya.services.IDetailProjetService;
import tn.esprit.foyeruniversiteeya.entities.DetailProjet;
import tn.esprit.foyeruniversiteeya.services.IProjetService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/DetailProjet")
public class DetailProjetController {
    IDetailProjetService detailprojetService;

    IProjetService projetService;
    @PostMapping("/addDetailProjet")
    DetailProjet addDetailProjet(@RequestBody DetailProjet detailprojet){
        return detailprojetService.addDetailProjet(detailprojet);
    }
    @GetMapping("/DetailProjet/{id}")
    DetailProjet retrieveDetailProjet(@PathVariable Long id){
        return detailprojetService.getDetailProjet(id);
    }
    @GetMapping("/DetailProjet")
    List<DetailProjet> retrieveDetailProjet(){
        return detailprojetService.getAllDetailProjet();
    }
    @DeleteMapping("/DetailProjet/{id}")
    void deleteDetailProjet(@PathVariable Long id){
        detailprojetService.deleteDetailProjet(id);
    }
    @PutMapping("/updateDetailProjet")
    DetailProjet updateDetailProjet(@RequestBody DetailProjet detailprojet){
        return detailprojetService.updateDetailProjet(detailprojet);
    }


    @Autowired
    private DetailProjetRepository detailProjetRepository;

    @GetMapping("/detailsProjet/pdf")
    public ResponseEntity<byte[]> downloadDetailsProjetPdf() throws IOException, DocumentException {
        List<DetailProjet> detailsProjetList = detailProjetRepository.findAll(); // Use instance, not static method

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // création d'un flux de sortie pour stocker le PDF généré
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(3); // Modification : création d'une table avec 3 colonnes

        // ajout des en-têtes de colonne à la table
        Stream.of("ID", "Description Detail", "Technologie")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY); // Modification : Use BaseColor
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        // ajout des données de détails de projet à la table
        for (DetailProjet detailProjet : detailsProjetList) {
            table.addCell(String.valueOf(detailProjet.getIdDetail()));
            table.addCell(detailProjet.getDescriptionDetail());
            table.addCell(detailProjet.getTechnologie());
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "detailsProjet.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<DetailProjet> filterDetailProjets(

            @RequestParam(name = "descriptionDetail", required = false) String descriptionDetail,
            @RequestParam(name = "technologie", required = false) String technologie) {
        return detailprojetService.filterDetailProjets(descriptionDetail, technologie);
    }


    //@RequestMapping("/api/export")





    //@GetMapping("/excel")
    // @Autowired
    //private ExcelExporter excelExporter;
    //public void exportToExcel(HttpServletResponse response) throws IOException {
    //List<DetailProjet> detailProjets = detailprojetService.getAllDetailProjet(); //
    // excelExporter.exportToExcel(response, detailProjets);
    // }
    private final IDetailProjetService detailProjetService;

    //*@PostMapping("/affecterProjet/{id}")
    //DetailProjet addAffecterProjet(@RequestBody DetailProjet detailProjet, @PathVariable long id) {
    //  return detailProjetService.affecterProjet(detailProjet, id);
    // }
}
