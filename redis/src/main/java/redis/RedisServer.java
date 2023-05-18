package redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Classe qui représente le serveur Redis 
public class RedisServer implements Runnable{
	
	
	private ServerSocket serverSocket;  //Pour écouter les connexions 
    private ExecutorService executorService;  //Pour gérer plusieurs clients 
    private Map<String, String> data;  //Pour stocker les données sous la forme de clé valeur 
    
    
    private static final int PORT = 6379; // Port Redis par défaut
    private static final int MAX_CLIENTS = 10; // Nombre maximal de clients simultanés

    public static void main(String[] args) {
        (new RedisServer()).run();
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur Redis en attente de connexions sur le port " + PORT + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new Worker(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            executorService.shutdown();
        }
    }

    private static class Worker implements Runnable {
        private final Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()) {
                StringBuilder current = new StringBuilder(80);
                byte[] buffer = new byte[20];

                int numRead = in.read(buffer);
                while (numRead != -1) {
                    int pos = indexOf(buffer, (byte) '\n');
                    if (pos != -1) {
                        current.append(new String(buffer, 0, pos + 1, StandardCharsets.UTF_8));
                        System.out.println("Reçu : " + current);

                        // Traitez la commande Redis ici et stockez-la quelque part

                        out.write(current.toString().toUpperCase().getBytes(StandardCharsets.UTF_8));

                        current.setLength(0);
                        current.append(new String(buffer, pos + 1, numRead - pos - 1, StandardCharsets.UTF_8));
                    } else {
                        current.append(new String(buffer, 0, numRead, StandardCharsets.UTF_8));
                    }
                    numRead = in.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        private int indexOf(byte[] buffer, byte b) {
            for (int k = 0; k < buffer.length; k++) {
                if (buffer[k] == b) {
                    return k;
                }
            }
            return -1;
        }
	
    }

}
