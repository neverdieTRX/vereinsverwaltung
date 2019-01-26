package de.trx.veve.facade;

import de.trx.veve.business.UserService;
import de.trx.veve.common.AspectLogger;
import de.trx.veve.entity.BaseEntity;
import de.trx.veve.facade.user.UserController;
import de.trx.veve.integration.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = {UserController.class, UserService.class, Application.class, AspectLogger.class})
@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
@EntityScan(basePackageClasses = {BaseEntity.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
