package com.ntgspiyggdrasil.yggdrasil.security.services;

import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.ListIterator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
    public User loadUserByUserId(long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public Iterable<User> loadUserAll() {
        return userRepository.findAll();
    }
}
