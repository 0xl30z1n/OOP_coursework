package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.UserDTO;
import br.iesb.imarket.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private  List<User> users = new ArrayList<>();

    public String login(UserDTO user){
        String email = user.getEmail();
        String password = user.getPassword();

        for(User u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return u.getToken();
            }
        }
        return null;
    }
    public int signup(UserDTO user){

        if(user.getName().trim().equals("") || user.getName().trim().split(" ").length < 2 || verifyFirstCaracName(user.getName().trim().split(" ")) || verifyNumber(user.getName().trim()) || verifySpecial(user.getName().trim())){
            return 1;
        }
        if(!(user.getEmail().contains("@")) || !(user.getEmail().trim().substring(user.getEmail().trim().length()-4,user.getEmail().trim().length()).equals(".com") || user.getEmail().trim().substring(user.getEmail().trim().length()-3,user.getEmail().trim().length()).equals(".br")) || user.getEmail().trim().substring(user.getEmail().indexOf("@") + 1,user.getEmail().indexOf("@")+2).equals(".")){
            return 2;
        }
        if(user.getPassword().length() < 6 || firstVerifyNumber(user.getPassword().trim()) || firstVerifySpecial(user.getPassword().trim()) || !(user.getPassword().trim().substring(0,1).equals(user.getPassword().trim().substring(0,1).toUpperCase())) || !verifyNumber(user.getPassword().trim()) || !verifySpecial(user.getPassword().trim())){
            return 3;
        }
        User entity = new User();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setAcesso(true);
        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        users.add(entity);
        return 0;
    }
    private boolean firstVerifyNumber(String str){
        if(str.substring(0,1).equals("0") || str.substring(0,1).equals("1") || str.substring(0,1).equals("2") || str.substring(0,1).equals("3") || str.substring(0,1).equals("4") || str.substring(0,1).equals("5") || str.substring(0,1).equals("6") || str.substring(0,1).equals("7") || str.substring(0,1).equals("8") || str.substring(0,1).equals("9")){
            return true;
        }
        return false;
    }
    private boolean firstVerifySpecial(String str){
        if(str.substring(0,1).equals("@") || str.substring(0,1).equals("#") || str.substring(0,1).equals("$") || str.substring(0,1).equals("%") || str.substring(0,1).equals("&") || str.substring(0,1).equals("*") || str.substring(0,1).equals("_")){
            return true;
        }
        return false;
    }
    private boolean verifySpecial(String str){
        if(str.contains(".") || str.contains("_") || str.contains("$") || str.contains("@") || str.contains("#") || str.contains("%") || str.contains("*") || str.contains("&")){
            return true;
        }
        return false;
    }
    private boolean verifyNumber(String str){
        if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
            return true;
        }
        return false;
    }
    private boolean verifyFirstCaracName(String[] array){
        for(String aux: array){
            if(!(aux.substring(0,1).equals(aux.substring(0,1).toUpperCase())) || firstVerifyNumber(aux) || firstVerifySpecial(aux)){
                return true;
            }
        }
        return false;
    }
}