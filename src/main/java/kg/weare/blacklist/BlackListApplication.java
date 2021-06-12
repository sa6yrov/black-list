package kg.weare.blacklist;

import kg.weare.blacklist.service.impl.DocumentParseServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BlackListApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackListApplication.class, args);
	}

}
