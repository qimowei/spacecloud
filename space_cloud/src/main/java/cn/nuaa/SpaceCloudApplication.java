package cn.nuaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.nuaa.dao")
public class SpaceCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceCloudApplication.class, args);

		System.out.println("///////////////////////////////////////////////////////////////////");
		System.out.println("//																 //");
		System.out.println("//							项目启动成功						  	 //");
		System.out.println("//																 //");
		System.out.println("///////////////////////////////////////////////////////////////////");

	}

}


