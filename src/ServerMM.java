import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMM{
    int port;

    ArrayList<Course> courseArrayList;
    ArrayList<Question> questionArrayList;

    public ServerMM(int port){
        this.port = port;

        courseArrayList = new ArrayList<>();
        questionArrayList = new ArrayList<>();

        courseArrayList.add(new Course(1, "Analisi 1", "2021/2022", "Prof. Manfredini", "Corso su derivate, integrali e studio di funzioni."));
        courseArrayList.add(new Course(2, "Analisi 2", "2021/2022", "Prof. Villarini", "Corso su serie, potenziale e derivate parziali."));

        questionArrayList.add(new Question(1, 1, "20211025", "2 + 2", "4", "5", "10", "-e"));
        questionArrayList.add(new Question(2, 1, "20211025", "2 - 2", "0", "5", "10", "-e"));
        questionArrayList.add(new Question(3, 1, "20211025", "2 + 22", "24", "5", "10", "-e"));
        questionArrayList.add(new Question(4, 1, "20211025", "2 * 2", "4", "5", "10", "-e"));
        questionArrayList.add(new Question(5, 1, "20211025", "2 / 2", "1", "5", "10", "-e"));
    }

    public void startServer() {
        System.out.println(" --- Server Ready --- ");
        while (true) {
            try {
                ServerSocket serverSock = new ServerSocket(3000);
                Socket socket = serverSock.accept();

                System.out.println("Client connected:");

                /* Initialize client-sever "two way" connection. */
                /* server -> client, out */
                OutputStream outStream = socket.getOutputStream();
                PrintWriter out= new PrintWriter(outStream, true);
                /* client -> server, in */
                InputStream inStream = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

                String inMessage;
                String name,surname,email;
                int matr = 0;

                if ((name = in.readLine()) == null) {
                    System.out.println("Error, name of user is \"null\"");
                    return;
                }
                if ((surname = in.readLine()) == null) {
                    System.out.println("Error, surname of user is \"null\"");
                    return;
                }
                if ((email = in.readLine()) == null) {
                    System.out.println("Error, email of user is \"null\"");
                    return;
                }
                if ((inMessage = in.readLine()) != null) {
                    matr = Integer.parseInt(inMessage);
                }
                else{
                    System.out.println("Error, \"matr\" of user is \"null\"");
                    return;
                }

                UserInformation userInformation = new UserInformation(name, surname, email, matr);

                System.out.println("\t- Client information: " + userInformation + ".");

                out.println("ACK");
                out.flush();

                out.println(Integer.toString(courseArrayList.size()));
                out.println(Integer.toString(questionArrayList.size()));
                out.flush();

                if(((inMessage = in.readLine()) == null) || (!inMessage.equals("ACK"))){
                    System.out.println("Error, \"ACK\" was not received");
                    return;
                }

                for(Course c: courseArrayList){
                    out.println(Integer.toString(c.getId()));
                    out.println(c.getName());
                    out.println(c.getAcademicYear());
                    out.println(c.getProfessor());
                    out.println(c.getDescription());
                    out.flush();
                }
                for(Question q: questionArrayList){
                    out.println(Integer.toString(q.getQid()));
                    out.println(Integer.toString(q.getIdCourse()));
                    out.println(q.getLastChanges());
                    out.println(q.getQuestionText());
                    out.println(q.getRightAnswer());
                    out.println(q.getWrongAnswer1());
                    out.println(q.getWrongAnswer2());
                    out.println(q.getWrongAnswer3());
                }

                if(((inMessage = in.readLine()) == null) || (!inMessage.equals("ACK"))){
                    System.out.println("Error, \"ACK\" was not received");
                    return;
                }

                System.out.println("\t- Operation succeeded.\n");

            /*pwrite.println("ok");
            pwrite.flush();
            if ((receiveMessage = receiveRead.readLine()) != null) {
                System.out.println(receiveMessage);
            }

            System.out.println("Conferma ricevuta");*/

            } catch (IOException e) {
            }
        }
    }
}
