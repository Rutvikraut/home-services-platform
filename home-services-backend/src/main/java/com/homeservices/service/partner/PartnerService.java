package com.homeservices.service.partner;

import java.util.List;

import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.UpdatePartnerDTO;
import com.homeservices.dto.request.VerifyPartnerDTO;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.PartnerBookingDTO;
import com.homeservices.dto.response.PartnerResponseDTO;
import com.homeservices.dto.response.PartnerServiceDTO;

public interface PartnerService {

	ApiResponse addPartner(PartnerRequestDTO partner);

	PartnerResponseDTO getPartner(Long id);

	ApiResponse updatePartner(Long id, UpdatePartnerDTO partner);

	ApiResponse deletePartner(Long id);

	List<PartnerBookingDTO> getPartnerBookings(Long id);

	Double getTotalEarning(Long id);

	List<PartnerServiceDTO> getPartnerServices(Long id);

	ApiResponse verifyPartner(Long id);

	ApiResponse assignBookingToPartner(Long partnerId, Long bookingId);

	List<PartnerResponseDTO> getAllPartners();

	List<PartnerResponseDTO> getByVerificationStatusTrue();

	List<PartnerResponseDTO> getByVerificationStatusFalse();

	ApiResponse updateBookingStatusCompleted(Long partnerId, Long bookingId);

	ApiResponse updateBookingStatusInProgress(Long partnerId, Long bookingId);

}
