package com.udemyapi.udemyclone.security;

import com.udemyapi.udemyclone.entity.User;
import com.udemyapi.udemyclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with this email");
        }


//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority( user.getRole()));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
