package comjwtdb.Controller;

import comjwtdb.Entity.RefreshToken;
import comjwtdb.Entity.User;
import comjwtdb.Model.JwtRequest;
import comjwtdb.Model.JwtResponse;
import comjwtdb.Model.RefreshTokenRequest;
import comjwtdb.Security.JwtHelper;
import comjwtdb.Service.RefreshTokenService;
import comjwtdb.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private RefreshTokenService refreshTokenService;



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String tok = this.helper.generateToken(userDetails);

        RefreshToken refreshToken=refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse response = JwtResponse.builder()
                .token(tok)
                .refreshToken(refreshToken.getRefreshToken())
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }


    @PostMapping("/refresh")
    public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request)
    {
        RefreshToken refreshToken=refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        User user=refreshToken.getUser();

        String token=this.helper.generateToken(user);

        return JwtResponse.builder().refreshToken(refreshToken.getRefreshToken())
                .token(token)
                .username(user.getEmail())
                .build();
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }
}