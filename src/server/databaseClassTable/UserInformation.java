package server.databaseClassTable;

public class UserInformation {
    public String name;
    public String surname;
    public String email;
    public int matr;

    public UserInformation(String name, String surname, String email, int matr) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.matr = matr;
    }

    /*      Getter.     */
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public int getMatr() {
        return matr;
    }

    /*      Setter.     */
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMatr(int matr) {
        this.matr = matr;
    }

    /*      toString.       */
    @Override
    public String toString() {
        return "UserInformation{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", matr=" + matr +
                '}';
    }
}
