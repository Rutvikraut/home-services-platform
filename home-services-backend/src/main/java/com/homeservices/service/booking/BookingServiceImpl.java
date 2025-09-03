package com.homeservices.service.booking;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.BookingRepository;
import com.homeservices.dao.PartnerRepository;
import com.homeservices.dao.ServiceRepository;
import com.homeservices.dao.UserAddressRepository;
import com.homeservices.dao.UserRepository;
import com.homeservices.dto.request.BookingRequestDto;
import com.homeservices.dto.response.AllBookingResponseDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.BookingResponse;
import com.homeservices.entities.Booking;
import com.homeservices.entities.Partner;
import com.homeservices.entities.User;
import com.homeservices.entities.UserAddress;
import com.homeservices.entities.ProvidedService;
import com.homeservices.utils.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepo;
	private final PartnerRepository partnerRepo;
	private final UserRepository userRepo;
	private final ServiceRepository serviceRepo;
	private final ModelMapper modelMapper;
	private final UserAddressRepository userAddressRepo;

	private UserAddress getOrAddAddress(User user, BookingRequestDto dto) {
		Optional<UserAddress> matchingAddress = user.getAddresses().stream()
				.filter(addr -> addr.getAddress().equalsIgnoreCase(dto.address().getAddress())
						&& addr.getCity().equalsIgnoreCase(dto.address().getCity())
						&& addr.getState().equalsIgnoreCase(dto.address().getState())
						&& addr.getCountry().equalsIgnoreCase(dto.address().getCountry())
						&& addr.getPincode().equalsIgnoreCase(dto.address().getPincode()))
				.findFirst();

		if (matchingAddress.isPresent()) {
			return matchingAddress.get();
		}

		UserAddress newAddress = new UserAddress();
		newAddress.setAddress(dto.address().getAddress());
		newAddress.setCity(dto.address().getCity());
		newAddress.setState(dto.address().getState());
		newAddress.setCountry(dto.address().getCountry());
		newAddress.setPincode(dto.address().getPincode());
		newAddress.setDeleted(false);

		UserAddress savedAddress = userAddressRepo.save(newAddress);

		user.getAddresses().add(savedAddress);
		userRepo.save(user);

		return newAddress;
	}

	@Override
	public ApiResponse createBooking(BookingRequestDto dto, Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		UserAddress selectedAddress = getOrAddAddress(user, dto);

		List<ProvidedService> services = serviceRepo.findAllById(dto.serviceIds());
		List<Booking> newBookings = new ArrayList<>();
		for (ProvidedService providedService : services) {
			Booking booking = new Booking();
			booking.setServiceDate(dto.serviceDate());
			booking.setServiceTime(dto.serviceTime());
			booking.setService(providedService);
			booking.setBookingStatus(BookingStatus.PENDING);
			booking.setTotalCost(providedService.getPrice());
			booking.setAddress(selectedAddress);
			newBookings.add(booking);
			user.getBookings().add(booking);
		}
		bookingRepo.saveAll(newBookings);
		return new ApiResponse("Order Created Successfully");
	}

	@Override
	public BookingResponse getBookingById(Long bookingId) {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));
		String serviceName = booking.getService().getName();
		String address = booking.getAddress().getAddress() + " " + booking.getAddress().getCity() + " "
				+ booking.getAddress().getState() + " " + booking.getAddress().getCountry() + " "
				+ booking.getAddress().getPincode();
		return new BookingResponse(
				booking.getId(), 
				booking.getServiceDate(),
				booking.getServiceTime(), 
				booking.getCompletionDate(),
				booking.getBookingStatus(), 
				booking.getTotalCost(), 
				serviceName, 
				address);
	}

	@Override
	public List<BookingResponse> getBookingsByUserId(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		List<Booking> bookings = user.getBookings();
		if (bookings.isEmpty()) {
			throw new ResourceNotFoundException("No Orders Found");
		}
		List<BookingResponse> response = new ArrayList<>();
		for (Booking booking : bookings) {
			String serviceName = booking.getService().getName();
			String fullAddress = "No Address Provided";
			if (booking.getAddress() != null) {
				UserAddress addr = booking.getAddress();
				fullAddress = addr.getAddress() + ", " + addr.getCity() + ", " + addr.getState() + ", "
						+ addr.getCountry() + " - " + addr.getPincode();
			}
			BookingResponse dto = new BookingResponse(
					booking.getId(),
					booking.getServiceDate(),
					booking.getServiceTime(),
					booking.getCompletionDate(), 
					booking.getBookingStatus(),
					booking.getTotalCost(),
					serviceName, 
					fullAddress);
			response.add(dto);
			
		}
		return response;
	}

	@Override
	public List<Booking> getBookingsByPartnerId(Long partnerId) {
		Partner partner = partnerRepo.findById(partnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Partner Not Found"));
		List<Booking> bookings = partner.getMyBookings();
		if (bookings.isEmpty()) {
			throw new ResourceNotFoundException("Bookings Not Found");
		}
		return bookings;
	}

	@Override
	public ApiResponse updateBookingStatus(Long bookingId) {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

		if (booking.getBookingStatus().equals(BookingStatus.PENDING)) {
			booking.setBookingStatus(BookingStatus.CONFIRMED);
		} else if (booking.getBookingStatus().equals(BookingStatus.CONFIRMED)) {
			booking.setBookingStatus(BookingStatus.INPROGRESS);
		} else if (booking.getBookingStatus().equals(BookingStatus.INPROGRESS)) {
			booking.setBookingStatus(BookingStatus.COMPLETED);
			booking.setCompletionDate(LocalDate.now());
		}

		bookingRepo.save(booking);
		return new ApiResponse("Booking Status Updated");
	}

	@Override
	public ApiResponse cancelBookingById(Long bookingId) {
		Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));
		booking.setBookingStatus(BookingStatus.CANCELLED);
		bookingRepo.save(booking);
		return new ApiResponse("Booking Cancelled");
	}

	@Override
	public List<AllBookingResponseDto> getAllBookings() {
		List<Booking> bookings = bookingRepo.findAll();
		if (bookings.isEmpty()) {
			throw new ResourceNotFoundException("Bookings Not Found");
		}
		return bookings.stream().map(booking -> {
			AllBookingResponseDto newBooking = new AllBookingResponseDto();
			newBooking.setId(booking.getId());
			newBooking.setBookingStatus(booking.getBookingStatus());
			newBooking.setCompletionDate(booking.getCompletionDate());
			newBooking.setService(booking.getService().getName());
			newBooking.setServiceDate(booking.getServiceDate());
			newBooking.setServiceTime(booking.getServiceTime());
			newBooking.setTotalCost(booking.getTotalCost());
			newBooking.setCategoryId(booking.getService().getCategory().getId());
			return newBooking;
		}).toList();
	}

	@Override
	public List<Booking> getBookingsByStatus(String status) {
		BookingStatus bookingStatus = BookingStatus.valueOf(status.toUpperCase());
		List<Booking> bookings = bookingRepo.findByBookingStatus(bookingStatus);
		if (bookings.isEmpty()) {
			throw new ResourceNotFoundException("Booking Not Found By Status : " + status);
		}
		return bookings;
	}

}
