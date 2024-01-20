package com.service.rentacar.rest;

import com.service.rentacar.domain.reservation.ReservationService;
import com.service.rentacar.domain.reservation.dto.Reservation;
import com.service.rentacar.domain.reservation.model.ReservationStatus;
import com.service.rentacar.rest.response.ReservationResponse;
import com.service.rentacar.rest.response.ReservationsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
public class ReservationRestController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(ReservationResponse.of(this.reservationService.save(reservation)));
    }

    @PutMapping
    public ResponseEntity<ReservationResponse> updateReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(ReservationResponse.of(this.reservationService.save(reservation)));
    }

    @PatchMapping("/{reservationId}/status/{status}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long reservationId, @PathVariable ReservationStatus status) {
        this.reservationService.updateReservationStatus(reservationId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(ReservationResponse.of(this.reservationService.getReservation(reservationId)));
    }

    @GetMapping("/my-self")
    public ResponseEntity<ReservationsResponse> getProfileReservations() {
        return ResponseEntity.ok(ReservationsResponse.of(this.reservationService.getProfileReservations()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('GET_ALL_RESERVATIONS')")
    public ResponseEntity<ReservationsResponse> getAllReservations() {
        return ResponseEntity.ok(ReservationsResponse.of(this.reservationService.getAllReservations()));
    }

}
