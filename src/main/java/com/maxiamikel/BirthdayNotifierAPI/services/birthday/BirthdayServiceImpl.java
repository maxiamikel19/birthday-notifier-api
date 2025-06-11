package com.maxiamikel.BirthdayNotifierAPI.services.birthday;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;
import com.maxiamikel.BirthdayNotifierAPI.exceptions.NoContentFoundException;
import com.maxiamikel.BirthdayNotifierAPI.repositories.UserRepository;

@Service
public class BirthdayServiceImpl implements BirthdayService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAnniversariesOfCurrentMonth() {
        int presentMonth = LocalDate.now().getMonthValue();
        List<User> users = userRepository.findByBirthdayMonth(presentMonth);
        if (users.isEmpty()) {
            throw new NoContentFoundException("No birthday to celebrate in this month");
        }
        return users;
    }

    @Override
    public List<User> getAnniversariesNextMonth() {
        int nextMonth = LocalDate.now().getMonthValue() + 1;
        List<User> users = userRepository.findByBirthdayMonth(nextMonth);
        if (users.isEmpty()) {
            throw new NoContentFoundException("No birthday to celebrate in this month");
        }
        return users;
    }

    @Override
    public List<User> getAnniversariesLastMonth() {
        int lastMonth = LocalDate.now().getMonthValue() - 1;
        List<User> users = userRepository.findByBirthdayMonth(lastMonth);
        if (users.isEmpty()) {
            throw new NoContentFoundException("No birthday to celebrate in this month");
        }
        return users;
    }

    @Override
    public List<User> getAnniversariesCustomMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month value. Use a number between 1 and 12.");
        }

        List<User> users = userRepository.findByBirthdayMonth(month);
        if (users.isEmpty()) {
            throw new NoContentFoundException("No birthday to celebrate in " + Month.of(month));
        }
        return users;
    }

    @Override
    public List<User> findByBirthdayToday(LocalDate searchDate) {
        List<User> users = userRepository.findByBirthdayToday(searchDate.getMonthValue(), searchDate.getDayOfMonth());
        if (users.isEmpty()) {
            throw new NoContentFoundException("No birthday to celebrate today");
        }
        return users;
    }
}
