package com.gorestapp.gorest.authconfig;


import com.gorestapp.gorest.entities.UserAuthAccount;
import com.gorestapp.gorest.entities.repos.UserAuthAccountRepo;
import com.gorestapp.gorest.exceptions.exceptionTypes.AlreadyRegisteredException;
import com.gorestapp.gorest.exceptions.exceptionTypes.LoginException;
import com.gorestapp.gorest.integration.GorestAPIClient;
import com.gorestapp.gorest.integration.responseModel.RegisterUserAccountResponse;
import com.gorestapp.gorest.integration.responseModel.UserAccount;
import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import com.gorestapp.gorest.model.request.RegisterRequest;
import com.gorestapp.gorest.model.response.RegisterResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    public GorestAPIClient gorestAPIClient;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthAccountRepo userAuthAccountRepo;

    @Value("${gorest.token}")
    private String gorestToken;


    @Override
    public UserAuthAccount loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuthAccount userAuthAccount = null;
        if (email.contains("@"))
            userAuthAccount = this.userAuthAccountRepo.findByEmail(email);
        else userAuthAccount = this.userAuthAccountRepo.findByUserName(email);

        if (userAuthAccount == null)
            throw new LoginException();
        return userAuthAccount;

    }

    public boolean validatePassword(String password, String encryptPassword) {
        return this.passwordEncoder.matches(password, encryptPassword);
    }

    public String generateTokenFromUserDetails(UserAuthAccount userAuthAccount) {
        return this.jwtTokenUtil.generateToken(userAuthAccount);
    }

    public RegisterResponse registerNewUser(RegisterRequest empAccount) {
        empAccount.setPassword(passwordEncoder.encode(empAccount.getPassword()));
        UserAuthAccount userAuthAccount = new UserAuthAccount(empAccount);
        UserApiResponse userAccountResponse = this.gorestAPIClient.findUserByEmail(empAccount.getEmail());

        if (userAccountResponse.getData() != null && userAccountResponse.getData()!=null && userAccountResponse.getData().size() > 0 ) {
            userAuthAccount.setGorestId(userAccountResponse.getData().get(0).getId());
            try {
                this.userAuthAccountRepo.save(userAuthAccount);
            }catch (Exception e){
                throw  new AlreadyRegisteredException();
            }
            return new RegisterResponse("You successfully registered");
        } else return new RegisterResponse("You are trying to register with email not listed in gorest users ");
    }


}