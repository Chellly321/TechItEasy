package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.payload.AuthenticationRequest;
import nl.novi.TechItEasy.payload.AuthenticationResponse;
import nl.novi.TechItEasy.service.CustomUserDetailService;
import nl.novi.TechItEasy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal){
        return ResponseEntity.ok().body(principal);
    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

    try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }
    catch (BadCredentialsException ex){
        throw new Exception("Incorrect username or password", ex);
    }

    final UserDetails userDetails = userDetailService
            .loadUserByUsername(username);

    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
