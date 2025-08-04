package com.homeservices.service.providedservice;

import java.util.List;

import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.custom_exceptions.ApiException;
import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.ServiceRepository;
import com.homeservices.dto.request.ProvidedServiceRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.ProvidedServiceResponseDTO;
import com.homeservices.entities.ProvidedService;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class ProvidedServicesServiceImp implements ProvidedServicesService{
	private final ServiceRepository serviceRepository;
	private final ModelMapper modelMapper;
	
	
	@Override
	public List<ProvidedServiceResponseDTO> getAllServices() {
		
		return serviceRepository
				.findAll()
				.stream()
				.map(service -> 
				      modelMapper.map(service, ProvidedServiceResponseDTO.class))
				.toList();
	}
	
	@Override
	public ApiResponse addNewService(ProvidedServiceRequestDto dto) {
		if(serviceRepository.existsByName(dto.getName()))
			throw new ApiException("Duplicate service name");
		ProvidedService service = modelMapper.map(dto, ProvidedService.class);
		ProvidedService saved = serviceRepository.save(service);
		return new ApiResponse("Service added " + saved.getId());
	}
	@Override
	public ProvidedServiceResponseDTO getServiceById(Long serviceId) {
		ProvidedService service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service not found by ID " + serviceId));
		return modelMapper.map(service, ProvidedServiceResponseDTO.class);
	}
	@Override
	public ApiResponse updateService(Long serviceId, ProvidedServiceRequestDto dto) {
		ProvidedService service = serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found by id " + serviceId));
		modelMapper.map(dto,service);
		return new ApiResponse("Updated successfully");
	}
	@Override
	public ApiResponse deleteService(Long serviceId) {
		ProvidedService service = serviceRepository.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found by id " + serviceId));
		serviceRepository.delete(service);
		return new ApiResponse("deleted successfully");
	}
}
