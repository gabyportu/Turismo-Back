package com.example.ProyectoFinal.Api;

import com.example.ProyectoFinal.Bl.ConversacionBl;
import com.example.ProyectoFinal.Dto.ChatMessageDto;
import com.example.ProyectoFinal.Entity.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketApi {

    @Autowired
    private ConversacionBl conversacionBl;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/enviar")
    public void enviarMensaje(ChatMessageDto message) {

        Conversacion saved = conversacionBl.guardarMensaje(message);

        // Canal único por turista-empresa
        String topic = "/topic/chat/"
                + message.getIdTurista()
                + "/"
                + message.getIdEmpresa();

        messagingTemplate.convertAndSend(topic, saved);
    }
}
