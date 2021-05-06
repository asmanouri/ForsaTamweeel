package com.ii.app.services.interfaces;

public interface EmailService {
    void sendAccountMail(String receiver, String identifier);
    
    void sendTransactionMail(String receiver, String identifier);
}
