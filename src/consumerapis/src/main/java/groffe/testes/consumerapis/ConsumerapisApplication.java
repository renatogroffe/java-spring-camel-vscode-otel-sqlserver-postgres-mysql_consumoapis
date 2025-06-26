package groffe.testes.consumerapis;

import org.apache.camel.opentelemetry.starter.CamelOpenTelemetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CamelOpenTelemetry
@SpringBootApplication
public class ConsumerapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerapisApplication.class, args);
	}

}
