package Service;

import Data.FileData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileUploadService {
    private ConnectToDB connectToDB = new ConnectToDB();

    public void saveFile(FileData fileData) throws SQLException {
        String query = "Insert into files(ownerId,originalname,encryptedname,encryptionKey,filedescription,date) values(?,?,?,?,?,?)";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fileData.getOwnerId());
        preparedStatement.setString(2, fileData.getOrginalName());
        preparedStatement.setString(3, fileData.getEncryptedName());
        preparedStatement.setString(4, fileData.getKeyForEncryption());
        preparedStatement.setString(5, fileData.getFileDescription());
        preparedStatement.setString(6, fileData.getDate());
        preparedStatement.execute();
        connection.close();


    }

}
