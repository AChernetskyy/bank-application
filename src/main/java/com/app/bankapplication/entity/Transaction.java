package com.app.bankapplication.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Data
public class Transaction {

    private UUID sender;
    private UUID receiver;
    private BigDecimal amount;
    private  String message;
    private Date creationDate;
}
