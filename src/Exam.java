import java.util.*;

public class Exam {
    private String examTitle;
    private List<Question> questions;//пулл данных так как это список и благодороя этому можно ими управлять
    private Candidate candidate;

    public Exam(String examTitle, List<Question> questions, Candidate candidate) {
        this.examTitle = examTitle;
        this.questions = questions;
        this.candidate = candidate;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;//пулл данных вопросов
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void conductExam() {
        System.out.println("Welcome to the " + examTitle + " exam!");
        candidate.displayDetails();

        int score = 0;
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer (enter option number): ");
            int answer = scanner.nextInt();
            if (question.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong answer.");
            }
        }

        System.out.println("Exam finished! " + candidate.getName() + ", your score is: " + score + "/" + questions.size());
    }

    @Override
    public String toString() {
        return "Exam: " + examTitle + ", Candidate: " + candidate + ", Questions: " + questions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exam exam = (Exam) obj;
        return Objects.equals(examTitle, exam.examTitle) &&
                Objects.equals(questions, exam.questions) &&
                Objects.equals(candidate, exam.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examTitle, questions, candidate);
    }

    public List<Question> searchQuestions(String keyword) {
        List<Question> results = new ArrayList<>();
        for (Question q : questions) {
            if (q.getQuestionText().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(q);//фильтрация по ключевому слову
            }
        }
        return results;//возврощяет отфильтрованный список
    }

    public void sortQuestionsByText() {
        questions.sort(Comparator.comparing(Question::getQuestionText));//сортировка по тексту вопроса(сортинг)
    }
}
