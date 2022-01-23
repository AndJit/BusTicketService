package com.aj.bts.TicketManagementSystem.db.payments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payment, Integer> {

    Payment findPaymentByPayId(String payId);

    Payment findPaymentByTicketId(int ticketId);

}
