package com.expense_tracker.expense.resources;

import com.expense_tracker.expense.Constants;
import com.expense_tracker.expense.services.UserService;
import com.expense_tracker.expense.store.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users/")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("all-users")
    public String allUsers(){
        return "All users was called";
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validate_user(email, password);
        return new ResponseEntity<>(generateToke(user), HttpStatus.OK);
    }

    @PostMapping("create_user")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.register_user(firstName, lastName, email, password);
        return new ResponseEntity<>(generateToke(user), HttpStatus.CREATED);
    }

    private Map<String, String> generateToke(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
