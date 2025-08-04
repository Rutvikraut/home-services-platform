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

//	POST   /api/partners/register                → Register a new partner
	@PostMapping
	public ResponseEntity<?> addPartner(@RequestBody PartnerRequestDTO partnerDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(partnerService.addPartner(partnerDTO));
	}

//	GET    /api/partners/{id}                    → Get partner profile
	@GetMapping("/{partnerId}")
	public ResponseEntity<?> getPartnerDetails(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartner(partnerId));
	}

//	PUT    /api/partners/{id}                    → Update partner profile
	@PutMapping("/{partnerId}")
	public ResponseEntity<?> updatePartner(@PathVariable Long partnerId, @RequestBody UpdatePartnerDTO partnerDTO) {
		return ResponseEntity.ok(partnerService.updatePartner(partnerId, partnerDTO));
	}

//	DELETE /api/partners/{id}                    → Soft-delete partner
	@DeleteMapping("/{partnerId}")
	public ResponseEntity<?> deletePartner(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.deletePartner(partnerId));
	}

//
//	GET    /api/partners/{id}/orders             → Get partner’s assigned orders
	@GetMapping("/{partnerId}/orders")
	public ResponseEntity<?> getPartnerOrders(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartnerOrders(partnerId));
	}

//	GET    /api/partners/{id}/earnings           → Get partner’s total earnings
	@GetMapping("/{partnerId}/earnings")
	public ResponseEntity<?> getTotalEarning(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getTotalEarning(partnerId));
	}

//	GET    /api/partners/{id}/services           → List services offered
	@GetMapping("/{partnerId}/services")
	public ResponseEntity<?> getPartnerServices(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.getPartnerServices(partnerId));
	}

//	PUT    /api/partners/{id}/verify             → Mark partner as verified
	@PutMapping("/{partnerId}/verify")
	public ResponseEntity<?> verifyPartner(@PathVariable Long partnerId) {
		return ResponseEntity.ok(partnerService.verifyPartner(partnerId));
	}
}
