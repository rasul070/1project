import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Создание списка вопросов
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"}, 2));
        questions.add(new Question("What is the boiling point of water in Celsius?", new String[]{"50", "100", "150", "200"}, 2));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 4));
        questions.add(new Question("Who painted the Mona Lisa?", new String[]{"Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"}, 2));
        questions.add(new Question("What is the chemical symbol for gold?", new String[]{"Ag", "Au", "Fe", "Hg"}, 2));
        questions.add(new Question("Which country has the largest population?", new String[]{"India", "USA", "China", "Russia"}, 3));
        questions.add(new Question("What is the square root of 64?", new String[]{"6", "7", "8", "9"}, 3));
        questions.add(new Question("Which element has the atomic number 1?", new String[]{"Oxygen", "Hydrogen", "Helium", "Carbon"}, 2));

        // Создание кандидата
        Candidate candidate = new Candidate("Rassul Tokatov", 17, "rtokatov@gmail.com");

        // Создание экзамена
        Exam exam = new Exam("General Knowledge Test", questions, candidate);

        // Сортировка вопросов перед экзаменом
        System.out.println("Sorting questions by text...");
        exam.sortQuestionsByText();

        // Отображение отсортированных вопросов
        System.out.println("Sorted Questions:");
        for (Question q : exam.getQuestions()) {
            System.out.println(q);
        }

        // Фильтрация вопросов по ключевому слову
        String keyword = "planet";
        System.out.println("\nSearching for questions with keyword: " + keyword);
        List<Question> filteredQuestions = exam.searchQuestions(keyword);
        System.out.println("Filtered Questions:");
        for (Question q : filteredQuestions) {
            System.out.println(q);
        }

        // Проведение экзамена
        System.out.println("\nStarting the exam...");
        exam.conductExam();
    }
}
