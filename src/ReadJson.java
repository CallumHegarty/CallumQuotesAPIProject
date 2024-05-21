import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson implements ActionListener {
    public static String displayedQuote;

    private JFrame mainFrame;
    private JPanel totalPanel;
    private JPanel buttonPanel;
    private JTextArea displayArea;

    private JButton ageButton;
    private JButton artButton;
    private JButton businessButton;
    private JButton changeButton;
    private JButton coolButton;
    private JButton deathButton;
    private JButton dreamsButton;
    private JButton educationButton;
    private JButton failureButton;
    private JButton familyButton;
    private JButton foodButton;
    private JButton happinessButton;
    private JButton hopeButton;
    private JButton knowledgeButton;
    private JButton lifeButton;
    private JButton loveButton;
    private JButton moneyButton;
    private JButton natureButton;
    private JButton powerButton;
    private JButton scienceButton;
    private JButton successButton;
    private JButton technologyButton;
    private JButton timeButton;
    private JButton truthButton;

    public ReadJson(){
        mainFrame = new JFrame("Magic Quote Machine");
        displayArea = new JTextArea(displayedQuote);
        totalPanel = new JPanel();
        buttonPanel = new JPanel();

        displayArea.setLineWrap(true);


        ageButton = new JButton("age");
        artButton = new JButton("art");
        businessButton = new JButton("business");
        changeButton = new JButton("change");
        coolButton = new JButton("cool");
        deathButton = new JButton("death");
        dreamsButton = new JButton("dreams");
        educationButton = new JButton("education");
        failureButton = new JButton("failure");
        familyButton = new JButton("family");
        foodButton = new JButton("food");
        happinessButton = new JButton("happiness");
        hopeButton = new JButton("hope");
        knowledgeButton = new JButton("knowledge");
        lifeButton = new JButton("life");
        loveButton = new JButton("love");
        moneyButton = new JButton("money");
        natureButton = new JButton("nature");
        powerButton = new JButton("power");
        scienceButton = new JButton("science");
        successButton = new JButton("success");
        technologyButton = new JButton("technology");
        timeButton = new JButton("time");
        truthButton = new JButton("truth");

        ageButton.addActionListener(this);
        artButton.addActionListener(this);
        businessButton.addActionListener(this);
        changeButton.addActionListener(this);
        coolButton.addActionListener(this);
        deathButton.addActionListener(this);
        dreamsButton.addActionListener(this);
        educationButton.addActionListener(this);
        failureButton.addActionListener(this);
        familyButton.addActionListener(this);
        foodButton.addActionListener(this);
        happinessButton.addActionListener(this);
        hopeButton.addActionListener(this);
        knowledgeButton.addActionListener(this);
        lifeButton.addActionListener(this);
        loveButton.addActionListener(this);
        moneyButton.addActionListener(this);
        natureButton.addActionListener(this);
        powerButton.addActionListener(this);
        scienceButton.addActionListener(this);
        successButton.addActionListener(this);
        technologyButton.addActionListener(this);
        timeButton.addActionListener(this);
        truthButton.addActionListener(this);

        int borderSize = 50;
        totalPanel.setBorder(BorderFactory.createEmptyBorder(borderSize,borderSize,borderSize,borderSize));
        totalPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(4,6));

        buttonPanel.add(ageButton);
        buttonPanel.add(artButton);
        buttonPanel.add(businessButton);
        buttonPanel.add(changeButton);
        buttonPanel.add(coolButton);
        buttonPanel.add(deathButton);
        buttonPanel.add(dreamsButton);
        buttonPanel.add(educationButton);
        buttonPanel.add(failureButton);
        buttonPanel.add(familyButton);
        buttonPanel.add(foodButton);
        buttonPanel.add(happinessButton);
        buttonPanel.add(hopeButton);
        buttonPanel.add(knowledgeButton);
        buttonPanel.add(lifeButton);
        buttonPanel.add(loveButton);
        buttonPanel.add(moneyButton);
        buttonPanel.add(natureButton);
        buttonPanel.add(powerButton);
        buttonPanel.add(scienceButton);
        buttonPanel.add(successButton);
        buttonPanel.add(technologyButton);
        buttonPanel.add(timeButton);
        buttonPanel.add(truthButton);

        totalPanel.add(buttonPanel, BorderLayout.NORTH);
        totalPanel.add(displayArea, BorderLayout.CENTER);
        mainFrame.add(totalPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String args[]) throws ParseException {
        new ReadJson();
    }

    public String genre = "";

    public void pull() throws ParseException {
        displayedQuote = "";

        try {
            String output;
            String totalJson = "";

            int page = (int)((Math.random()*80)+1);

            URL url = new URL("https://quote-garden.onrender.com/api/v3/quotes?genre="+genre+"&&page="+page);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            //200 is the response code that means it was successful
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((output = br.readLine()) != null) {
                totalJson += output;
            }
            conn.disconnect();

            try {
                JSONParser parser = new JSONParser();
                org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totalJson);

                JSONArray array = (JSONArray)jsonObject.get("data");

                JSONObject quoteObject = (JSONObject) array.get((int)(Math.random()*9)+1);
                displayedQuote += quoteObject.get("quoteText") + " - "+quoteObject.get("quoteAuthor")+"\n";
            }

            catch (ParseException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object buttonClicked = e.getSource();

        if(buttonClicked == ageButton){
            genre = "age";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == artButton){
            genre = "art";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == businessButton){
            genre = "business";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == changeButton){
            genre = "change";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == coolButton){
            genre = "cool";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == deathButton){
            genre = "death";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == dreamsButton){
            genre = "dreams";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == educationButton){
            genre = "education";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == failureButton){
            genre = "failure";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == familyButton){
            genre = "family";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == foodButton){
            genre = "food";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == happinessButton){
            genre = "happiness";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == hopeButton){
            genre = "hope";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == knowledgeButton){
            genre = "knowledge";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == lifeButton){
            genre = "life";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == loveButton){
            genre = "love";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == moneyButton){
            genre = "money";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == natureButton){
            genre = "nature";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == powerButton){
            genre = "power";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == scienceButton){
            genre = "science";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == successButton){
            genre = "success";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == technologyButton){
            genre = "technology";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == timeButton){
            genre = "time";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
        if(buttonClicked == truthButton){
            genre = "truth";
            try {
                pull();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            displayArea.setText(displayedQuote);
        }
    }
}