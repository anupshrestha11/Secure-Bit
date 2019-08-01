package Service;

import Data.FileRequestData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowRequestFileService {
    private ConnectToDB connectToDB = new ConnectToDB();

    public List<FileRequestData> getAllRequestData(int sessionId) throws SQLException {
        String query = "select * from  requests where ownerId=? and accept = ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, sessionId);
        preparedStatement.setBoolean(2, false);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<FileRequestData> fileRequestDataList = new ArrayList<>();
        while (resultSet.next()) {
            FileRequestData fileRequestData = new FileRequestData();
            fileRequestData.setRequestId(resultSet.getInt("requestId"));
            fileRequestData.setFileName(resultSet.getString("fileName"));
            fileRequestData.setRequestingUserEmail(resultSet.getString("reuestingEmail"));
            fileRequestData.setRequestingUserName(resultSet.getString("requestingUser"));
            fileRequestData.setFileDescription(resultSet.getString("filediscription"));
            fileRequestDataList.add(fileRequestData);

        }
        return fileRequestDataList;

    }


}
