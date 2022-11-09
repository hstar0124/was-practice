package com.example;

import com.example.calculator.Calculator;
import com.example.calculator.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustemWebApplicationServer {

    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustemWebApplicationServer.class);

    public CustemWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustemWebApplicationServer] started {} port", port);

            Socket clientSocker;
            logger.info("[CustemWebApplicationServer] waiting for client");

            while ((clientSocker = serverSocket.accept()) != null) {
                logger.info("[CustemWebApplicationServer] client connected!");

                new Thread(new ClientRequestHandler(clientSocker)).start();

            }

        }
    }
}
