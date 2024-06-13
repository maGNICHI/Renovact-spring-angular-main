package tn.esprit.foyeruniversiteeya.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import lombok.AllArgsConstructor;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.foyeruniversiteeya.entities.*;
import tn.esprit.foyeruniversiteeya.repositories.*;

import javax.print.attribute.standard.Media;
import com.itextpdf.layout.Document;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import com.cloudinary.Cloudinary;

@Service
@AllArgsConstructor

public class UniversiteServiceImpl implements IUniversiteService{

    UniversiteRepository universiteRepository;
    DepartmentRepository departementRepository;
    FoyerRepository foyerRepository;
    VilleRepository villeRepository;
    ImagesRepository imagesRepository;
    Cloudinary cloudinary;



    @Override
    public List<Universite> getAllUniversites() {

        //   List<Universite>cc=universiteRepository.findAll();;

        return universiteRepository.findAll();
    }

    @Override
    public Page<Universite> getAllUniversites(Pageable pageable) {
        return  universiteRepository.findAll(pageable);
    }

    @Override
    public Universite getUniversite(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public List<Universite> getUniversiteByVille(long idVille) {
        Ville ville=villeRepository.findById(idVille).orElse(null);
        if (ville != null) {
            return universiteRepository.findByVille(ville);
        }
        return Collections.emptyList(); // Retourne une liste vide si la ville n'est pas trouvée.
    }


    @Override
    public void deleteUniversite(Long idUniversite)throws Exception  {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        List<Department> departements =universiteRepository.findDepartementByUniversite(idUniversite);
        for (Department departement : departements) {
            departement.setUniversite(null);
            // Either delete departement or update foreign key
        }

        universiteRepository.deleteById(idUniversite);
    }

    @Override
    //List<MultipartFile> files,
    public Universite updateUniversite(Universite universite, Long idVille, MultipartFile file) throws Exception{
        Optional<Universite> optionalUniversite = universiteRepository.findById(universite.getIdUniversite());
        Universite existingUniversite = optionalUniversite.get();        Ville ville=villeRepository.findById(idVille).orElse(null);
        universite.setVille(ville);
        if (file == null || file.isEmpty()) {
            universite.setLogo(existingUniversite.getLogo());
        } else {
            Images media = new Images();
            String url = cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            media.setImagenUrl(url);
            media.setName(file.getName());

            imagesRepository.save(media);
            Images existingPictures = existingUniversite.getLogo();
            imagesRepository.delete(existingPictures);
            universite.setLogo(media);
            String qrCodeText = universite.getNomUniversite() + " "
                    + universite.getMail() + " "

                    + "L email est : " + universite.getMail() + " Le numero est :(+216) "
                    + universite.getNumero();
            // Create a new PNG image
            int width = 500;
            int height = 250;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(pdfOutputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            // Add a title to the PDF
            document.add(new Paragraph("\n")); // add a blank line
            Paragraph title = new Paragraph(universite.getNomUniversite());
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n")); // add a blank line
            document.add(new Paragraph("\n")); // add a blank line
            // Add a description to the PDF
            Paragraph description = new Paragraph("L'e-mail de l'université est " + universite.getMail());
            description.setTextAlignment(TextAlignment.JUSTIFIED);
            document.add(description);
            document.add(new Paragraph("\n")); // add a blank line
            document.add(new Paragraph("\n")); // add a blank line
            // Add a description to the PDF
            Paragraph description1 = new Paragraph( "qui se situe : " + universite.getVille().getNom() + " a l'addresse :  " + universite.getAdresse() +  ".");
            description.setTextAlignment(TextAlignment.JUSTIFIED);
            document.add(description1);
            document.add(new Paragraph("\n")); // add a blank line
            document.add(new Paragraph("\n")); // add a blank line
            // Add the QR code image to the PDF
            Image qrCodePdfImage = new Image(ImageDataFactory.create(pngData));
            qrCodePdfImage.scaleToFit(200, 200);
            qrCodePdfImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(qrCodePdfImage);
            // Add other information to the PDF
            // Create the paragraph with the formatted date and time
            Image logo = new Image(ImageDataFactory.create(universite.getLogo().getImagenUrl()));
            logo.setWidth(75);
            logo.setHeight(50);
            logo.setFixedPosition(document.getLeftMargin()-20 , document.getPageEffectiveArea(document.getPdfDocument().getDefaultPageSize()).getTop() - 20);
            document.add(logo);
            // Close the PDF document
            document.close();
            byte[] pdfData = pdfOutputStream.toByteArray();
            // Save the PDF to a file
            String fileName = universite.getNomUniversite() + ".pdf";
            String filePath = "C:/Users/NAJIB/Desktop/integration ang/ESPRITUniversity/src/assets/"+ fileName;
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(pdfData);
            fos.flush();
            fos.close();
        }

        return  universiteRepository.save(universite);
    }



    @Override
    public Universite findUniversiteByName(String nomUniversite) {
        return universiteRepository.findUniversiteByNomUniversite(nomUniversite);
    }

    @Override
    public List<Department> getDepartementsByUniversite(Long idUniversite) {
        return universiteRepository.findDepartementByUniversite(idUniversite);
    }

    @Override
    public Long nbrDepartementsByUniversite(Long idUniversite) {
        return getDepartementsByUniversite(idUniversite).stream().count();

    }

    @Override
    public TreeSet<Universite> getUniversitiesSorted() {
        List<Universite> all = universiteRepository.findAll();
        TreeSet<Universite> collect = all.stream().collect(Collectors.toCollection(TreeSet::new));
        return  collect;
    }

    @Override
    public void assignUniversiteToDepartement(Long idUniversite, Long idDepartement) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Department departement = departementRepository.findById(idDepartement).orElse(null);
        /* universite.getDepartements().add(departement);*/
        universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversiteParLot(List<Long> ids) throws Exception {
        List<Universite> universites = universiteRepository.findByIds(ids);

        List<Department> departements =universiteRepository.findDepartementByIdUniversite(ids);
        for (Department departement : departements) {
            departement.setUniversite(null);
            // Either delete departement or update foreign key
        }

        if(universites.isEmpty()){
            throw  new Exception("liste vide ");
        }

        universiteRepository.deleteAll(universites);
    }

    @Override
    public  List<Filtre>  getDataByFiltre(long idVille, long idUniversite, long idDepartement) {
        List<Filtre> filters=  new ArrayList<>();
        Filtre filtre=new Filtre();
        if(idVille != 0 && idDepartement ==0){
            Ville ville=villeRepository.findById(idVille).orElse(null);
            if(idUniversite != 0){
                Universite universite=universiteRepository.findById(idUniversite).orElse(null);
                List<Department> depatms = getDepartementsByUniversite(idUniversite);
                List<Filtre> filtersByVille = depatms.stream().map(d -> {
                    Filtre filtr=new Filtre();
                    filtr.setNom(ville.getNom());
                    filtr.setNomDepartement(d.getNomDepartment());
                    filtr.setNomUniversite(d.getUniversite().getNomUniversite());
                    return filtr;
                }).toList();
                filters.addAll(filtersByVille);
                return  filters ;
            }

            List<Department> depatms = universiteRepository.findDepartementByUniversiteByVille(idVille);
            List<Filtre> filtersByVille = depatms.stream().map(d -> {
                Filtre filtr=new Filtre();
                filtr.setNom(ville.getNom());
                filtr.setNomDepartement(d.getNomDepartment());
                filtr.setNomUniversite(d.getUniversite().getNomUniversite());
                return filtr;
            }).toList();
            filters.addAll(filtersByVille);
            return  filters ;
        }

        Ville ville=villeRepository.findById(idVille).orElse(null);
        Universite universite=universiteRepository.findById(idUniversite).orElse(null);
        Department departement=departementRepository.findById(idDepartement).orElse(null);

        filtre.setNom(ville.getNom());
        filtre.setNomUniversite(universite.getNomUniversite());
        filtre.setNomDepartement(departement.getNomDepartment());
        filters.add(filtre);



        return filters;

    }
    @Override
    public Universite addUniversiteVille(Universite universite,Long idVille, MultipartFile file) throws Exception {
        Ville ville = villeRepository.findById(idVille).get();
        universite.setVille(ville);
//        universiteRepository.save(universite);
        Images image = new Images();
        if (file==null||file.isEmpty()){
            universite.setLogo(null);
            return universiteRepository.save(universite);
        }
        else {
            String url = cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            image.setImagenUrl(url);
            image.setName(file.getName());
        }
        imagesRepository.save(image);
        universite.setLogo(image);
        String qrCodeText = universite.getNomUniversite() + " "
                + universite.getMail() + " "

                + "L email est : " + universite.getMail() + " Le numero est :(+216) "
                + universite.getNumero();
        // Create a new PNG image
        int width = 500;
        int height = 250;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(pdfOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        // Add a title to the PDF
        document.add(new Paragraph("\n")); // add a blank line
        Paragraph title = new Paragraph(universite.getNomUniversite());
        title.setTextAlignment(TextAlignment.CENTER);
        document.add(title);
        document.add(new Paragraph("\n")); // add a blank line
        document.add(new Paragraph("\n")); // add a blank line
        // Add a description to the PDF
        Paragraph description = new Paragraph("L'e-mail de l'université est " + universite.getMail());
        description.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(description);
        document.add(new Paragraph("\n")); // add a blank line
        document.add(new Paragraph("\n")); // add a blank line
        // Add a description to the PDF
        Paragraph description1 = new Paragraph( "qui se situe : " + universite.getVille().getNom() + " a l'addresse :  " + universite.getAdresse() +  ".");
        description.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(description1);
        document.add(new Paragraph("\n")); // add a blank line
        document.add(new Paragraph("\n")); // add a blank line
        // Add the QR code image to the PDF
        Image qrCodePdfImage = new Image(ImageDataFactory.create(pngData));
        qrCodePdfImage.scaleToFit(200, 200);
        qrCodePdfImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(qrCodePdfImage);
        // Add other information to the PDF
        // Create the paragraph with the formatted date and time
        Image logo = new Image(ImageDataFactory.create(universite.getLogo().getImagenUrl()));
        logo.setWidth(75);
        logo.setHeight(50);
        logo.setFixedPosition(document.getLeftMargin()-20 , document.getPageEffectiveArea(document.getPdfDocument().getDefaultPageSize()).getTop() - 20);
        document.add(logo);
        // Close the PDF document
        document.close();
        byte[] pdfData = pdfOutputStream.toByteArray();
        // Save the PDF to a file
        String fileName = universite.getNomUniversite() + ".pdf";
        String filePath = "C:/Users/NAJIB/Desktop/integration ang/ESPRITUniversity/src/assets/"+ fileName;
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(pdfData);
        fos.flush();
        fos.close();
        // Create a new Pictures object with the PDF data
        return universiteRepository.save(universite);
    }
}
