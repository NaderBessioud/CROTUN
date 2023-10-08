package tn.esprit.CROTUN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@EnableScheduling
@SpringBootApplication


public class CrotunApplication {

 

	public static void main(String[] args) {
		
		SpringApplication.run(CrotunApplication.class, args);

	
	}

		
		
	    
	}


