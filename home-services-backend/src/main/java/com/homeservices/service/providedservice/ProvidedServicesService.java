package com.homeservices.service.providedservice;

import java.util.List;

import com.homeservices.dto.request.CategoryRequestDto;
import com.homeservices.dto.request.ProvidedServiceRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.ProvidedServiceResponseDTO;
import com.homeservices.entities.ProvidedService;


public interface ProvidedServicesService {
	List<ProvidedServiceResponseDTO> getAllServices();

	ApiResponse addNewService(ProvidedServiceRequestDto dto);

	ProvidedServiceResponseDTO getServiceById(Long serviceId);

	ApiResponse updateService(Long serviceId, ProvidedServiceRequestDto dto);

	ApiResponse deleteService(Long serviceId);
}
