import java.util.*;

public class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean checkAnswer(int selectedOption) {
        return selectedOption == correctOption;
    }

    @Override
    public String toString() {
        return "Question: " + questionText + ", Options: " + Arrays.toString(options);//инхеритансе, но не полный, но будет если добавить новые классы в Экхам
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return correctOption == question.correctOption &&
                Objects.equals(questionText, question.questionText) &&
                Arrays.equals(options, question.options);//позволяет объектам разных классов быть обработанными единообразно
    }

    @Override
    public int hashCode() {//возвращает числовой код, связанный с объектом.
        int result = Objects.hash(questionText, correctOption);
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }
}