package el.ev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElevatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatorApplication.class, args);
	}

}
