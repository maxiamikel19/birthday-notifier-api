package com.maxiamikel.BirthdayNotifierAPI.schelduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;
import com.maxiamikel.BirthdayNotifierAPI.services.birthday.BirthdayService;
import com.maxiamikel.BirthdayNotifierAPI.services.email.EmailService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BirthdaySchelduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private BirthdayService birthdayService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendBirthdayNotification() {
        LocalDate today = LocalDate.now();
        String messageToday = "Happy birthday to you";
        String subjectToday = "Birthday";
        List<User> usersBirthdayToday = birthdayService.findByBirthdayToday(today);

        LocalDate tomorrow = today.plusDays(1);
        String messageTomorrow = "Your birthday is comming tomorrow";
        String subjectTomorrow = "Alert";
        List<User> usersBirthdaysTomorrow = birthdayService.findByBirthdayToday(tomorrow);

        for (User userToday : usersBirthdayToday) {
            emailService.sendBirthdayNotification(userToday.getEmail(), subjectToday, messageToday);
            log.info("Today: " + usersBirthdayToday.size());
        }

        for (User userTomorrow : usersBirthdaysTomorrow) {
            emailService.sendBirthdayNotification(userTomorrow.getEmail(), subjectTomorrow, messageTomorrow);
            log.info("Tomorrow: " + usersBirthdaysTomorrow.size());
        }
    }
}
