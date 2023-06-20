package pe.edu.utp.alumnosapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import pe.edu.utp.alumnosapi.entity.Alumno;

public interface AlumnoService {

	public List<Alumno> findAll(Pageable page);	
	public List<Alumno> findByCodigo(String codigo, Pageable page);    
	public Alumno findById(int id);    
	public Alumno save(Alumno alumno);	
	public Alumno update(Alumno alumno);
	public void delete(int id);
}
