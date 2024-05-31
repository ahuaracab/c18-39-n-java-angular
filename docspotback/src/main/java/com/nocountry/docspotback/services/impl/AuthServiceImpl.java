package com.nocountry.docspotback.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    public boolean hasAccess(String path){
        boolean rpta = false;

        String methodRole = "";

        switch (path) {
            case "findAll":
                methodRole = "ADMIN";
                break;

            case "findById":
                methodRole = "PATIENT,MEDIC";
                break;
        }

        String metodoRoles[] = methodRole.split(",");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority gra : auth.getAuthorities()) {
            String rolUser = gra.getAuthority();
            System.out.println(auth.getName());
            System.out.println(rolUser);

            for (String rolMet : metodoRoles) {
                if (rolUser.equalsIgnoreCase(rolMet)) {
                    rpta = true;
                    break;
                }
            }
        }

        return rpta;
    }
}