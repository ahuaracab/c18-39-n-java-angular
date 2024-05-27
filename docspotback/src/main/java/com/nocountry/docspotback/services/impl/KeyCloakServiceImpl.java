package com.nocountry.docspotback.services.impl;

import com.nocountry.docspotback.models.User;
import com.nocountry.docspotback.security.KeyCloakConfig;
import com.nocountry.docspotback.services.IKeyCloakService;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KeyCloakServiceImpl implements IKeyCloakService {

    @Override
    public boolean addUser(User user) throws Exception{
        boolean rpta;
        //USUARIO CON PRIVILEGIOS de manejo total de realm para poder agregar, debe ser un ADMIN, algun usuario fijo //usuario.getUsername() falla, porque ese usuario no existe
        RealmResource realmResource = KeyCloakConfig.getInstance("andersoncusma20@gmail.com").realm(KeyCloakConfig.realm);
        UsersResource usersResource = realmResource.users();

        List<UserRepresentation> lista = usersResource.search(user.getEmail(), true);
        rpta = lista.isEmpty();

        if (rpta) {
            //Si lista vacia, significa que usuario no existe, entonces crearlo
            UserRepresentation ur = new UserRepresentation();
            ur.setUsername(user.getEmail());
            ur.setCredentials(Arrays.asList(generarPassword(user.getPassword())));
            ur.setFirstName(user.getName());
            ur.setLastName(user.getLastname());
            //ur.setRealmRoles(Arrays.asList("USER"));//NO FUNCIONA
            ur.setEmail(user.getEmail());
            ur.setEnabled(true);
            ur.setEmailVerified(true);
            usersResource.create(ur);

            //Agregar un rol por defecto para que funcione las opciones de menu
            //Se busca nuevamente al usuario creado para obtener su ID
            lista = usersResource.search(user.getEmail(), true);
            String idUser = lista.get(0).getId();
            RoleRepresentation rr = realmResource.roles().get(user.getRol()).toRepresentation();
            usersResource.get(idUser).roles().realmLevel().add(Arrays.asList(rr));
        }

        return rpta;
    }

    private CredentialRepresentation generarPassword(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        return credential;
    }

}
