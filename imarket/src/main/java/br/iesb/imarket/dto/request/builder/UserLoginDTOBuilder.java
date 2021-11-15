package br.iesb.imarket.dto.request.builder;

import br.iesb.imarket.dto.request.UserLoginDTO;

public class UserLoginDTOBuilder {
    private final UserLoginDTO userLogin;

    private UserLoginDTOBuilder(){
        this.userLogin = new UserLoginDTO();
    }

    public static UserLoginDTOBuilder builder(){
        return new UserLoginDTOBuilder();
    }

    public UserLoginDTOBuilder nameInUser(String name){
        userLogin.setName(name);
        return this;
    }

    public UserLoginDTOBuilder withAcesso(boolean acess){
        userLogin.setAcess(acess);
        return this;
    }

    public UserLoginDTOBuilder withToken(String token){
        userLogin.setToken(token);
        return this;
    }

    public UserLoginDTO build(){
        return this.userLogin;
    }
}
