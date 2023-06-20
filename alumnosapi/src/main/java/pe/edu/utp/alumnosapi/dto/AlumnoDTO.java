package pe.edu.utp.alumnosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {
	private int id;
	private String codigo;
	private String nombre;
	private String carrera;
	private String direccion;
}
