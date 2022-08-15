package br.com.saulo.cleancommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class CleancommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleancommerceApplication.class, args);
	}

}
