package banqueServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Représente un serveur TCP, qui écoute sur un numéro de port
 */
public class ServeurTCP {

    private final int PORT;
    private final int serverCapacity;
    public IBanque banque;

    ServeurTCP(int port) {
        PORT = port;
        serverCapacity = 1000;
    }

    public IBanque getBanque() {
        return banque;
    }

    public void setBanque(IBanque uneBanque) {
        banque = uneBanque;
    }

    public void go() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + PORT + ", " + e);
            System.exit(1);
        }

        for (int nbClient = 0; nbClient <= serverCapacity; nbClient++) {
            try {
                System.out.println(" Attente du serveur pour la communication d'un client ");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: " + 6666 + ", " + e);
                System.exit(1);
            }
            ServeurSpecifique st = new ServeurSpecifique(clientSocket, this);
            st.start();
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }

    @Override
    public String toString() {
        return "Serveur de banque avec une somme de :" + banque;
    }
}
