package com.homeservices.service.partner;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.VerifyPartnerDTO;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.PartnerOrderDTO;
import com.homeservices.dto.response.PartnerResponseDTO;
import com.homeservices.dto.response.PartnerServiceDTO;


public interface PartnerService {
	
//	POST   /api/partners/register                → Register a new partner
	   ApiResponse addPartner(PartnerRequestDTO partner);   
	
//	GET    /api/partners/{id}                    → Get partner profile
	   PartnerResponseDTO getPartner(Long id);
//	PUT    /api/partners/{id}                    → Update partner profile
	   ApiResponse updatePartner(Long id,PartnerRequestDTO partner);
//	DELETE /api/partners/{id}                    → Soft-delete partner
	   ApiResponse deletePartner(Long id);
//
//	GET    /api/partners/{id}/orders             → Get partner’s assigned orders
	   List<PartnerOrderDTO> getPartnerOrders(Long id);
//	GET    /api/partners/{id}/earnings           → Get partner’s total earnings
	   Double getTotalEarning(Long id);
//	GET    /api/partners/{id}/services           → List services offered
	   List<PartnerServiceDTO> getPartnerServices(Long id);
//	PUT    /api/partners/{id}/verify             → Mark partner as verified
	   ApiResponse verifyPartner(Long id,VerifyPartnerDTO  verifypartnerDTO);
	 
	 

}
