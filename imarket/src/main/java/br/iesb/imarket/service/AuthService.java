package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.UserDTO;
import br.iesb.imarket.dto.request.UserLoginDTO;
import br.iesb.imarket.dto.request.builder.UserDTOBuilder;
import br.iesb.imarket.dto.request.builder.UserLoginDTOBuilder;
import br.iesb.imarket.exception.UserBadRequestException;
import br.iesb.imarket.exception.UserNotFoundException;
import br.iesb.imarket.model.User;
import br.iesb.imarket.model.UserLogin;
import br.iesb.imarket.model.builder.UserBuilder;
import br.iesb.imarket.model.builder.UserLoginBuilder;
import br.iesb.imarket.repository.UserLoginRepo;
import br.iesb.imarket.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepo repositoyUser;

    @Autowired
    private UserLoginRepo repositoryLogin;

    public List<UserDTO> getUsers() throws UserNotFoundException{
        List<User> listUsers = verifyIfUsersExists();
        List<UserDTO> list = new ArrayList<>();
        for (User u: listUsers){
            UserDTO dto = getUserDTO(u);
            list.add(dto);
        }
        return list;
    }

    public List<UserLoginDTO> getUsersLog() throws UserNotFoundException{
        List<UserLogin> listUsers = verifyIfUsersLogExists();
        List<UserLoginDTO> list = new ArrayList<>();
        for (UserLogin u: listUsers){
            UserLoginDTO dto = getUserLoginDTO(u);
            list.add(dto);
        }
        return list;
    }

    public UserDTO getUserById(Long id) throws UserNotFoundException{
        UserDTO user = verifyIfUserExistsById(id);
        return user;
    }

    public UserLoginDTO getLogById(Long id) throws UserNotFoundException{
        UserLoginDTO user = verifyIfUserLogExistsById(id);
        return user;
    }

    public String login(UserDTO user){
        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> resultBank = repositoyUser.findByEmailAndPasswordContaining(email,password);
        User userResult = resultBank.get();

        UserLoginBuilder builder = UserLoginBuilder.builder();
        UserLogin entity = builder.idInUser(userResult.getId()).nameInUser(userResult.getName())
                .tokenInUser(userResult.getToken()).withAcesso(userResult.isAcess()).build();

        repositoryLogin.save(entity);
        return userResult.getToken();
    }

    public int signup(UserDTO user) throws UserBadRequestException{
        verifyIfEmailOrPasswordExists(user.getEmail(), user.getPassword());
        if(user.getName().trim().equals("") || user.getName().trim().split(" ").length < 2 || verifyFirstCaracName(user.getName().trim().split(" ")) || verifyNumber(user.getName().trim()) || verifySpecial(user.getName().trim())){
            return 1;
        }
        if(!(user.getEmail().contains("@")) || !(user.getEmail().trim().substring(user.getEmail().trim().length()-4,user.getEmail().trim().length()).equals(".com") || user.getEmail().trim().substring(user.getEmail().trim().length()-3,user.getEmail().trim().length()).equals(".br")) || user.getEmail().trim().substring(user.getEmail().indexOf("@") + 1,user.getEmail().indexOf("@")+2).equals(".")){
            return 2;
        }
        if(user.getPassword().length() < 6 || firstVerifyNumber(user.getPassword().trim()) || firstVerifySpecial(user.getPassword().trim()) || !(user.getPassword().trim().substring(0,1).equals(user.getPassword().trim().substring(0,1).toUpperCase())) || !verifyNumber(user.getPassword().trim()) || !verifySpecial(user.getPassword().trim())){
            return 3;
        }
        String token = UUID.randomUUID().toString();
        UserBuilder builder = UserBuilder.builder();
        User entity = builder.nameInUser(user.getName()).emailInUser(user.getEmail())
                .passwordInUser(user.getPassword()).withAcesso(user.isAcess()).tokenInUser(token).build();
        repositoyUser.save(entity);
        return 0;
    }

    private List<User> verifyIfUsersExists() throws UserNotFoundException {
        Iterator<User> resultBank = repositoyUser.findAll().iterator();
        List<User> listUsers = new ArrayList<>();
        resultBank.forEachRemaining(listUsers::add);
        if(listUsers.isEmpty()){
            throw new UserNotFoundException("Bank users is empty");
        }
        return listUsers;
    }

    private List<UserLogin> verifyIfUsersLogExists() throws UserNotFoundException{
        Iterator<UserLogin> resultBank = repositoryLogin.findAll().iterator();
        List<UserLogin> listUsers = new ArrayList<>();
        resultBank.forEachRemaining(listUsers::add);
        if(listUsers.isEmpty()){
            throw new UserNotFoundException("Bank users login is empty");
        }
        return listUsers;
    }

    private UserDTO verifyIfUserExistsById(Long id) throws UserNotFoundException{
        User resultBank = repositoyUser.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with ID "+ id));
        return getUserDTO(resultBank);
    }

    private UserLoginDTO verifyIfUserLogExistsById(Long id) throws UserNotFoundException{
        UserLogin resultBank = repositoryLogin.findById(id).orElseThrow(()-> new UserNotFoundException("User login not found with ID "+ id));
        return getUserLoginDTO(resultBank);
    }

    private void verifyIfEmailOrPasswordExists(String email, String password) throws UserBadRequestException {
        Optional<List<User>> aux = repositoyUser.findByEmailOrPasswordContaining(email,password);
        List<User> result = aux.get();
        if(!result.isEmpty()){
            throw new UserBadRequestException("Email or password already exists");
        }
    }

    private UserDTO getUserDTO(User u) {
        UserDTOBuilder builder = UserDTOBuilder.builder();
        UserDTO dto = builder.nameInUser(u.getName()).emailInUser(u.getEmail())
                .passwordInUser(u.getPassword()).withAcesso(u.isAcess()).build();
        return dto;
    }

    private UserLoginDTO getUserLoginDTO(UserLogin u) {
        UserLoginDTOBuilder builder = UserLoginDTOBuilder.builder();
        UserLoginDTO dto = builder.nameInUser(u.getName()).withToken(u.getToken())
                .withAcesso(u.isAcess()).build();
        return dto;
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