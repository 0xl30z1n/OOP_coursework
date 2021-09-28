package br.iesb.imarket.service;

import br.iesb.imarket.dto.UserDTO;
import br.iesb.imarket.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private  List<UserEntity> users = new ArrayList<>();

    public String login_adm(UserDTO user){
        String email = user.getEmail();
        String password = user.getPassword();

        for(UserEntity u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return u.getToken();
            }
        }
        return null;
    }
    public int signup_adm(UserDTO user){

        if(user.getName().trim().equals("") || user.getName().trim().split(" ").length < 2){
            return 1;
        }
        if(!(user.getEmail().contains("@"))){
            return 2;
        }
        if(user.getPassword().length() < 6){
            return 3;
        }
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setAcesso(true);
        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        users.add(entity);
        return 0;
    }
}
