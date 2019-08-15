package Service;

import Data.SignupData;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupService {
    private ConnectToDB connectToDB = new ConnectToDB();

    public Boolean saveUser(SignupData signupData) throws SQLException {
        if (checkIfUserIsUnique(signupData)) {
            String query = "Insert into users(name,username,email,password) values(?,?,?,?)";
            Connection connection = connectToDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, signupData.getFullName());
            preparedStatement.setString(2, signupData.getUserName());
            preparedStatement.setString(3, signupData.getEmail());
            preparedStatement.setString(4, signupData.getPassword());
            preparedStatement.execute();
            connection.close();

            return true;
        } else {

            return false;
        }

    }

    public Boolean checkIfUserIsUnique(SignupData signupData) throws SQLException {

        String query = "Select * from users where username=? or email = ?";

        Connection connection = connectToDB.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, signupData.getUserName());
        preparedStatement.setString(2, signupData.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            connection.close();

            return false;

        } else {
            connection.close();


            return true;
        }


    }
}

