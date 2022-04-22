package com.gorestapp.gorest.entities;

import com.gorestapp.gorest.common.Gender;
import com.gorestapp.gorest.integration.responseModel.UserAccount;
import com.gorestapp.gorest.model.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserAuthAccounts")
public class UserAuthAccount implements UserDetails {
    public String _id;
    public Integer gorestId;
    @Indexed(unique = true, background = true)
    public String userName;
    public String password;
    @Indexed(unique = true, background = true)
    public String email;
    public int isActive;
    private Gender gender;
    public String token;


   public UserAuthAccount(RegisterRequest empAccount){
       this.userName= empAccount.getUserName();
       this.password= empAccount.getPassword();
       this.email= empAccount.getEmail();
       this.gender= empAccount.getGender();
       this.isActive=1;
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive == 1 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
