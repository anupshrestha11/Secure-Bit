package Service;

import Data.FileData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDataRetriveService {
private ConnectToDB connectToDB=new ConnectToDB();
public List<FileData> getFileDatas(int ownerID) throws SQLException
{
    String query="Select * from files where ownerId=?";
    Connection connection=connectToDB.getConnection();
    PreparedStatement preparedStatement=connection.prepareStatement(query);
    preparedStatement.setInt(1,ownerID);
    ResultSet resultSet=preparedStatement.executeQuery();
    List<FileData>fileDataList=new ArrayList<>();
    while (resultSet.next())
    {
        FileData fileData=new FileData();
        fileData.setFileId(resultSet.getInt("fileId"));
fileData.setOrginalName(resultSet.getString("originalname"));
fileData.setDate(resultSet.getString("date"));
fileData.setEncryptedName(resultSet.getString("encryptedname"));
fileData.setKeyForEncryption(resultSet.getString("encryptionKey"));
fileData.setFileDescription(resultSet.getString("filedescription"));
fileDataList.add(fileData);


    }
    connection.close();

    return fileDataList;

}


}
