package br.iesb.imarket.model.builder;

import br.iesb.imarket.model.UserLogin;

public class UserLoginBuilder {
    private final UserLogin userLogin;

    private UserLoginBuilder(){
        this.userLogin = new UserLogin();
    }

    public static UserLoginBuilder builder(){
        return new UserLoginBuilder();
    }

    public UserLoginBuilder idInUser(Long id){
        userLogin.setId(id);
        return this;
    }

    public UserLoginBuilder nameInUser(String name){
        userLogin.setName(name);
        return this;
    }

    public UserLoginBuilder tokenInUser(String token){
        userLogin.setToken(token);
        return this;
    }

    public UserLoginBuilder withAcesso(boolean acess){
        userLogin.setAcess(acess);
        return this;
    }

    public UserLogin build(){
        return this.userLogin;
    }
}
