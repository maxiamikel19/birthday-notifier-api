package com.maxiamikel.BirthdayNotifierAPI.services.email;

public interface EmailService {
    void sendBirthdayNotification(String receiver, String subject, String message);
}
