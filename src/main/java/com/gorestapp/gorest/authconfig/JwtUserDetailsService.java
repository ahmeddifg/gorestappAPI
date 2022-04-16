package com.gorestapp.gorest.authconfig;


import com.gorestapp.gorest.exceptions.exceptionTypes.LoginException;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Data
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    public GorestAPIClient gorestAPIClient;
    //    @Autowired
//    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserAuthAccount loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApiResponse userApiResponse = null;
        if(email.contains("@"))
        userApiResponse = this.gorestAPIClient.findUserByEmail(email);
        else userApiResponse = this.gorestAPIClient.findUserByName(email);

        if (userApiResponse.getData().size() == 0)
            throw new LoginException();
        return new UserAuthAccount(userApiResponse.getData().get(0));
    }

    public String generateTokenFromUserDetails(UserAuthAccount userAuthAccount) {
        return this.jwtTokenUtil.generateToken(userAuthAccount);
    }

//    public void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new LoginException();
//        }
//    }

}