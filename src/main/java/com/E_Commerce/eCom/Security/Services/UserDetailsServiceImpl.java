package com.E_Commerce.eCom.Security.Services;

import com.E_Commerce.eCom.Model.User;
import com.E_Commerce.eCom.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(()-> new UsernameNotFoundException("Invalid username or password"));
        return new CustomUserDetails(user);
    }
}
