package br.iesb.imarket.dto.request.builder;

import br.iesb.imarket.dto.request.UserDTO;

public class UserDTOBuilder {
    private final UserDTO user;

    private UserDTOBuilder(){
        this.user = new UserDTO();
    }

    public static UserDTOBuilder builder(){
        return new UserDTOBuilder();
    }

    public UserDTOBuilder nameInUser(String name){
        user.setName(name);
        return this;
    }

    public UserDTOBuilder emailInUser(String email){
        user.setEmail(email);
        return this;
    }

    public UserDTOBuilder passwordInUser(String password){
        user.setPassword(password);
        return this;
    }

    public UserDTOBuilder withAcesso(boolean acess){
        user.setAcess(acess);
        return this;
    }

    public UserDTO build(){
        return this.user;
    }
}
