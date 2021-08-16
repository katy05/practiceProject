package task8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication

public class task8 {

    public static void main(String[] args) {
        SpringApplication.run(task8.class, args);
    }
}
