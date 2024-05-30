package com.nocountry.docspotback.security;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;


public class KeyCloakConfig {
    public static Keycloak keycloak = null;
    public final static String serverUrl = "http://localhost:8080";
    //private static final String USER_CONSOLE = "admin";
    //public final static String REALM_MASTER = "master";
    public final static String realm = "docspot";
    public final static String clientId = "docspotback";
    public final static String clientSecret = ""; //necesario en confidencial
    //public final static String userRootName = "andersoncusma20@gmail.com"; //Usuario root o con privilegios necesarios si quiero uno fijo
    public final static String password = "123";

    public KeyCloakConfig() {
    }

    public static Keycloak getInstance(String userName){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    //.authorization("authorization")
                    //.clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }
    public static Keycloak getUserResource(String username) {
        return getInstance(username);
    }

}