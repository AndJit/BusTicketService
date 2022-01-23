package com.aj.bts.TicketManagementSystem.services;

import com.aj.bts.TicketManagementSystem.db.payments.Payment;
import com.aj.bts.TicketManagementSystem.db.payments.PaymentsRepository;
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

    @Autowired
    private PaymentsRepository payments;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void process(){

        payments.findAll().iterator().forEachRemaining(payment -> {
            String id = payment.getPayId();
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
        Ticket ticket = payments.findPaymentByPayId(id).getTicket();
        remove(id);
        ticketService.delete(ticket);
    }

    public void add(String id, Ticket ticket){
        payments.save(new Payment(id, ticket));
    }

    private void remove(String id){
        payments.delete(payments.findPaymentByPayId(id));
    }

    public String getPaymentId(Ticket ticket){
        return payments.findPaymentByTicketId(ticket.getId()).getPayId();
    }


}
