package com.aj.bts.TicketManagementSystem.db.tickets;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Integer> {

}
