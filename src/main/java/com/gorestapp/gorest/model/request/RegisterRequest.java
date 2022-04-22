package com.gorestapp.gorest.model.request;

import com.gorestapp.gorest.common.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Length(min = 5 , max = 30)
    private String userName;

    @NotBlank
    @Length(min = 9 , max = 30)
    private String password;

    @NotBlank
    @Email
    private String email;


    private Gender gender;
}
