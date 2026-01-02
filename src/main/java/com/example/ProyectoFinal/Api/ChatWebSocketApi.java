package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.ConversacionBl;
import com.example.ProyectoFinal.Dto.ChatMessageDto;
import com.example.ProyectoFinal.Dto.ConversacionDto;
import com.example.ProyectoFinal.Entity.Conversacion;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ChatWebSocketApi {

    @Autowired
    private ConversacionBl conversacionBl;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/enviar")
    public void enviarMensaje(ChatMessageDto message) {

        ConversacionDto saved = conversacionBl.guardarMensajeWs(message);

        // ChatWebSocketApi
        String topicChat = "/topic/chat/" + saved.getIdTurista() + "/" + saved.getIdEmpresa();
        messagingTemplate.convertAndSend(topicChat, saved);

        String topicEmpresa = "/topic/empresa/" + saved.getIdEmpresa();
        messagingTemplate.convertAndSend(topicEmpresa, saved);

    }

}
