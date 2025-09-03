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
import com.homeservices.dao.BookingRepository;
import com.homeservices.dao.PartnerRepository;
import com.homeservices.dto.request.PartnerRequestDTO;
import com.homeservices.dto.request.UpdatePartnerDTO;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.BookingResponse;
import com.homeservices.dto.response.PartnerBookingDTO;
import com.homeservices.dto.response.PartnerResponseDTO;
import com.homeservices.dto.response.PartnerServiceDTO;
import com.homeservices.entities.AppUser;
import com.homeservices.entities.Category;
import com.homeservices.entities.Booking;
import com.homeservices.entities.Partner;
import com.homeservices.entities.PartnerAddress;
import com.homeservices.entities.UserAddress;
import com.homeservices.utils.BookingStatus;
import com.homeservices.utils.Role;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

	private final PartnerRepository partnerRepository;
	private final CategoryRepository categoryRepository;
	private final BookingRepository bookingRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
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
		String encodedPassowrd = passwordEncoder.encode(partner.getPassword());
		partner.setPassword(encodedPassowrd);

		Partner savedPartner = partnerRepository.save(partner);
		AppUser appUser = AppUser.builder().email(savedPartner.getEmail()).password(savedPartner.getPassword())

				.role(Role.PARTNER).referenceId(savedPartner.getId()).entityType("PARTNER").build();
		appUserRepository.save(appUser);

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

//		partner.setFirstName(partnerupdateDTO.getFirstName());
//		partner.setLastName(partnerupdateDTO.getLastName());
//		partner.setEmail(partnerupdateDTO.getEmail());
//		partner.setPhoneNumber(partnerupdateDTO.getPhoneNumber());
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
	public List<PartnerBookingDTO> getPartnerBookings(Long id) {
		Partner partner = partnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		List<PartnerBookingDTO> response = new ArrayList<>();
		for (Booking booking : partner.getMyBookings()) {
			String serviceName = booking.getService().getName();
			String fullAddress = "No Address Provided";
			if (booking.getAddress() != null) {
				UserAddress addr = booking.getAddress();
				fullAddress = addr.getAddress() + ", " + addr.getCity() + ", " + addr.getState() + ", "
						+ addr.getCountry() + " - " + addr.getPincode();
			}
			PartnerBookingDTO dto = new PartnerBookingDTO(booking.getId(), booking.getServiceDate(), booking.getServiceTime(),
					booking.getCompletionDate(), booking.getBookingStatus(), booking.getTotalCost(), serviceName, fullAddress);
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
	public ApiResponse assignBookingToPartner(Long partnerId, Long bookingId) {
		System.out.println(partnerId);
		Partner partner = partnerRepository.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Booking  ID"));

		if (partner.getMyBookings().contains(booking)) {
			throw new ApiException("Booking Already Assigned to this Partner");
		}

		booking.setBookingStatus(BookingStatus.CONFIRMED);
		bookingRepository.save(booking);

		partner.getMyBookings().add(booking);
		partnerRepository.save(partner);
		return new ApiResponse("Booking with Id " + bookingId + " Assigned to Partner " + partnerId);

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
	public ApiResponse updateBookingStatusCompleted(Long partnerId, Long bookingId) {
		Partner partner = partnerRepository.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));

		if (!partner.getMyBookings().contains(booking)) {
			throw new ApiException("This booking is not assigned to the specified partner.");
		}

		if (booking.getBookingStatus() == BookingStatus.COMPLETED) {
			throw new ApiException("Booking is already marked as COMPLETED.");
		}

		booking.setBookingStatus(BookingStatus.COMPLETED);
		booking.setCompletionDate(LocalDate.now());
		partner.setNoOfBookings(partner.getNoOfBookings() + 1);

		partner.setTotalEarning(partner.getTotalEarning() + booking.getService().getPrice());

		bookingRepository.save(booking);
		partnerRepository.save(partner);

		return new ApiResponse("Booking with ID " + bookingId + " marked as COMPLETED.");

	}

	@Override
	public ApiResponse updateBookingStatusInProgress(Long partnerId, Long bookingId) {
		Partner partner = partnerRepository.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Partner ID"));

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));

		if (!partner.getMyBookings().contains(booking)) {
			throw new ApiException("This booking is not assigned to the specified partner.");
		}

		if (booking.getBookingStatus() == BookingStatus.INPROGRESS) {
			throw new ApiException("Booking is already marked as COMPLETED.");
		}

		booking.setBookingStatus(BookingStatus.INPROGRESS);
		bookingRepository.save(booking);

		return new ApiResponse("Booking with ID " + bookingId + " marked as INPROGRESS");
	}

}
