package com.example.demo;

import jakarta.servlet.http.HttpServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication extends HttpServlet {

public static void main(String[] args) {
SpringApplication.run(DemoApplication.class, args);
}

}
