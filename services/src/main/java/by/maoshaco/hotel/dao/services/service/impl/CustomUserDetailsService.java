package by.maoshaco.hotel.dao.services.service.impl;

import by.maoshaco.hotel.dao.model.CustomProfileDetail;
import by.maoshaco.hotel.dao.model.Profile;
import by.maoshaco.hotel.dao.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Profile domainProfile = profileRepository.findByUsername(name);
        String role = domainProfile.getAuthority().getRole();
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role));

        CustomProfileDetail customProfileDetail = new CustomProfileDetail();
        customProfileDetail.setProfile(domainProfile);
        customProfileDetail.setAuthorities(authorities);
        return customProfileDetail;

    }
}
