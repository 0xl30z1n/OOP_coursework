package br.iesb.imarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
    private String token;
    private Date lastUpdate;
    private boolean acesso;
}