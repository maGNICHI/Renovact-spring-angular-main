package tn.esprit.foyeruniversiteeya.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyeruniversiteeya.entities.*;
import tn.esprit.foyeruniversiteeya.repositories.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;
    ChambreRepository chambreRepository;
    UserRepository userRepository;
    BlocRepository blocRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public List<Reservation> getAlReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);

    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        Reservation ch = reservationRepository.findById(reservation.getIdReservation()).orElse(null);
        if (ch != null)
            reservationRepository.save(reservation);
        return ch;
    }

  /*  @Override
    public Reservation ajouterReservation(Long idChambre, Long cinEtudiant) {
       /* Chambre chambre = chambreRepository.findById(idChambre).orElseThrow(null);
        Etudiant etudiant = userRepository.findByCin(cinEtudiant);
        Bloc bloc = blocRepository.findById(chambre.getBloc().getIdBloc()).orElseThrow();
        Reservation chambreReserve = null;
        chambreReserve = reservationRepository.findByChambre(idChambre);
        Reservation res = reservationRepository.findById(String.valueOf(chambre.getNumeroChambre()) + "-" + bloc.getIdBloc() + "-" + LocalDate.now().getYear()).orElse(null);
        if (res == null) {
            res = new Reservation();
            res.setAnneeUniversitaire(LocalDate.of(LocalDate.now().getYear(), 8, 15));
            res.setIdReservation(
                    String.valueOf(chambre.getNumeroChambre()) + "-" + bloc.getIdBloc() + "-" + LocalDate.now().getYear()
            );
        }
        if (chambreReserve == null || chambreReserve.getAnneeUniversitaire().getYear() < LocalDate.now().getYear()) {
            if (chambre.getTypeC() == TypeChambre.SIMPLE) {
                res.setEstValide(false);
            } else {
                res.setEstValide(true);
            }
            chambre.getReservations().add(res);
            if (res.getEtudiants() == null) {
                Set<Etudiant> etudiants = new HashSet<>();
                etudiants.add(etudiant);
                res.setEtudiants(etudiants);
            } else {
                res.getEtudiants().add(etudiant);
            }
            chambreRepository.save(chambre);
        } else if (res.getAnneeUniversitaire().getYear() < LocalDate.now().getYear()) {
            if (chambre.getTypeC() == TypeChambre.SIMPLE) {
                res.setEstValide(false);
            } else {
                res.setEstValide(true);
            }
            reservationRepository.save(res);
        } else {
            if (res.isEstValide()) {
                if (reservationRepository.getNumberReservation(res.getIdReservation()) == 1 && chambre.getTypeC() == TypeChambre.DOUBLE) {
                    res.setEstValide(false);
                    reservationRepository.save(res);
                } else if (reservationRepository.getNumberReservation(res.getIdReservation()) == 1 && chambre.getTypeC() == TypeChambre.TRIPLE) {
                    res.setEstValide(true);
                    reservationRepository.save(res);
                } else {
                    res.setEstValide(false);
                }
                if (res.getEtudiants() == null) {
                    Set<Etudiant> etudiants = new HashSet<>();
                    etudiants.add(etudiant);
                    res.setEtudiants(etudiants);
                } else {
                    res.getEtudiants().add(etudiant);
                }
                reservationRepository.save(res);
            }
        }
        return res;
    }*/

}
