package pe.edu.utp.alumnosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlumnosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnosapiApplication.class, args);
	}

}
