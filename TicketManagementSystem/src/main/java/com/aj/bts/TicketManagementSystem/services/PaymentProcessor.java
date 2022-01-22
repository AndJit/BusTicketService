package com.aj.bts.TicketManagementSystem.services;

import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class PaymentProcessor {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketService ticketService;

    private static HashMap<String, Ticket> payments = new HashMap();

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void process(){

        payments.keySet().iterator().forEachRemaining(id -> {
            switch (paymentService.checkStatus(id)){
                case FAILED: {
                    fail(id);
                    System.out.println("fail");
                }
                case DONE: break;
            }
        });
    }

    public void fail(String id){
        Ticket ticket = payments.get(id);
        ticketService.delete(ticket);
        remove(id);
    }

    public static void add(String id, Ticket ticket){
        payments.put(id, ticket);
    }

    private static void remove(String id){
        payments.remove(id);
    }

    public static Ticket getTicket(String id){
        return payments.get(id);
    }
}
