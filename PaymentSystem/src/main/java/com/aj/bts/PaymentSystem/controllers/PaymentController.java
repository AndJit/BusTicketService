package com.aj.bts.PaymentSystem.controllers;

import com.aj.bts.PaymentSystem.db.payments.Payment;
import com.aj.bts.PaymentSystem.db.payments.PaymentsRepository;
import com.aj.bts.PaymentSystem.db.payments.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @GetMapping("/id")
    public String getId(@RequestParam String name, @RequestParam int sum){
        String id = UUID.randomUUID().toString() + sum;
        paymentsRepository.save(new Payment(id, Status.NEW));
        return id;
    }


    @GetMapping("/status/{id}")
    public Map<String, String> getStatus(@PathVariable String id){
        HashMap<String, String> out = new HashMap<>();
        out.put("status", "NONE");
        Payment payment = paymentsRepository.findPaymentByPayId(id);
        if (payment != null) {
            String statusName = "";
            if (payment.getStatus() == Status.NEW) {
                Status[] statuses = Status.values();
                int i = new Random().nextInt(2) + 1;
                Status status = statuses[i];
                payment.setStatus(status);
                paymentsRepository.save(payment);
                statusName = statuses[i].name();
            }else{
                statusName = payment.getStatus().name();
            }
            out.put("status", statusName);
        }
        return out;
    }

}
