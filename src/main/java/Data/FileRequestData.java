package Data;

public class FileRequestData {

    private int fileId;
    private int ownerId;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    private int requestId;
    private String fileName;
    private String requestingUserName;
    private String requestingUserEmail;
    private String fileDescription;
    private boolean requestAccept;

    public boolean isRequestAccept() {
        return requestAccept;
    }

    public void setRequestAccept(boolean requestAccept) {
        this.requestAccept = requestAccept;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRequestingUserName() {
        return requestingUserName;
    }

    public void setRequestingUserName(String requestingUserName) {
        this.requestingUserName = requestingUserName;
    }

    public String getRequestingUserEmail() {
        return requestingUserEmail;
    }

    public void setRequestingUserEmail(String requestingUserEmail) {
        this.requestingUserEmail = requestingUserEmail;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }
}
