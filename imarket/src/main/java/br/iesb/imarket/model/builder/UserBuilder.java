package br.iesb.imarket.model.builder;

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
        user.setemail(email);
        return this;
    }

    public UserBuilder passwordInUser(String password){
        user.setpassword(password);
        return this;
    }

    public UserBuilder tokenInUser(String token){
        user.settoken(token);
        return this;
    }

    public UserBuilder withAcesso(boolean acesso){
        user.setacesso(acesso);
        return this;
    }

    public User build(){
        return this.user;
    }

}
