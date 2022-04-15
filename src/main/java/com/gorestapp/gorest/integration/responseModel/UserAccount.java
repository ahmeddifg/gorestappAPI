package com.gorestapp.gorest.integration.responseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
