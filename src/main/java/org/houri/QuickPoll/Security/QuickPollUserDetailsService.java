package org.houri.QuickPoll.Security;

import org.houri.QuickPoll.domain.User;
import org.houri.QuickPoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.isAdmin()) {
            authorities = AuthorityUtils.createAuthorityList
                    ("ROLE_ADMIN");
        }
        // Create a UserDetails object from the data
        UserDetails userDetails = new org.springframework.security.core.
                userdetails.User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}
