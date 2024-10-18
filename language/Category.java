package language;
/**
 * @author sri
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Category{
    /**
     * attributes for story class
    */
    private String course;
    private ArrayList<Question> questions;
    private Story story;

    public Category(String course, ArrayList<Question> questions, Story story){
        this.course = course;
        this.questions = questions;
        this.story = story;
    }

    // Getters and setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String toString(){
        return "";
    }

    public void listCategories(ArrayList<Category> categories){
        System.out.println("Categories to choose from:");
        for(Category category : categories){
            System.out.println(category.course);
        }
    }

    public String chooseCategory(ArrayList<String> categoryNames){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a category");

        for (int i = 0; i < categoryNames.size(); i++) {
            System.out.println((i + 1) + ". " + categoryNames.get(i));
        }

        // checks the user entered a valid number
        int choice = 0;
        while (choice < 1 || choice > categoryNames.size()) {
            System.out.print("Enter the number for the category you want to choose: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > categoryNames.size()) {
                    System.out.println("Invalid choice. Please select a valid category number.");
                }
            } else {
                System.out.println("Please enter a number.");
                scanner.next(); 
            }
        }
        // show which category the user chose
        String selectedCategory = categoryNames.get(choice - 1);
        System.out.println("You have chosen: " + selectedCategory);
        return selectedCategory;
    }

    public void saveCategory(){
        System.out.println( course + " saved successfully!");
    }

    public static void main(String[] args) {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Words");
        categories.add("Phrases");
        categories.add("Mock Conversations");
        categories.add("Fill in the Blank");
        categories.add("Flashcards");
        categories.add("Matching");

        Category category = new Category("", new ArrayList<Question>(), new Story());
        category.chooseCategory(categories);
    }

}