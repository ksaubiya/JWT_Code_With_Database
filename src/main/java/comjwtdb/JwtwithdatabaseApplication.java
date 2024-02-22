package comjwtdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class JwtwithdatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtwithdatabaseApplication.class, args);
	}

}
