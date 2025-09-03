package com.homeservices.controller.providedservice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.CategoryRequestDto;
import com.homeservices.dto.request.ProvidedServiceRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.ProvidedServiceResponseDTO;
import com.homeservices.service.category.CategoryService;
import com.homeservices.service.providedservice.ProvidedServicesService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/service")
public class ProvidedServiceController {
	private final ProvidedServicesService servicesService; 

	@GetMapping
	public ResponseEntity<?> getAllServices() {
		return ResponseEntity.ok(servicesService.getAllServices());
	}
 
	@PostMapping
    public ResponseEntity<?> addNewService(ProvidedServiceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesService.addNewService(dto));
    }
	
	@PostMapping("/getByIds")
	public ResponseEntity<List<ProvidedServiceResponseDTO>> getAllServicesByIds(@RequestBody List<Long> serviceIds) {
		return ResponseEntity.ok(servicesService.getAllServicesByIds(serviceIds));
	}

	@GetMapping(path = "/{serviceId}")
	public ResponseEntity<?> getServiceById(@PathVariable Long serviceId){
		return ResponseEntity.ok(servicesService.getServiceById(serviceId));
	}
	  
	@PutMapping(path = "/{serviceId}")
	public ResponseEntity<?> updateService(@PathVariable Long serviceId,@RequestBody ProvidedServiceRequestDto dto){
		return ResponseEntity.ok(servicesService.updateService(serviceId,dto));
	}

	@DeleteMapping(path = "/{serviceId}")
	public ResponseEntity<?> deleteService(Long serviceId){
		return ResponseEntity.ok(servicesService.deleteService(serviceId));
	}
	
}
