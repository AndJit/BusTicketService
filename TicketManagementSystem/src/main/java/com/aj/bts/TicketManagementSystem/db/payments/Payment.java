package com.aj.bts.TicketManagementSystem.db.payments;

import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private int id;
    private String payId;

    @OneToOne
    @JoinColumn(name = "tickets_id", referencedColumnName = "id")
    private Ticket ticket;

    public Payment(){

    }

    public Payment(String payId, Ticket ticket){
        this.payId = payId;
        this.ticket = ticket;
    }


    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
