package tn.esprit.CROTUN.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tn.esprit.CROTUN.Audit.AcitivityLogger;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getAcitivityLogger());
	}
	
	@Bean
	public AcitivityLogger getAcitivityLogger() {
		return new AcitivityLogger();
	}


}
