package tn.esprit.foyeruniversiteeya.services;

import tn.esprit.foyeruniversiteeya.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation reservation);
    Reservation getReservation(String idReservation);

    public List<Reservation> getAlReservations();
    void deleteReservation(String idReservation);

    Reservation updateReservation(Reservation reservation);
    /*Reservation ajouterReservation (Long idChambre, Long cinEtudiant) ;*/
    /*
    Reservation annulerReservation (Long cinEtudiant) ;*/
}
