package com.netcrackerpractice.startup_social_network.app;

import com.netcrackerpractice.startup_social_network.annotations.IgnoreDuringScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.netcrackerpractice.startup_social_network", excludeFilters = @ComponentScan.Filter(IgnoreDuringScan.class))
@EnableJpaRepositories(basePackages = "com.netcrackerpractice.startup_social_network.repositories")
@EntityScan(basePackages = "com.netcrackerpractice.startup_social_network.entity")
@SpringBootApplication
public class StartupSocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartupSocialNetworkApplication.class, args);


	}
}
