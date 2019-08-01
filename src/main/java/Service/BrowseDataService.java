package Service;

import Data.FileData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrowseDataService {

private ConnectToDB connectToDB=new ConnectToDB();
private ConnectToDB connectToDB1=new ConnectToDB();
    public List<FileData> getData(int ownerId)throws SQLException
    {
        String query="Select * from files where ownerId<>?";
        Connection connection=connectToDB.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,ownerId);
        ResultSet resultSet=preparedStatement.executeQuery();

        String query1="select * from users where id=?";
        Connection connection1=connectToDB1.getConnection();
        PreparedStatement preparedStatement1=connection1.prepareStatement(query1);


        List<FileData> fileDataLists=new ArrayList<>();
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

        connection.close();

        return fileDataLists;
    }
}
