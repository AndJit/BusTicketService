package com.aj.bts.TicketManagementSystem.db.tickets;

import com.aj.bts.TicketManagementSystem.db.races.Race;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "races_id", referencedColumnName = "id")
    private Race race;

    public Ticket(){

    }

    public Ticket(String name, Race race){
        this.name = name;
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
