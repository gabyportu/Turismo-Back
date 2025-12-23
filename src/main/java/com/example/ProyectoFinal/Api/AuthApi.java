package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.AuthBl;
import com.example.ProyectoFinal.Dto.LoginRequestDto;
import com.example.ProyectoFinal.Dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthApi {
    @Autowired
    private AuthBl authBl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req){
        try{
            LoginResponseDto res = authBl.login(req);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
