package com.bfluent.management_api.Bfluent.domain.interector.auth;

import com.bfluent.management_api.Bfluent.domain.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserUseCase implements UserDetailsService {
    private final StudentRepository studentRepository;
    //private final CustomerRepository customerRepository;

    public FindUserUseCase(StudentRepository studentRepository /*CustomerRepository customerRepository*/) {
        this.studentRepository = studentRepository;
        //this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var adminUser = studentRepository.findByEmailForAuth(email);
        //final var customerUser = customerRepository.findByEmail(email);

        if(adminUser.isEmpty()/* && customerUser.isEmpty()*/){
            throw new UsernameNotFoundException("User not found");
        }

        return adminUser.get();//.orElseGet(() -> customerUser.get());
    }
}
