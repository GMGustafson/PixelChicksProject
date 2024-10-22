package language;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataLoader class is responsible for loading data from the JSON files.
 * It parses the JSON data and converts it into a list of objects.
 */
public class DataLoader extends DataConstants{
    // Loads the lists of Users
    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<User>();
    try {
        FileReader reader = new FileReader(FILE_NAME_USER);
        JSONParser parser = new JSONParser();
        JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        for (int i=0; i < usersJSON.size(); i++) {
            JSONObject userJSON = (JSONObject)usersJSON.get(i);
            UUID id = UUID.fromString(String.valueOf(userJSON.get(USER_ID)));
            String firstName = (String)userJSON.get(USER_FIRST_NAME);
            String lastName = (String)userJSON.get(USER_LAST_NAME);
            String email = (String)userJSON.get(USER_EMAIL);
            String phoneNumber = (String)userJSON.get(USER_PHONE_NUMBER);
            String dobString = (String) userJSON.get(USER_DATE_OF_BIRTH);
            LocalDate dateOfBirth = null;
            dateOfBirth = LocalDate.parse(dobString, formatter);
            String username = (String)userJSON.get(USER_USERNAME);
            String password = (String)userJSON.get(USER_PASSWORD);
            String 
            User newUser = new User(id, firstName, lastName, email, phoneNumber, dateOfBirth, username, password);
            userList.add(newUser);
        }   
        return userList;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

public static ArrayList<Language> getLanguages () {
    ArrayList<Language> languageList = new ArrayList<Language>();
    try {
        FileReader reader = new FileReader(FILE_NAME_CATEGORY);
        JSONParser parser = new JSONParser();
        JSONArray languagesJSON = (JSONArray)new JSONParser().parse(reader);

        for (int i=0; i < languagesJSON.size(); i++) {
            JSONObject languageJSON = (JSONObject)languagesJSON.get(i);
            UUID languageID = UUID.fromString(String.valueOf(languageJSON.get(LANGUAGE_ID)));
            String language = (String)languageJSON.get(LANGUAGE);
            String title = (String)languageJSON.get(CATEGORY_TITLE);
            String question = (String)languageJSON.get(CATEGORY_QUESTION);
            String currentCategory = (String)languageJSON.get(CURRENT_CATEGORY);
            int totalQuestionsAnswered = ((Long)languageJSON.get(TOT_QUESTIONS_ANSWERED)).intValue();
            int progressInCategory = ((Long)languageJSON.get(PROGRESS_IN_CATEGORY)).intValue();
            int numCorrectAnswers = ((Long)languageJSON.get(NUM_CORRECT_ANSWERS)).intValue();
            JSONArray missedWordsJSON = (JSONArray)languageJSON.get(MISSED_WORDS);
            ArrayList<String> missedWords = new ArrayList<>();
            for (Object word : missedWordsJSON) {
                missedWords.add((String) word);
            }

           Category newCategory = new Category(currentCategory, totalQuestionsAnswered, progressInCategory, numCorrectAnswers, missedWords);
           Language newLanguage = new Language(languageID, language, title, question, newCategory);
           languageList.add(newLanguage);

        }
        return languageList;
    }
    catch (Exception e) {
    e.printStackTrace();
    }
return null;
}

// Main method to test getUsers

public static void main(String[] args) {
    ArrayList<User> users = getUsers();
    if (users != null) {
        if (users.isEmpty()) {
            System.out.println("No users found in the data.");
        } else {
            for (User user : users) {
                System.out.println("User: " + user.getFirstName() + " " + user.getLastName() + ", Email: " + user.getEmail() + " Date of Birth: " + user.getDateOfBirth());
            }
        }
    } else {
        System.out.println("Failed to load user data.");
    }
}


// Loads the list of flashcards
    public static List<Flashcards> loadFlashcards() {
        List<Flashcards> flashcards = new ArrayList<>();

        // Try to read and parse the JSON file
        try (FileReader reader = new FileReader(FILE_NAME_FLASHCARDS)) {
            JSONParser jsonParser = new JSONParser();
            
            // Parse the JSON array from the file
            Object obj = jsonParser.parse(reader);
            JSONArray flashcardList = (JSONArray) obj;

            // Iterate through each JSON object in the array and convert it to a Flashcard
            for (Object flashcardObject : flashcardList) {
                JSONObject flashcardJSON = (JSONObject) flashcardObject;

                String word = (String) flashcardJSON.get("word");
                String translation = (String) flashcardJSON.get("translation");
                String phrase = (String) flashcardJSON.get("phrase");
                
                // Create a new Flashcard object and add it to the list
                Flashcards flashcard = new Flashcards(word, translation, phrase);
                flashcards.add(flashcard);
            }
        }  catch (IOException | ParseException e) {
            e.printStackTrace();  // Handle errors in reading or parsing the file
        }

        return flashcards;  // Return the list of flashcards
    }

    
    // loads the list of questions
    public static List<Question> loadQuestions() {
        List<Question> questionsList = new ArrayList<>();

        // Try to read and parse the JSON file
        try (FileReader reader = new FileReader(FILE_NAME_QUESTIONS)) {
            JSONParser jsonParser = new JSONParser();
            
            // Parse the JSON array from the file
            Object obj = jsonParser.parse(reader);
            JSONArray flashcardList = (JSONArray) obj;

            // Iterate through each JSON object in the array and convert it to a Flashcard
            for (Object flashcardObject : flashcardList) {
                JSONObject flashcardJSON = (JSONObject) flashcardObject;

                String questionFromJson = (String) flashcardJSON.get("Question");
                
                
                // Create a new Flashcard object and add it to the list
                Question question = new Question(questionFromJson);
                questionsList.add(question);
            }
        }  catch (IOException | ParseException e) {
            e.printStackTrace();  // Handle errors in reading or parsing the file
        }

        return questionsList;  // Return the list of questions
    }

    // Method to convert JSONArray to a String array
     private static ArrayList<String> convertJsonArrayToStringArray(JSONArray jsonArray) {
        // Create a String array with the same size as the JSONArray
        ArrayList<String> missedWords = new ArrayList<>();

        // Loop through the JSONArray and extract each element as a String
        for (int i = 0; i < jsonArray.size(); i++) {
            missedWords.add((String) jsonArray.get(i)); // Cast each object to a String
        }

        // Return the String array
        return missedWords;
    }
        
}
