package github.hmasumt52.jpa_auditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaAuditingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAuditingApplication.class, args);
	}

}
