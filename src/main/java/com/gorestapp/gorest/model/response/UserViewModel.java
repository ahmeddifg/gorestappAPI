package com.gorestapp.gorest.model.response;

import com.gorestapp.gorest.integration.responseModel.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewModel {
    private String name;
    private String gender;
    private String status;

    public UserViewModel(UserAccount userAccount) {
        this.name = userAccount.getName();
        this.gender = userAccount.getGender();
        this.status = userAccount.getStatus();
    }
}
