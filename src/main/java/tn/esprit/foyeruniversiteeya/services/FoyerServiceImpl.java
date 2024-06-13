package tn.esprit.foyeruniversiteeya.services;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.foyeruniversiteeya.entities.Foyer;
import tn.esprit.foyeruniversiteeya.entities.Images;
import tn.esprit.foyeruniversiteeya.entities.Universite;
import tn.esprit.foyeruniversiteeya.repositories.BlocRepository;
import tn.esprit.foyeruniversiteeya.repositories.FoyerRepository;
import tn.esprit.foyeruniversiteeya.repositories.ImagesRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoyerServiceImpl implements IFoyerService{
    FoyerRepository foyerRepository;

    BlocRepository blocRepository;
    Cloudinary cloudinary;
    ImagesRepository imagesRepository;
    @Override
    public List < Foyer > getTheListStudent() {
        return foyerRepository.findAll();
    }

    @Autowired
    public FoyerServiceImpl(FoyerRepository foyerRepository,BlocRepository blocRepository) {
        this.foyerRepository = foyerRepository;
        this.blocRepository=blocRepository;
    }

    /*@Override
    public Foyer addFoyer(Foyer foyer, MultipartFile file) throws Exception {
        Images image = new Images();

        if (file == null || file.isEmpty()) {
            foyer.setImage(null);
            return foyerRepository.save(foyer);
        } else {
            String url = cloudinary.uploader()
                    .upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            image.setImagenUrl(url);
            image.setName(file.getOriginalFilename());

            // Save the Images entity
            imagesRepository.save(image);
        }

        // Associate the image with the foyer
        foyer.setImage(image);

        // Save the Foyer entity
        return foyerRepository.save(foyer);


    }*/

    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);


    }

    @Override
    public List<Foyer> getAllFoyers() {
        return (List<Foyer>) foyerRepository.findAll();
    }

    @Override
    public Foyer getFoyer(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void deleteFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }


    /*@Override
    public Foyer updateFoyer(Foyer foyer , MultipartFile file) throws Exception{
        Optional<Foyer> optionalFoyer = foyerRepository.findById(foyer.getIdFoyer());
        Foyer existingFoyer = optionalFoyer.get();

        if (file == null || file.isEmpty()) {
            foyer.setImage(existingFoyer.getImage());
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
            Images existingPictures = existingFoyer.getImage();
            imagesRepository.delete(existingPictures);
            foyer.setImage(media);
        }
        return foyerRepository.save(foyer);}*/
    @Override
    public Foyer updateFoyer(Foyer foyer ){
        return foyerRepository.save(foyer);}
}
