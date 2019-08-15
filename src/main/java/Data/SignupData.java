package Data;

import Encoder.PasswordEncoder;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class SignupData extends PasswordEncoder implements Serializable {
    private String fullName;
    private String Email;
    private String userName;
    private String Password;
    private int userId;
    private String encodedPassword;

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {

        try {
            this.Password = sha512PasswordEncoder(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
