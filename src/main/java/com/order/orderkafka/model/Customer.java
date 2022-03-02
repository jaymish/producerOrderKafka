package com.order.orderkafka.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Customer {
    @Id
    private String customer_id;
    private String customer_name;
    private String email_id;
}
