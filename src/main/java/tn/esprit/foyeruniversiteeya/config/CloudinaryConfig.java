package tn.esprit.foyeruniversiteeya.config;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Configuration;
@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "dx7l2n0h6";
    private final String API_KEY = "924176283356352";
    private final String API_SECRET = "DWpL7d7tSbZi3TYmrqsZLnfl1CA";

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }
}
