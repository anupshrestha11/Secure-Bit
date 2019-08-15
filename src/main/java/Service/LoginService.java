package Service;

import Data.SignupData;
import Encoder.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private ConnectToDB connectToDB = new ConnectToDB();

    public boolean checkUserForLogin(SignupData signupData) throws SQLException {
        String query = "Select * from users where username=? AND password=?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, signupData.getUserName());
        preparedStatement.setString(2, signupData.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            signupData.setUserId(resultSet.getInt("id"));
            signupData.setUserName(resultSet.getString("username"));
            signupData.setEmail(resultSet.getString("email"));
            signupData.setFullName(resultSet.getString("name"));
            signupData.setEncodedPassword(resultSet.getString("password"));
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }

    }

    public boolean checkPassword(String username, String password) throws SQLException {
        String query = "Select password from users where username = ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        try {
            password = passwordEncoder.sha512PasswordEncoder(password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultSet.next()) {
            password.equals(resultSet.getString("password"));
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }


}
