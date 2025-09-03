package com.homeservices.controller.partner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.UpdatePartnerDTO;
import com.homeservices.dto.request.VerifyPartnerDTO;
import com.homeservices.service.partner.PartnerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/partner")
@AllArgsConstructor
@Validated

public class PartnerController {
	private final PartnerService partnerService;

	@GetMapping
	public ResponseEntity<?> getAllPartners() {
		return ResponseEntity.ok(partnerService.getAllPartners());
	}

	@GetMapping("/verified")
	public ResponseEntity<?> getByVerificationStatusTrue() {
		return ResponseEntity.ok(partnerService.getByVerificationStatusTrue());
	}

	@GetMapping("/unverified")
	public ResponseEntity<?> getByVerificationStatusFalse() {
		return ResponseEntity.ok(partnerService.getByVerificationStatusFalse());
	}

	@PostMapping("/register")
	public ResponseEntity<?> addPartner(@RequestBody PartnerRequestDTO partnerDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(partnerService.addPartner(partnerDTO));
	}

	@GetMapping("/{partnerId}")
	public ResponseEntity<?> getPartnerDetails(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartner(partnerId));
	}

	@PutMapping("/{partnerId}")
	public ResponseEntity<?> updatePartner(@PathVariable Long partnerId, @RequestBody UpdatePartnerDTO partnerDTO) {
		return ResponseEntity.ok(partnerService.updatePartner(partnerId, partnerDTO));
	}

	@DeleteMapping("/{partnerId}")
	public ResponseEntity<?> deletePartner(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.deletePartner(partnerId));
	}

	@GetMapping("/{partnerId}/bookings")
	public ResponseEntity<?> getPartnerBookings(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartnerBookings(partnerId));
	}

	@GetMapping("/{partnerId}/earnings")
	public ResponseEntity<?> getTotalEarning(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getTotalEarning(partnerId));
	}

	@GetMapping("/{partnerId}/services")
	public ResponseEntity<?> getPartnerServices(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartnerServices(partnerId));
	}

	@PutMapping("/{partnerId}/verify")
	public ResponseEntity<?> verifyPartner(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.verifyPartner(partnerId));
	}

	@PutMapping("/{partnerId}/bookings/{bookingId}")
	public ResponseEntity<?> assignBookingToPartner(@PathVariable Long partnerId, @PathVariable Long bookingId) {
		return ResponseEntity.ok(partnerService.assignBookingToPartner(partnerId, bookingId));
	}

	@PutMapping("/{partnerId}/bookings/{bookingId}/status/completed")
	public ResponseEntity<?> updateBookingStatusCompleted(@PathVariable Long partnerId, @PathVariable Long bookingId) {
		return ResponseEntity.ok(partnerService.updateBookingStatusCompleted(partnerId, bookingId));
	}
	
	@PutMapping("/{partnerId}/bookings/{bookingId}/status/inprogress")
	public ResponseEntity<?> updateBookingStatusInProgress(@PathVariable Long partnerId, @PathVariable Long bookingId) {
		return ResponseEntity.ok(partnerService.updateBookingStatusInProgress(partnerId, bookingId));
	}

}
