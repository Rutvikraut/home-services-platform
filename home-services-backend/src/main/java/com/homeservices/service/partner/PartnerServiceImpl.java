package com.homeservices.service.partner;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.CategoryRepository;
import com.homeservices.dao.PartnerRepository;
import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.UpdatePartnerDTO;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.PartnerOrderDTO;
import com.homeservices.dto.response.PartnerResponseDTO;
import com.homeservices.dto.response.PartnerServiceDTO;
import com.homeservices.entities.Category;
import com.homeservices.entities.Partner;
import com.homeservices.entities.PartnerAddress;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private final PartnerRepository partnerRepository;
	private final CategoryRepository categoryRepository;
	private ModelMapper mapper;

	@Override
	public ApiResponse addPartner(PartnerRequestDTO partnerreq) {

		if (partnerRepository.existsByEmail(partnerreq.getEmail())) {
			return new ApiResponse("Email Already Registered");
		}

		if (partnerRepository.existsByPhoneNumber(partnerreq.getPhoneNumber())) {
			return new ApiResponse("Phone Number Already Registered");
		}

		Partner partner = mapper.map(partnerreq, Partner.class);

		return new ApiResponse("Partner Added with Id " + partnerRepository.save(partner).getId());
	}

	@Override
	public PartnerResponseDTO getPartner(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner Id.. "));
		return mapper.map(partner, PartnerResponseDTO.class);
	}

	@Override
	public ApiResponse updatePartner(Long id, UpdatePartnerDTO partnerupdateDTO) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Category category = categoryRepository.findById(partnerupdateDTO.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category ID"));

		PartnerAddress myAddress = mapper.map(partnerupdateDTO.getMyAddress(), PartnerAddress.class);

		partner.setFirstName(partnerupdateDTO.getFirstName());
		partner.setLastName(partnerupdateDTO.getLastName());
		partner.setEmail(partnerupdateDTO.getEmail());
		partner.setPhoneNumber(partnerupdateDTO.getPhoneNumber());
		partner.setExperience(partnerupdateDTO.getExperience());
		partner.setBirthDate(partnerupdateDTO.getBirthDate());
		partner.setCategory(category);
		partner.setMyAddress(myAddress);

		partnerRepository.save(partner);

		return new ApiResponse("Partner Updated with id " + partner.getId());
	}

	@Override
	public ApiResponse deletePartner(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		partner.setStatus(false);
		partnerRepository.save(partner);
		return new ApiResponse("Partner Deleted with id" + partner.getId());
	}

	@Override
	public List<PartnerOrderDTO> getPartnerOrders(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		return partner.getMyOrders().stream().map(order -> mapper.map(order, PartnerOrderDTO.class)).toList();
	}

	@Override
	public Double getTotalEarning(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		return partner.getTotalEarning();
	}

	@Override
	public List<PartnerServiceDTO> getPartnerServices(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));
		Category c = categoryRepository.findById(partner.getCategory().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category ID"));

		List<PartnerServiceDTO> partnerServices = c.getServices().stream()
				.map(service -> mapper.map(service, PartnerServiceDTO.class)).toList();
		if (partnerServices.isEmpty()) {
			throw new ResourceNotFoundException("No services to show");
		}

		return partnerServices;
	}

	@Override
	public ApiResponse verifyPartner(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));
		partner.setVerified(true);
		return new ApiResponse("");
	}

}
