package br.iesb.imarket.model.builder;

import br.iesb.imarket.model.User;

public class UserBuilder {
    private final User user;

    private UserBuilder(){
        this.user = new User();
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder nameInUser(String name){
        user.setName(name);
        return this;
    }

    public UserBuilder emailInUser(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder passwordInUser(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder tokenInUser(String token){
        user.setToken(token);
        return this;
    }

    public UserBuilder withAcesso(boolean acess){
        user.setAcess(acess);
        return this;
    }

    public User build(){
        return this.user;
    }

}
