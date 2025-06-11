package com.maxiamikel.BirthdayNotifierAPI.services.birthday;

import java.util.List;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;

public interface BirthdayService {

    List<User> getAnniversariesOfCurrentMonth();

    List<User> getAnniversariesNextMonth();

    List<User> getAnniversariesLastMonth();

    List<User> getAnniversariesCustomMonth(int month);
}
