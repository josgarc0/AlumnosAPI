package pe.edu.utp.alumnosapi.converter;

import org.springframework.stereotype.Component;

import pe.edu.utp.alumnosapi.dto.AlumnoDTO;
import pe.edu.utp.alumnosapi.entity.Alumno;

@Component
public class AlumnoConverter extends AbstractConverter<Alumno, AlumnoDTO>{
	@Override
	public AlumnoDTO fromEntity(Alumno entity) {
		if(entity==null) return null;

		return AlumnoDTO.builder()
				.id(entity.getId())
				.codigo(entity.getCodigo())
				.nombre(entity.getNombre())
				.carrera(entity.getCarrera())
				.direccion(entity.getDireccion())
				.build();
	}
	@Override
	public Alumno fromDTO(AlumnoDTO dto) {
		if(dto==null) return null;

		return Alumno.builder()
				.id(dto.getId())
				.codigo(dto.getCodigo())
				.nombre(dto.getNombre())
				.carrera(dto.getCarrera())
				.direccion(dto.getDireccion())
				.build();
	}

}
