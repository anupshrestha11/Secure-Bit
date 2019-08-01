package Service;

import Data.FileData;
import Data.FileRequestData;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestFileService {
    private ConnectToDB connectToDB = new ConnectToDB();
    private FileRequestData fileRequestData = new FileRequestData();
     private FileData fileData=new FileData();

    public void getFromFileId(int fileId, HttpServletRequest request) throws SQLException {
        String query = "Select * from files where fileId=?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fileId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            fileRequestData.setFileId(resultSet.getInt("fileId"));
            fileRequestData.setOwnerId(resultSet.getInt("ownerId"));
            fileRequestData.setFileDescription(resultSet.getString("filedescription"));
            fileRequestData.setFileName(resultSet.getString("originalname"));
            fileRequestData.setRequestingUserName((String) request.getSession().getAttribute("userFullName"));
            fileRequestData.setRequestingUserEmail((String) request.getSession().getAttribute("userEmail"));

        }
        checkIfRequestExists(fileRequestData, request);
    }

    public void addIntoRequestTable(FileRequestData fileRequestData, HttpServletRequest request) throws SQLException {
        String query = "INSERT into requests(ownerId,fileId,filediscription,fileName,requestingUser,reuestingEmail,accept) values(?,?,?,?,?,?,?)";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fileRequestData.getOwnerId());
        preparedStatement.setInt(2, fileRequestData.getFileId());
        preparedStatement.setString(3, fileRequestData.getFileDescription());
        preparedStatement.setString(4, fileRequestData.getFileName());
        preparedStatement.setString(5, fileRequestData.getRequestingUserName());
        preparedStatement.setString(6, fileRequestData.getRequestingUserEmail());
        preparedStatement.setBoolean(7, false);
        preparedStatement.execute();

    }

    public void checkIfRequestExists(FileRequestData fileRequestData, HttpServletRequest request) throws SQLException {
        String query = "Select * from requests where ownerId=? AND fileId=? AND reuestingEmail=?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, fileRequestData.getOwnerId());
        preparedStatement.setInt(2, fileRequestData.getFileId());
        preparedStatement.setString(3,fileRequestData.getRequestingUserEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            request.getSession().setAttribute("uploadMessage", "You Have Pending Request For File");
            return;

        } else {

            addIntoRequestTable(fileRequestData, request);
        }

    }

    public FileData RequestAccepted(int requestId) throws SQLException {
        String query = "UPDATE requests SET accept=? WHERE requestId = ?";
        Connection connection = connectToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBoolean(1, true);
        preparedStatement.setInt(2, requestId);
        preparedStatement.execute();

        String q1 = "Select * From files where fileId = (Select fileId from requests where requestId =? and accept = ?) ";
        PreparedStatement preparedStatement1 = connection.prepareStatement(q1);
        preparedStatement1.setInt(1,requestId);
        preparedStatement1.setBoolean(2,true);
        ResultSet resultSet=preparedStatement1.executeQuery();

        String q2 = "select * from requests where requestId = ?";
        PreparedStatement preparedStatement3 = connection.prepareStatement(q2);
        preparedStatement3.setInt(1,requestId);
        ResultSet resultSet3 = preparedStatement3.executeQuery();
        if (resultSet3.next()){
            fileData.setEmail(resultSet3.getString("reuestingEmail"));
        }

        if(resultSet.next())
        {
            fileData.setKeyForEncryption(resultSet.getString("encryptionKey"));

        }


        String query1="select * from users where email=?";
        PreparedStatement preparedStatement2=connection.prepareStatement(query1);

preparedStatement2.setString(1,fileData.getEmail());
        ResultSet resultSet1=preparedStatement2.executeQuery();
        if(resultSet1.next())
        {

            fileData.setRequestingUserName(resultSet1.getString("username"));


        }

return fileData;


    }

}
