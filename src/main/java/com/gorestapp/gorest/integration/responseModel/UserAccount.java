package com.gorestapp.gorest.integration.responseModel;

import com.gorestapp.gorest.entities.UserAuthAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public UserAccount(UserAuthAccount userAuthAccount){
        this.email= userAuthAccount.getEmail();
        this.name= userAuthAccount.getUsername();
        this.gender= userAuthAccount.getGender().name();
        this.status="active";
    }
}
