package pe.edu.utp.alumnosapi.validator;

import pe.edu.utp.alumnosapi.entity.Alumno;

public class AlumnoValidator{
	public static void save(Alumno alumno) {
		if(alumno.getCodigo()==null) {
			throw new RuntimeException("El codigo es requerido");
		}
		if(alumno.getCodigo().length()<8) {
			throw new RuntimeException("El codigo debe ser mayor 8 caracteres");
		}
		if(alumno.getNombre()==null) {
			throw new RuntimeException("El nombre es requerido");
		}
		if(alumno.getCodigo().length()>100) {
			throw new RuntimeException("El nombre es muy extenso, máximo 100 caracteres");
		}
		if(alumno.getCarrera()==null) {
			throw new RuntimeException("La carrera es requerida");
		}
		if(alumno.getDireccion()==null) {
			throw new RuntimeException("La dirección es requerida");
		}
	}

}
