package com.aj.bts.TicketManagementSystem.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    public String createPay(String name, int sum){
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("sum", String.valueOf(sum));
        String uuid = new RestTemplate().getForObject("http://localhost:8070/api/id?name={name}&sum={sum}", String.class, map);
        return uuid;
    }

    public Status checkStatus(String id){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        String status = (String) new RestTemplate().getForObject("http://localhost:8070/api/status/{id}", HashMap.class, map).get("status");
        return Status.valueOf(status);
    }


    enum Status{

        NEW("NEW"),
        FAILED("FAILED"),
        DONE("DONE");

        public final String label;

        Status(String done) {
            this.label = done;
        }

    }

}
