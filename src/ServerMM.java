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
        courseArrayList.add(new Course(3, "Inglese", "2021/2022", "Prof. ___", "Corso B1 inglese."));
        courseArrayList.add(new Course(4, "Geometria", "2021/2022", "Prof. Casali", "Corso di algebra lineare e geometria."));
        courseArrayList.add(new Course(5, "Fondamenti di Informatica 1", "2021/2022", "Prof. ___", "Corso fondamenti di informatica"));

        /* Analisi 1 */
        questionArrayList.add(new Question(1, 1, "20211025", "2 + 2", "4", "5", "10", "-e"));
        questionArrayList.add(new Question(2, 1, "20211025", "2 - 2", "0", "5", "10", "-e"));
        questionArrayList.add(new Question(3, 1, "20211025", "2 + 22", "24", "5", "10", "-e"));
        questionArrayList.add(new Question(4, 1, "20211025", "2 * 2", "4", "5", "10", "-e"));
        questionArrayList.add(new Question(5, 1, "20211025", "2 / 2", "1", "5", "10", "-e"));

        /* Analisi 2 */
        questionArrayList.add(new Question(6, 2, "20211025", "2 ^ 2", "4", "5", "10", "-e"));
        questionArrayList.add(new Question(7, 2, "20211025", "10 * 10", "100", "5", "10", "-e"));
        questionArrayList.add(new Question(8, 2, "20211025", "10 / 10", "1", "5", "10", "-e"));
        questionArrayList.add(new Question(9, 2, "20211025", "2 ^ 10", "1024", "5", "10", "-e"));
        questionArrayList.add(new Question(10, 2, "20211025", "2 ^ 8", "256", "5", "10", "-e"));

        /* Inglese */
        questionArrayList.add(new Question(11, 3, "20211025", "Translate: \"Dog\"", "Cane", "Gatto", "Mattia", "Lucertola"));
        questionArrayList.add(new Question(12, 3, "20211025", "Translate: \"Hello\"", "Ciao", "Gatto", "Buona sera", "Addio"));
        questionArrayList.add(new Question(13, 3, "20211025", "Translate: \"Object-oriented programming\"", "Programmazione orientata agli oggetti", "Analisi", "Matematica", "Geografia"));
        questionArrayList.add(new Question(14, 3, "20211025", "Translate: \"Door\"", "Porta", "Finestra", "Veranda", "Balcone"));
        questionArrayList.add(new Question(15, 3, "20211025", "Translate: \"Lizard\"", "Lucertola", "Gatto", "Cane", "Ciao"));

        /* Geometria */
        questionArrayList.add(new Question(16, 4, "20211025", "Quante facce ha un triangolo?", "3", "0", "1", "-1"));
        questionArrayList.add(new Question(17, 4, "20211025", "Quante facce ha un rettangolo?", "4", "0", "1", "-1"));
        questionArrayList.add(new Question(18, 4, "20211025", "Quanti angoli ha un triangolo?", "3", "0", "1", "-1"));
        questionArrayList.add(new Question(19, 4, "20211025", "Quanti angoli ha un rettangolo?", "4", "0", "1", "-1"));
        questionArrayList.add(new Question(20, 4, "20211025", "Quanti angoli ha un cerchio?", "0", "10", "1", "-1"));

        /* Fondamenti di informatica 1 */
        questionArrayList.add(new Question(21, 5, "20211025", "Converti in decimale la seguente stringa binaria: \"1001\"", "9", "0", "1", "7"));
        questionArrayList.add(new Question(22, 5, "20211025", "Converti in decimale la seguente stringa binaria: \"1101\"", "13", "0", "1", "7"));
        questionArrayList.add(new Question(23, 5, "20211025", "Converti in decimale la seguente stringa binaria: \"0001\"", "1", "0", "9", "7"));
        questionArrayList.add(new Question(24, 5, "20211025", "Converti in decimale la seguente stringa binaria: \"0000\"", "0", "1", "9", "7"));
        questionArrayList.add(new Question(25, 5, "20211025", "Converti in decimale la seguente stringa binaria: \"0111\"", "7", "1", "9", "0"));
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
