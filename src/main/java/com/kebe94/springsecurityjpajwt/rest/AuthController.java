package com.kebe94.springsecurityjpajwt.rest;

import com.kebe94.springsecurityjpajwt.auth.AccountService;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {
    private AccountService accountService;
    Map<String, String> errers;
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> registration(@RequestBody AppUser userForm, BindingResult result) {

        if(result.hasErrors()) {
            errers = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errers.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errers, HttpStatus.NOT_ACCEPTABLE);
        }
        AppUser existing = accountService.loadUserByUsername(userForm.getUsername());
        if (existing!=null) {
            throw new RuntimeException("Votre addresse mail,  n'est pas disponible.");
        }

        return  new ResponseEntity<>(accountService.addUser(userForm), HttpStatus.OK);
    }
}
