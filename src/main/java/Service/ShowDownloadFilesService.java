package Service;

import Data.FileData;
import Data.FileRequestData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowDownloadFilesService {
    private FileRequestData fileRequestData = new FileRequestData();
    ConnectToDB connectToDB = new ConnectToDB();

    public List<FileData> getAllDownloadFiles(String email) throws SQLException {
        String query = "SELECT fileId FROM requests where reuestingEmail=? and accept=?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setBoolean(2, true);
        ResultSet resultSet = preparedStatement.executeQuery();

        String query1 = "Select * from files where fileId = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

        String query2 = "select * from users where id=?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);


        List<FileData> fileDataList = new ArrayList<>();
        while (resultSet.next()) {
            FileData fileData = new FileData();
            preparedStatement1.setInt(1, resultSet.getInt("fileId"));
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {

                preparedStatement2.setInt(1,resultSet1.getInt("ownerId"));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()){
                    fileData.setOwnerName(resultSet2.getString("name"));
                    fileData.setEmail(resultSet2.getString("email"));
                }
                fileData.setOrginalName(resultSet1.getString("originalname"));
                fileData.setFileDescription(resultSet1.getString("filedescription"));
            }
            fileData.setFileId(resultSet.getInt("fileId"));
            fileDataList.add(fileData);

        }
        return fileDataList;
    }
}
