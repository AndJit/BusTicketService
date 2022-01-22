package com.aj.bts.TicketManagementSystem;

import com.aj.bts.TicketManagementSystem.db.races.Race;
import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TicketManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testRace(){
		String from = "A";
		String to = "B";
		long timestamp = 1651275204;
		int price = 200;
		int amount = 20;
		Race race = new Race(from, to, timestamp, price, amount);
		Assertions.assertEquals(race.getAmount(), amount);
	}

	@Test
	void testTicket(){
		String from = "A";
		String to = "B";
		long timestamp = 1651275204;
		int price = 200;
		int amount = 20;
		Race race = new Race(from, to, timestamp, price, amount);
		String username = "Vlad";
		Ticket ticket = new Ticket(username, race);
		Assertions.assertEquals(race.getFromCity(), from);
		Assertions.assertEquals(ticket.getRace(), race);
		Assertions.assertEquals(ticket.getName(), username);
	}
}
