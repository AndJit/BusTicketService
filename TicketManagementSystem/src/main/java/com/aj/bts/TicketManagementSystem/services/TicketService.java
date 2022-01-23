package com.aj.bts.TicketManagementSystem.services;

import com.aj.bts.TicketManagementSystem.db.races.Race;
import com.aj.bts.TicketManagementSystem.db.races.RacesRepository;
import com.aj.bts.TicketManagementSystem.db.tickets.Ticket;
import com.aj.bts.TicketManagementSystem.db.tickets.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private RacesRepository racesRepository;

    @Transactional
    public boolean save(Ticket ticket){
        Race race = ticket.getRace();
        race.setAmount(race.getAmount() - 1);
        ticketsRepository.save(ticket);
        racesRepository.save(race);
        return true;
    }

    public boolean delete(Ticket ticket){
        ticketsRepository.delete(ticket);
        Race race = ticket.getRace();
        race.setAmount(race.getAmount() + 1);
        racesRepository.save(race);
        return true;
    }

    public Ticket getTicket(int id){
        return ticketsRepository.findById(id).get();
    }

}
