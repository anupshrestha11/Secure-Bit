package Service;

import Algorithm.AES.AESMain;
import Algorithm.CBC.CBC;
import Data.FileData;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadFileService {
    private FileData fileData = new FileData();
    private ConnectToDB connectToDB = new ConnectToDB();
    public FileData downloadFile(int fileId) throws SQLException{

        String query = "Select * from files where fileId = ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,fileId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()){
            return null;
        }
        else {
            fileData.setOrginalName(resultSet.getString("originalname"));
            fileData.setEncryptedName(resultSet.getString("encryptedname"));
            fileData.setOwnerId(resultSet.getInt("ownerId"));
            fileData.setKeyForEncryption(resultSet.getString("encryptionKey"));
        }
        String query1 = "select * from users where id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        preparedStatement1.setInt(1,fileData.getOwnerId());
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        if (resultSet1.next()){
            fileData.setOwnerUserName(resultSet1.getString("username"));
        }
        connection.close();

        return fileData;
    }

    public boolean CheckKey(String Key, int fileId) throws SQLException{
        String query = "select * from files where encryptionKey = ? and fileId = ?";

        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, Key);
        preparedStatement.setInt(2,fileId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()){
            connection.close();

            return false;
        }
        else {
            connection.close();

            return true;
        }

    }
}
