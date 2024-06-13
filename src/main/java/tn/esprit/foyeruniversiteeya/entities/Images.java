package tn.esprit.foyeruniversiteeya.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Images implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imagenUrl;
    private String codeImage;
    public Images(String name, String imagenUrl, String imagencode) {

        this.name = name;
        this.imagenUrl = imagenUrl;
        this.codeImage = imagencode;
    }
}
