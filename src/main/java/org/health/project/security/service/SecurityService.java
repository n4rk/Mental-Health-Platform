package org.health.project.security.service;

import org.health.project.security.entities.AppRole;
import org.health.project.security.entities.AppUser;

import java.util.List;

public interface SecurityService {

    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser( String username, String roleName);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
    // login
    AppUser login(AppUser appUser);
    // logout
}
