package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 数据初始化器
 * 应用启动时自动创建测试数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===========================================");
        System.out.println("DataInitializer: 数据库初始化完成");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:mem:testdb");
        System.out.println("Username: sa");
        System.out.println("Password: (empty)");
        System.out.println("===========================================");
    }
}
