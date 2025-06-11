package com.maxiamikel.BirthdayNotifierAPI.services.birthday;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;
import com.maxiamikel.BirthdayNotifierAPI.repositories.UserRepository;

@Service
public class BirthdayServiceImpl implements BirthdayService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAnniversariesOfCurrentMonth() {
        int presentMonth = LocalDate.now().getMonthValue();
        return userRepository.findByBirthdayMonth(presentMonth);
    }

    @Override
    public List<User> getAnniversariesNextMonth() {
        int nextMonth = LocalDate.now().getMonthValue() + 1;
        return userRepository.findByBirthdayMonth(nextMonth);
    }

    @Override
    public List<User> getAnniversariesLastMonth() {
        int lastMonth = LocalDate.now().getMonthValue() - 1;
        return userRepository.findByBirthdayMonth(lastMonth);
    }

    @Override
    public List<User> getAnniversariesCustomMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month value. Use a number between 1 and 12.");
        }
        return userRepository.findByBirthdayMonth(month);
    }
}
