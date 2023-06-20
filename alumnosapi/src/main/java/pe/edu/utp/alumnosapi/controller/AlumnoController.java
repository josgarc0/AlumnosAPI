package pe.edu.utp.alumnosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.alumnosapi.converter.AlumnoConverter;
import pe.edu.utp.alumnosapi.dto.AlumnoDTO;
import pe.edu.utp.alumnosapi.entity.Alumno;
import pe.edu.utp.alumnosapi.service.AlumnoService;
import pe.edu.utp.alumnosapi.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/alumnos")
public class AlumnoController {
	@Autowired
	private AlumnoService service;
	
	@Autowired
	private AlumnoConverter converter;
	
	@GetMapping
	public ResponseEntity<List<AlumnoDTO>> findAll(
		@RequestParam(value="codigo", required = false, defaultValue = "") String codigo,
		@RequestParam(value="offset", required =false, defaultValue = "0") int pageNumber,
		@RequestParam(value="limit", required =false, defaultValue = "5") int pageSize
		){
		Pageable page=PageRequest.of(pageNumber, pageSize);
		List<Alumno> alumnos;
		if(codigo==null) {
			alumnos=service.findAll(page);
		} else {
			alumnos = service.findByCodigo(codigo, page);
		}
		List<AlumnoDTO> alumnosDTO=converter.fromEntity(alumnos);
		//return ResponseEntity.ok(alumnosDTO);
		return new WrapperResponse(true,"success",alumnosDTO).createResponse(HttpStatus.OK);
		
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<AlumnoDTO>> findById(@PathVariable("id") int id){
		Alumno registro=service.findById(id);
		AlumnoDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<AlumnoDTO> create(@RequestBody AlumnoDTO alumnoDTO){
		Alumno registro=service.save(converter.fromDTO(alumnoDTO));
		
		return new WrapperResponse(true,"success",converter.fromEntity(registro)).createResponse(HttpStatus.CREATED);
		//return ResponseEntity.status(HttpStatus.CREATED).body(converter.fromEntity(registro));
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<AlumnoDTO> update(@PathVariable("id") int id,@RequestBody AlumnoDTO alumnoDTO){
		Alumno registro=service.update(converter.fromDTO(alumnoDTO));
		
		return new WrapperResponse(true,"success",converter.fromEntity(registro)).createResponse(HttpStatus.OK);
		//return ResponseEntity.ok(converter.fromEntity(registro));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Alumno> delete(@PathVariable("id") int id){
		service.delete(id);
		
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
		//return ResponseEntity.ok(null);
	}
}
