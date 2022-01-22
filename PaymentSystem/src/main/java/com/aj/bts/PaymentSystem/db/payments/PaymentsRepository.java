package com.aj.bts.PaymentSystem.db.payments;

import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payment, Long> {

    Payment findPaymentByPayId(String payId);

}
