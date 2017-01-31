package com.psk.bank.model;

import java.time.LocalDateTime;

public interface Account {
    String getAccountNumber();
    String getFirstName();
    String getLastName();
    Boolean isActive();
    LocalDateTime getCreationTs();
}
