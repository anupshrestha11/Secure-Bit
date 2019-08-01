package Service;

import Data.SignupData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private ConnectToDB connectToDB=new ConnectToDB();
    public boolean checkUserForLogin(SignupData signupData) throws SQLException
    {
        String query="Select * from users where username=? AND password=?";
        Connection connection=connectToDB.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(query);
preparedStatement.setString(1,signupData.getUserName());
preparedStatement.setString(2,signupData.getPassword());
        ResultSet resultSet=preparedStatement.executeQuery();

        if(resultSet.next())
        {
signupData.setUserId(resultSet.getInt("id"));
signupData.setUserName(resultSet.getString("username"));
signupData.setEmail(resultSet.getString("email"));
signupData.setFullName(resultSet.getString("name"));
return true;
        }

else {

            return false;
        }

    }




}
