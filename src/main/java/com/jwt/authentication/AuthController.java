package com.jwt.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//
//    @Autowired
//    JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username) {
//        System.out.println("qwertyuiop");
//        return jwtUtil.generateToken(username);
//    }
//
//    @GetMapping("/validate")
//    public boolean validateToken(@RequestParam String token) {
//        return jwtUtil.validateToken(token);
//    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;


    // Login endpoint that generates a token after successful authentication
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        try {
            System.out.println("here");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token, @RequestParam String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.validateToken(token, userDetails);
    }
}
