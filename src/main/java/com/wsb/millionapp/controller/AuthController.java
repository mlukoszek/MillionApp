package com.wsb.millionapp.controller;

import com.wsb.millionapp.config.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = tokenProvider.generateToken(authentication);

//            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            String sessionId = request.getSession().getId();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Login successful");
            responseBody.put("sessionId", sessionId);
            HttpSession session = request.getSession(false);
            System.out.println("servlet context:" + session.getId());
            session.setAttribute("sessionId", session.getId());
            System.out.println("atrybut sessionId:" + session.getAttribute(sessionId));
            System.out.println("id sesji:" + session.getAttributeNames());

            System.out.println("Session ID: " + session.getId());
            System.out.println("Logged-in user: " + SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println("User authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

    @Getter
    @Setter
    class LoginRequest {
        private String username;
        private String password;

        // getters and setters
    }

//    @Getter
//    @Setter
//    public static class JwtAuthenticationResponse {
//        private String accessToken;
//
//        public JwtAuthenticationResponse(String accessToken) {
//            this.accessToken = accessToken;
//        }
//
//    }
//}