package fsb.ucar.Microservice.QCM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroServiceQcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceQcmApplication.class, args);
	}


}
