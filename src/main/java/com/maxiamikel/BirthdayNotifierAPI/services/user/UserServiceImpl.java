package com.maxiamikel.BirthdayNotifierAPI.services.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.dtos.UserUpdateRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.entities.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User create(UserRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public User findById(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public User update(String userId, UserUpdateRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<User> findByBirthdayToday(LocalDate searchDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByBirthdayToday'");
    }

}
