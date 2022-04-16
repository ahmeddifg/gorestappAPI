package com.gorestapp.gorest.authconfig;

import com.gorestapp.gorest.integration.responseModel.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthAccount implements UserDetails {

    public long userId;

    public String userName;
    public String password;

    public String email;

    public int isActive;

    public String token;

   public UserAuthAccount(UserAccount userAccount){
        this.userId=userAccount.getId();
        this.email=userAccount.getEmail();
        this.userName=userAccount.getName();
        this.isActive=userAccount.getStatus() !=null && userAccount.getStatus().equals("active")?1:0;
        this.password="123";
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
