package Service;

public class MaintainSize {
    private String returnUserName = "";


    public String getMaintainedSize(String key, String userName) {

        if (key.length() != userName.length()) {
            if (key.length() < userName.length()) {

                returnUserName="";
                for (int i = 0; i < key.length(); i++) {

                    returnUserName += userName.charAt(i);

                }
            return returnUserName;
            }
                if (key.length() > userName.length()) {
                    returnUserName = userName;
                    for (int i = 0; i < key.length(); i++) {

                        returnUserName += 0;
                    }
                    return returnUserName;
                }


            }


         else {

            returnUserName = userName;

        }

        return  returnUserName;

    }

}

