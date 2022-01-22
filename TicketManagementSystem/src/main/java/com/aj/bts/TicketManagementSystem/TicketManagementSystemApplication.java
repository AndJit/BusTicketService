package com.aj.bts.TicketManagementSystem;

import com.aj.bts.TicketManagementSystem.db.races.Race;
import com.aj.bts.TicketManagementSystem.db.races.RacesRepository;
import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;
import com.aj.bts.TicketManagementSystem.db.tickets.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class TicketManagementSystemApplication {

	@Autowired
	private RacesRepository racesRepository;

	@Autowired
	private TicketsRepository ticketsRepository;

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementSystemApplication.class, args);
	}


	@PostConstruct
	public void generateFlights(){
		ticketsRepository.deleteAll();
		racesRepository.deleteAll();
		for (int i = 0; i < 10; i++) {
			String from = getFrom();
			String to = getTo(from);
			Date time = new Date();
			time.setMinutes(new Date().getMinutes() + new Random().nextInt(60));
			long timestamp = time.getTime();
			int price = new Random().nextInt(500);
			int amount = 20;
			racesRepository.save(new Race(from, to, timestamp, price, amount));
		}

	}

	public String getFrom(){
		String[] cities = new String[]{"New-York", "Los-Angeles", "Denver", "Albuquerque", "San-Diego", "San-Jose", "San-Francisco"};
		String from = cities[new Random().nextInt(cities.length)];
		return from;
	}

	public String getTo(String from){
		String[] cities = new String[]{"New-York", "Los-Angeles", "Denver", "Albuquerque", "San-Diego", "San-Jose", "San-Francisco"};
		String to = cities[new Random().nextInt(cities.length)];
		if (to.equals(from)) return getTo(from);
		return to;
	}





}
