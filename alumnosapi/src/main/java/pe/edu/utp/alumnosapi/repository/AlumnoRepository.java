package pe.edu.utp.alumnosapi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.alumnosapi.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	List<Alumno> 	findByCodigoContaining(String codigo, Pageable page);
	Alumno findByCodigo(String codigo);

}
