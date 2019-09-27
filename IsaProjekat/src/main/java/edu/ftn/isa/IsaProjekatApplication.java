package edu.ftn.isa;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan("edu.ftn.isa.model")
public class IsaProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaProjekatApplication.class, args);
	}
	
	@Bean(value = "datasource")
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	}
	
}
