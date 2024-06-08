package com.nocountry.docspotback.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nocountry.docspotback.dto.AuthUser;
import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.repositories.IUserRepo;

/*
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepo repository;

   @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = repository.findByEmail(username);
        System.out.println(userOptional);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", username));
        }

        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities);
    }

}*/

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));

        List<GrantedAuthority> authorities = user.getRoles().stream()
               .map(role -> new SimpleGrantedAuthority(role.getNameRole()))
               .collect(Collectors.toList());
        System.out.println(authorities);
        return new AuthUser(user, authorities);
    }
}
