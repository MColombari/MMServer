
public class Question {
    public int qid;
    public int idCourse;
    public String lastChanges;
    public String questionText;
    public String rightAnswer;
    public String wrongAnswer1;
    public String wrongAnswer2;
    public String wrongAnswer3;

    public Question(int qid, int idCourse, String lastChanges, String questionText, String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        this.qid = qid;
        this.idCourse = idCourse;
        this.lastChanges = lastChanges;
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    /*      Getter      */
    public int getQid() {
        return qid;
    }
    public int getIdCourse() {
        return idCourse;
    }
    public String getLastChanges() {
        return lastChanges;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    /*      Setter      */
    public void setQid(int qid) {
        this.qid = qid;
    }
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
    public void setLastChanges(String lastChanges) {
        this.lastChanges = lastChanges;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }
    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }
    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    /*      toString        */
    @Override
    public String toString() {
        return "Question{" +
                "qid=" + qid +
                ", lastChanges='" + lastChanges + '\'' +
                ", questionText='" + questionText + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", wrongAnswer1='" + wrongAnswer1 + '\'' +
                ", wrongAnswer2='" + wrongAnswer2 + '\'' +
                ", wrongAnswer3='" + wrongAnswer3 + '\'' +
                '}';
    }
}