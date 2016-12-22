package Database;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// FIXME: beter singleton? thread safe? ... nu elke keer instance maken om te gebruiken, dodgy
/**
 * Created by qmanna on 7/11/2016.
 */
public class Database {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://172.21.18.24:3306/Spaceraider", "kevin", "resultSet123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String email,String username,String password,int score) { // TODO : prevent sql injection
        String query = "INSERT INTO User(Email,Username, Password,Score) VALUES(?,?,?,?)";
        System.out.println(checkUserAvailable(username));

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4,0);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }



    }
    public boolean getUsername(String username) { // TODO : prevent sql injection
        boolean login = false;
       String query = "SELECT Username FROM User WHERE Username = ?";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String dbuserName = resultSet.getString("Username");

                if(dbuserName.equals(username)){
                    System.out.println("OK");
                    login = true;}
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }
    public boolean getPassword(String username, String password) { // TODO : prevent sql injection
        boolean login = false;
       String query = "SELECT password FROM User WHERE username = ?";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dbpassword = resultSet.getString("Password");
                if(dbpassword.equals(password)){

                login = true;
            }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public boolean checkUserAvailable(String username){
        String query = "SELECT Username FROM User WHERE Username = ?";
        boolean available = true;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                available = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available;
    }
    public boolean checkEmailAvailable(String email){
        String query = "SELECT Email FROM User WHERE Email = ?";
        boolean available = true;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                available = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available;
    }
    public List<UserScore> getHighscores(){
        String query = "SELECT Username, Score FROM User ORDER BY Score DESC";

        List<UserScore> highscoreList = new ArrayList<UserScore>();

        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            //System.out.println(resultSet);
            while (resultSet.next()){

                highscoreList.add( new UserScore(resultSet.getString("Username"),resultSet.getInt("Score")));
            }
            resultSet.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return highscoreList;
    }

    public void updateHighscore(int userId,int score){
        String query = "UPDATE User SET SCORE = ? where UserID = ?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,score);
            preparedStatement.setInt(2,userId);
            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // FIXME: ook niet beter met UserScore ? (ook al is geen list nodig hier)
    public Map<String,Integer> getHighscoreUser(int userID){
        String query = "SELECT Score FROM User WHERE UserID = ?";
        Map<String,Integer> highscore = new HashMap<String,Integer>();
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userID);
            resultSet = preparedStatement.executeQuery();
            //System.out.println(resultSet);
            while (resultSet.next()){
                highscore.put(resultSet.getString("Username"),resultSet.getInt("Score"));
            }
            resultSet.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return highscore;
    }


}
