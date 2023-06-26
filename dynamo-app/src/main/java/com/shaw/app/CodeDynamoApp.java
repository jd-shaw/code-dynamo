package com.shaw.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

/**
 * @author shaw
 * @date 2023/6/25
 */
@Slf4j
@RestController
@SpringBootApplication
public class CodeDynamoApp {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(CodeDynamoApp.class, args);
        Environment env = application.getEnvironment();
        // 环境变量
        String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        String path = env.getProperty("spring.mvc.servlet.path", "");

        // 应用信息栏
        String appInfo = MessageFormat.format("应用 \"{0}\" 运行成功! \n\t", appName);
        // swagger栏
        String swagger = MessageFormat.format("Swagger文档: \t\thttp://{0}:{1}{2}{3}/doc.html\n\t", host, port, contextPath, path);

        log.info("\n----------------------------------------------------------\n\t" + "{}{}\n" + "----------------------------------------------------------", appInfo, swagger);
    }

}
