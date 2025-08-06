package com.homeservices.service.partner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ApiException;
import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.AppUserRepository;
import com.homeservices.dao.CategoryRepository;
import com.homeservices.dao.OrderRepository;
import com.homeservices.dao.PartnerRepository;
import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.UpdatePartnerDTO;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.OrderResponse;
import com.homeservices.dto.response.PartnerOrderDTO;
import com.homeservices.dto.response.PartnerResponseDTO;
import com.homeservices.dto.response.PartnerServiceDTO;
import com.homeservices.entities.AppUser;
import com.homeservices.entities.Category;
import com.homeservices.entities.Order;
import com.homeservices.entities.Partner;
import com.homeservices.entities.PartnerAddress;
import com.homeservices.entities.UserAddress;
import com.homeservices.utils.OrderStatus;
import com.homeservices.utils.Role;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private final PartnerRepository partnerRepository;
	private final CategoryRepository categoryRepository;
	private final OrderRepository orderRepository;
	//private final AppUserRepository appUserRepository;
	//private final PasswordEncoder passwordEncoder;
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
		//String encodedPassowrd = passwordEncoder.encode(partner.getPassword());
		//.setPassword(encodedPassowrd);

	Partner savedPartner = partnerRepository.save(partner);
//		AppUser appUser = AppUser.builder().email(savedPartner.getEmail()).password(savedPartner.getPassword())
//
//				.role(Role.PARTNER).referenceId(savedPartner.getId()).entityType("PARTNER").build();
//		appUserRepository.save(appUser);

		return new ApiResponse("Partner Added with Id " + savedPartner.getId());

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

		List<PartnerOrderDTO> response = new ArrayList<>();
		for (Order order : partner.getMyOrders()) {
			String serviceName = order.getService().getName();
			String fullAddress = "No Address Provided";
			if (order.getAddress() != null) {
				UserAddress addr = order.getAddress();
				fullAddress = addr.getAddress() + ", " + addr.getCity() + ", " + addr.getState() + ", "
						+ addr.getCountry() + " - " + addr.getPincode();
			}
			PartnerOrderDTO dto = new PartnerOrderDTO(order.getId(), order.getServiceDate(), order.getServiceTime(),
					order.getCompletionDate(), order.getOrderStatus(), order.getTotalCost(), serviceName, fullAddress);
			response.add(dto);

		}

		return response;
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
		if (partner.isVerified() == true) {
			throw new ApiException("Partner Already Verified");
		}
		partner.setVerified(true);
		return new ApiResponse("Partner Verified Successfully");
	}

	@Override
	public ApiResponse assignOrderToPartner(Long partnerId, Long orderId) {
		Partner partner = partnerRepository.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Order  ID"));

		if (partner.getMyOrders().contains(order)) {
			throw new ApiException("Order Already Assigned to this Partner");
		}

		partner.getMyOrders().add(order);
		partnerRepository.save(partner);
		return new ApiResponse("Order with Id " + orderId + " Assigned to Partner " + partnerId);

	}

	@Override
	public List<PartnerResponseDTO> getAllPartners() {
		List<PartnerResponseDTO> list = partnerRepository.findAll().stream()
				.map(partner -> mapper.map(partner, PartnerResponseDTO.class)).toList();
		if (list.isEmpty()) {
			throw new ApiException("No Parteners to show");
		}
		return list;
	}

	@Override
	public List<PartnerResponseDTO> getByVerificationStatusTrue() {
		List<PartnerResponseDTO> list = partnerRepository.findByIsVerifiedTrue().stream()
				.map(partner -> mapper.map(partner, PartnerResponseDTO.class)).toList();
		if (list.isEmpty()) {
			throw new ApiException("No Parteners to show");
		}
		return list;
	}

	@Override
	public List<PartnerResponseDTO> getByVerificationStatusFalse() {
		List<PartnerResponseDTO> list = partnerRepository.findByIsVerifiedFalse().stream()
				.map(partner -> mapper.map(partner, PartnerResponseDTO.class)).toList();
		if (list.isEmpty()) {
			throw new ApiException("No Parteners to show");
		}
		return list;
	}

	@Override
	public ApiResponse updateOrderStatus(Long partnerId, Long orderId) {
		Partner partner = partnerRepository.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Order ID"));

		if (!partner.getMyOrders().contains(order)) {
			throw new ApiException("This order is not assigned to the specified partner.");
		}

		if (order.getOrderStatus() == OrderStatus.COMPLETED) {
			throw new ApiException("Order is already marked as COMPLETED.");
		}

		order.setOrderStatus(OrderStatus.COMPLETED);
		order.setCompletionDate(LocalDate.now());

		orderRepository.save(order);

		return new ApiResponse("Order with ID " + orderId + " marked as COMPLETED.");

	}

}
