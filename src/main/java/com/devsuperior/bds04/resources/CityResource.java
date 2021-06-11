package com.devsuperior.bds04.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {

	@Autowired
	private CityService service;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAllSortedByName(){
		List<CityDTO> list = service.findAllSortedByName();		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
		CityDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody @Valid CityDTO dto) {
		CityDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CityDTO> update(@PathVariable Long id, @RequestBody @Valid CityDTO dto) {
		CityDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
} 
