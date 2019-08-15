package Service;

import Data.FileRequestData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDeleteService {
    private ConnectToDB connectToDB = new ConnectToDB();

    public void deleteRequest(int requestId) throws SQLException {
        String query = "DELETE from requests where requestId=?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, requestId);
        FileRequestData fileRequestData = new FileRequestData();
        fileRequestData = getInfo(requestId);
        SendingEmail sendingEmail = new SendingEmail();
        preparedStatement.execute();
        sendingEmail.mail("Rejected", fileRequestData.getRequestingUserEmail(), "", fileRequestData.getFileName(), false);

        connection.close();

    }

    public FileRequestData getInfo(int requestId) throws SQLException {
        String query = "Select * from requests where requestId = ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, requestId);
        ResultSet resultSet = preparedStatement.executeQuery();
        FileRequestData fileRequestData = new FileRequestData();
        if (resultSet.next()) {
            fileRequestData.setFileName(resultSet.getString("fileName"));
            fileRequestData.setRequestingUserEmail(resultSet.getString("reuestingEmail"));
        }
        connection.close();
        return fileRequestData;
    }
}
