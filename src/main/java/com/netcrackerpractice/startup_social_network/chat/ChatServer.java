package com.netcrackerpractice.startup_social_network.chat;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.netcrackerpractice.startup_social_network.converter.MessageConverter;
import com.netcrackerpractice.startup_social_network.entity.Message;
import com.netcrackerpractice.startup_social_network.model.MessageModel;
import com.netcrackerpractice.startup_social_network.security.JwtTokenProvider;
import com.netcrackerpractice.startup_social_network.service.MessageService;
import com.netcrackerpractice.startup_social_network.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ChatServer {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MessageServiceImpl messageServiceImpl;

    private SocketIOServer socketIOServer;
    private Map<UUID, SocketIOClient> users;

    public ChatServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10000);

        socketIOServer = new SocketIOServer(config);
        users = new HashMap<>();

        setupListeners();
        socketIOServer.start();
    }

    private void setupListeners() {
        socketIOServer.addConnectListener(socketIOClient -> {
            String token = socketIOClient.getHandshakeData().getHttpHeaders().get("token");
            String userId = socketIOClient.getHandshakeData().getHttpHeaders().get("userId");

            if (token != null && userId != null && jwtTokenProvider.validateToken(token)) {
                users.put(UUID.fromString(userId), socketIOClient);
            } else {
                MessageModel messageModel = new MessageModel();
                messageModel.setMsg("You don't have enough access rights");
                messageModel.setCreationDate(new Timestamp(System.currentTimeMillis()));
                socketIOClient.sendEvent("access_denied", messageModel);
                socketIOClient.disconnect();
            }
        });

        socketIOServer.addEventListener("new_message", MessageModel.class, (socketIOClient, messageModel, ackRequest) -> {
            if (users.get(messageModel.getReceiverId()) != null) {
                users.get(messageModel.getReceiverId()).sendEvent("new_message", messageModel);
            }
            messageServiceImpl.addMessage(MessageConverter.toMessageEntity(messageModel));

            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData(1);
            }
        });

        socketIOServer.addDisconnectListener(socketIOClient -> {
            for (Map.Entry<UUID, SocketIOClient> entry : users.entrySet()) {
                if (entry.getValue() == socketIOClient) {
                    users.remove(entry.getKey());
                    entry.getValue().disconnect();
                    break;
                }
            }
        });
    }
}
