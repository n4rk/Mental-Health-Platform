package org.health.project.security.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.health.project.security.entities.AppRole;
import org.health.project.security.entities.AppUser;
import org.health.project.security.repositories.AppRoleRepository;
import org.health.project.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        appUser.setId(UUID.randomUUID().toString());
        appUser.setPassword( passwordEncoder.encode( appUser.getPassword() ) );
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username,String roleName){
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add( appRole );
    }


    @Override
    public AppUser loadUserByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser>listUsers(){
        return appUserRepository.findAll();
    }

    @Override
    public AppUser login(AppUser appUser) {
        AppUser user = appUserRepository.findByUsername(appUser.getUsername());
        if(user == null){
            return null;
        }
        if(!passwordEncoder.matches(appUser.getPassword(), user.getPassword())){
            return null;
        }
        return user;
    }
}
