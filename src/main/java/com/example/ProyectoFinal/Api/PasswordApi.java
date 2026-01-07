package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.AuthBl;
import com.example.ProyectoFinal.Dto.ForgotPasswordRequest;
import com.example.ProyectoFinal.Dto.ResentPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordApi {
    @Autowired private AuthBl authBl;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody ForgotPasswordRequest req) {
        authBl.solicitarRecuperacion(req.getCorreo());
        // respuesta neutra
        return ResponseEntity.ok("SI_EXISTE_SE_ENVIO_CORREO");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody ResentPasswordRequest req) {
        authBl.confirmarRecuperacion(req.getToken(), req.getNuevaPassword());
        return ResponseEntity.ok("PASSWORD_ACTUALIZADA");
    }

}
