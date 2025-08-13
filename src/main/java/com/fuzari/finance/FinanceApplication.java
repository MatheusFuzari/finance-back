package com.fuzari.finance;

import com.fuzari.exceptions.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
