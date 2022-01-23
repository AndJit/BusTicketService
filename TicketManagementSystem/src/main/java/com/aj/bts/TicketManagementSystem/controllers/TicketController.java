package com.aj.bts.TicketManagementSystem.controllers;

import com.aj.bts.TicketManagementSystem.db.races.Race;
import com.aj.bts.TicketManagementSystem.db.races.RacesRepository;
import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;
import com.aj.bts.TicketManagementSystem.services.PaymentProcessor;
import com.aj.bts.TicketManagementSystem.services.PaymentService;
import com.aj.bts.TicketManagementSystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RacesRepository racesRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @GetMapping("/buy")
    public int buy(@RequestParam String name, @RequestParam int race_id){
        Ticket ticket = new Ticket(name, racesRepository.findRaceById(race_id));
        if (ticket.getRace() != null) {
            if (ticketService.save(ticket)) {
                String id = paymentService.createPay(name, racesRepository.findRaceById(race_id).getPrice());
                paymentProcessor.add(id, ticket);
                return 200;
            }
        }
        return 400;
    }

    @GetMapping("/info/{id}")
    public Map<String, Object> getInfo(@PathVariable String id){
        Map<String, Object> out = new HashMap<>();
        Ticket ticket = ticketService.getTicket(Integer.parseInt(id));
        out.put("status", null);
        out.put("race", null);
        if (ticket != null) {
            Race race = ticket.getRace();
            out.put("status", paymentService.checkStatus(paymentProcessor.getPaymentId(ticket)));
            out.put("race", race);
        }
        return out;
    }


}
