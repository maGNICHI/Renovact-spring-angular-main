package tn.esprit.foyeruniversiteeya.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyeruniversiteeya.entities.Reservation;
import tn.esprit.foyeruniversiteeya.services.ReservationServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {
    ReservationServiceImpl reservationService;
    @PostMapping("/addreservation")
    Reservation addReservation(@RequestBody Reservation reservation){ return reservationService.addReservation(reservation);
    }
    @GetMapping("/reservation/{id}")
    Reservation retriveReservation(@PathVariable String id){
        return reservationService.getReservation(id);
    }

    @GetMapping("/reservation")
    List<Reservation> retriveReservations(){
        return reservationService.getAlReservations();
    }

    @DeleteMapping("/reservation/{id}")
    void deleteReservation(@PathVariable String id){ reservationService.deleteReservation(id);
    }

    @PutMapping("/reservation")
    Reservation updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }
    /*@PostMapping("/reserer/{idChambre}/{cinEtudiant}")
    public Reservation reserverchambre(@PathVariable long idChambre ,@PathVariable long cinEtudiant){
        return reservationService.ajouterReservation(idChambre,cinEtudiant);
    }*/
}
