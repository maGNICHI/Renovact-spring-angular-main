package tn.esprit.foyeruniversiteeya.services;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.foyeruniversiteeya.entities.Images;
import tn.esprit.foyeruniversiteeya.repositories.ImagesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ImagesRepository imagesRepository;
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }
    public List<String> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            Images images = new Images();
            String url = cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            urls.add(url);
            images.setImagenUrl(url);
            imagesRepository.save(images);
        }
        return urls;
    }
}
