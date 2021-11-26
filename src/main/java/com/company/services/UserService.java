package com.company.services;

import com.company.dto.UserRegistrationDto;
import com.company.models.Role;
import com.company.models.User;
import com.company.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User addUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByLogin(registrationDto.getLogin()) != null) return null;
        User user = new User(registrationDto.getLogin(), bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        if (user.getLogin().equals("admin"))
            user.setRoles(new HashSet<>(Arrays.asList(new Role(1, "ROLE_USER"), new Role(2, "ROLE_ADMIN"))));
        else
            user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        return userRepository.save(user);
    }

    public User findByLogin(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user == null)
            return null;
        if (bCryptPasswordEncoder.matches(password, user.getPassword()))
            return user;
        else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
