package com.homeservices.controller.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.CategoryRequestDto;
import com.homeservices.dto.request.ServiceRequestDto;
import com.homeservices.service.category.CategoryService;
import com.homeservices.service.services.ServicesService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/service")
public class ServiceController {
	private final ServicesService servicesService; 
//	GET /api/services - List all services 
	@GetMapping
	public ResponseEntity<?> getAllServices() {
		return ResponseEntity.ok(servicesService.getAllServices());
	}
//	POST /api/services - Create a service  
	@PostMapping
    public ResponseEntity<?> addNewService(CategoryRequestDto dto) {
		String response = servicesService.addNewService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesService.addNewService(dto));
    }
//	GET /api/services/{id} - Get specific service 
	@GetMapping(path = "/{serviceId}")
	public ResponseEntity<?> getServiceById(@PathVariable Long serviceId){
		return ResponseEntity.ok(servicesService.getServiceById(serviceId));
	}
//	PUT /api/services/{id} - Update service  
	@PostMapping(path = "/{serviceId}")
	public ResponseEntity<?> updateService(@PathVariable Long serviceId,@RequestBody ServiceRequestDto dto){
		return ResponseEntity.ok(servicesService.updateService(serviceId,dto));
	}
//	DELETE /api/services/{id} - Delete service 
	@DeleteMapping(path = "/{serviceId}")
	public ResponseEntity<?> deleteService(Long serviceId){
		return ResponseEntity.ok(servicesService.deleteService(serviceId));
	}
}
