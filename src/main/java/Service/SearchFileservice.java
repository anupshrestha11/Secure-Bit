package Service;

import Data.FileData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFileservice {
    ConnectToDB connectToDB = new ConnectToDB();
    ConnectToDB connectToDB1 = new ConnectToDB();
    public List<FileData> SearchFile(String searchStr) throws SQLException {
        String query = "SELECT * FROM files WHERE originalname LIKE ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "%"+searchStr+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<FileData> fileDataLists=new ArrayList<>();

        String query1="select * from users where id=?";
        Connection connection1=connectToDB1.getConnection();
        PreparedStatement preparedStatement1=connection1.prepareStatement(query1);

        while(resultSet.next())
        {
            FileData fileData=new FileData();
            fileData.setOwnerId(resultSet.getInt("ownerId"));
            fileData.setFileId(resultSet.getInt("fileId"));
            fileData.setOrginalName(resultSet.getString("originalname"));
            fileData.setDate(resultSet.getString("date"));
            fileData.setEncryptedName(resultSet.getString("encryptedname"));
            fileData.setKeyForEncryption(resultSet.getString("encryptionKey"));
            fileData.setFileDescription(resultSet.getString("filedescription"));

            preparedStatement1.setInt(1,fileData.getOwnerId());
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
            {
                fileData.setOwnerName(resultSet1.getString("name"));
                fileData.setEmail(resultSet1.getString("email"));

            }

            fileDataLists.add(fileData);
        }

        return fileDataLists;
    }
}
