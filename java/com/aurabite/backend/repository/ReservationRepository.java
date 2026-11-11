package com.aurabite.backend.repository;

import com.aurabite.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByBookingRef(String bookingRef);
    List<Reservation> findByReservationDate(LocalDate date);
    List<Reservation> findByCustomerEmail(String email);
    List<Reservation> findByTableIdAndReservationDate(Long tableId, LocalDate date);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.reservationDate = CURRENT_DATE")
    long countTodayReservations();

    @Query("SELECT COALESCE(SUM(r.totalPreorderAmount), 0.0) FROM Reservation r WHERE r.status <> 'CANCELLED'")
    double calculateTotalRevenue();
}
