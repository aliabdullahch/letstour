package com.tourMaster.letsTour.security;

import com.tourMaster.letsTour.DAO.TourDestDAO;
import com.tourMaster.letsTour.modals.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    TourDestDAO mydao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  userfound=mydao.getUserByEmail(username);
        System.out.println(userfound);
        return new CustomUserDetails(userfound);
    }
}
