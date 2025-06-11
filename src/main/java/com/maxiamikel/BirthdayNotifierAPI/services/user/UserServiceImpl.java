package com.maxiamikel.BirthdayNotifierAPI.services.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.dtos.UserUpdateRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.entities.User;
import com.maxiamikel.BirthdayNotifierAPI.exceptions.DuplicateEntityException;
import com.maxiamikel.BirthdayNotifierAPI.exceptions.ResourceNotFoundException;
import com.maxiamikel.BirthdayNotifierAPI.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(UserRequestDto request) {
        if (userRepository.findByEmail(request.getEmail().trim()).isPresent()) {
            throw new DuplicateEntityException("User aleready exists!");
        }

        User user = new User();
        user.setUserId(null);
        user.setName(request.getName().trim());
        user.setBirthday(request.getBirthday());
        user.setCreatedAt(LocalDate.now());
        user.setEmail(request.getEmail().trim());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone().trim());

        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(String userId) {
        return getById(userId);
    }

    @Override
    public void delete(String userId) {
        userRepository.deleteById(getById(userId).getUserId());
    }

    @Override
    @Transactional
    public User update(String userId, UserUpdateRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException(
                    "Email: " + request.getEmail() + " aleready used by annother user");
        }
        User presentUser = getById(userId);
        presentUser.setEmail(request.getEmail().trim());
        presentUser.setPhone(request.getPhone().trim());
        presentUser.setName(request.getName().trim());
        return userRepository.save(presentUser);
    }

    private User getById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ID:" + userId + " cannot be found!"));
    }

}
