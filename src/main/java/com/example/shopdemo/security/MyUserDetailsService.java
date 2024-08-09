package com.example.shopdemo.security;


import com.example.shopdemo.entity.Users;
import com.example.shopdemo.enums.EnumAviableStatus;
import com.example.shopdemo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UsersRepository usersReposirtory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=usersReposirtory.findUsersByEmailAndActive(username, EnumAviableStatus.ACTIVE.value);
        if (user==null){
            throw   new UsernameNotFoundException("Not found "+ username);
        }
        MyUserDetails myUserDetails=new MyUserDetails(user);

        return myUserDetails;
    }
}
