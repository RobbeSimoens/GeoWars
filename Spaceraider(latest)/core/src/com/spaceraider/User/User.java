package com.spaceraider.User;

/**
 * Created by qmann on 8/11/2016.
 */
public class User {
    private String email;
    private String username;
    private String password;
    private long score;

    public User(String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.email = email;

    }


    public String getEmail() {
        return email;
    }

      public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SinglePlayer{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                '}';
    }
}
