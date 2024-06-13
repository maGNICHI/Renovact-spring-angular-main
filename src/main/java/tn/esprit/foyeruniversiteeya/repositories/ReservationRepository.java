package tn.esprit.foyeruniversiteeya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.foyeruniversiteeya.entities.Reservation;


import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation>findByAnneeUniversitaireBetween(Date startDate, Date endDate);
    @Query(value = "SELECT * FROM reservation r JOIN chambre_reservation cr WHERE r.id_reservation = cr.reservation_id_reservation AND cr.chambre_id_chambre = :idChambre", nativeQuery = true)
    public Reservation findByChambre(@Param("idChambre") Long idChambre);
    @Query(value = "SELECT COUNT(*) FROM etudiant_reservations WHERE reservations_id_reservation = :ReservationId ",nativeQuery = true)
    public Long getNumberReservation(@Param("ReservationId") String ReservationId);

}
