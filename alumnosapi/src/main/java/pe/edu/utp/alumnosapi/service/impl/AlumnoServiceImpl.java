package pe.edu.utp.alumnosapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.alumnosapi.entity.Alumno;
import pe.edu.utp.alumnosapi.exception.GeneralServiceException;
import pe.edu.utp.alumnosapi.exception.NoDataFoundException;
import pe.edu.utp.alumnosapi.exception.ValidateServiceException;
import pe.edu.utp.alumnosapi.repository.AlumnoRepository;
import pe.edu.utp.alumnosapi.service.AlumnoService;
import pe.edu.utp.alumnosapi.validator.AlumnoValidator;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService{
	@Autowired
	private AlumnoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch(ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByCodigo(String codigo, Pageable page) {
		// TODO Auto-generated method stub
		try {
			return repository.findByCodigoContaining(codigo, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findById(int id) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id)
					.orElseThrow(()->new NoDataFoundException("No existe el alumno con el ID "+id));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		AlumnoValidator.save(alumno);
		try {
			if(repository.findByCodigo(alumno.getCodigo())!=null) {
				throw new ValidateServiceException("Existe un alumno con el código "+alumno.getCodigo());
			}
			return repository.save(alumno);
		} catch (ValidateServiceException | GeneralServiceException e) {
			log.info(e.getMessage());
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Alumno update(Alumno alumno) {
		// TODO Auto-generated method stub
		AlumnoValidator.save(alumno);
		try {
			Alumno registroCod = repository.findByCodigo(alumno.getCodigo());
			if(registroCod!= null && registroCod.getId()!=alumno.getId())  {
				throw new ValidateServiceException("Existe un alumno con ese código "+alumno.getCodigo());
			}			
			Alumno registro = repository.findById(alumno.getId())
					.orElseThrow(()->new NoDataFoundException("No existe el alumno con el ID "+alumno.getId()));
			registro.setCodigo(alumno.getCodigo());
			registro.setNombre(alumno.getNombre());
			registro.setCarrera(alumno.getCarrera());
			registro.setDireccion(alumno.getDireccion());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			Alumno registro = repository.findById(id)
					.orElseThrow(()->new NoDataFoundException("No existe el alumno con el ID "+id));
			repository.delete(registro);
		}catch (ValidateServiceException | NoDataFoundException e){ 
			log.info(e.getMessage());
			throw e;
	    }catch (Exception e) {
	    	log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

}
