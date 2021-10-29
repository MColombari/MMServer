package server;

public class Main {
    public static void main(String[] args){
        ServerMM serverMM = new ServerMM(3000);
        serverMM.startServer();
    }
}
