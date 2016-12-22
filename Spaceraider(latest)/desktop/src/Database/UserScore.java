package Database;

/**
 * Created by Kevin on 22/12/2016.
 */
public class UserScore {
    private String userName;
    private int score;

    public UserScore(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }
    public String toString(){
        return userName+" has a highest score of " + score+".";
    }
}
