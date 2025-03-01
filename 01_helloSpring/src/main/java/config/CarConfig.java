package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.Kia;
import model.KiaEngine;
import model.Tesla;
import model.ToyotaEngine;

@Configuration
public class CarConfig {
	
	@Bean
	public Kia kia() {
		return new Kia(new ToyotaEngine());
	}
	
	@Bean(name = "bmw")
	public Tesla telsa() {
		return new Tesla(new KiaEngine());
	}
}