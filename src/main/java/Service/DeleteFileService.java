package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.io.*;

public class DeleteFileService {
private ConnectToDB connectToDB=new ConnectToDB();
    public void deleteFile(int ownerId,int fileId,String totalPath) throws SQLException
    {
        deleteFileFromServer(totalPath,fileId);

        String query="DELETE from files where ownerId=? And fileId=?";
        Connection connection=connectToDB.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,ownerId);
        preparedStatement.setInt(2,fileId);
        preparedStatement.execute();
        connection.close();



    }
    public void deleteFileFromServer(String totalPath,int fileId)throws SQLException
    {
String query="Select * from files where fileId=?";
Connection connection=connectToDB.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement(query);
preparedStatement.setInt(1,fileId);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next())
        {

            String  name=resultSet.getString("encryptedname");
            File totalPathTOFile=new File(totalPath+name+".txt");
            if(totalPathTOFile.exists())
            {

                totalPathTOFile.delete();
            }

        }
        connection.close();



    }
}
