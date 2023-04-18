package com.bdqn.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bdqn.mall.dao.*")
public class MailApplication {
	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}
}
