package Service;

import Algorithm.CBC.CBC;

import javax.xml.bind.DatatypeConverter;

public class UniqueKeyGenerator {
    private String username1;
    private String username2;
    private String key;
    private String uniqueKey;


    public UniqueKeyGenerator(String Key, String userName1, String userName2) {
        this.username1 = DatatypeConverter.printHexBinary(userName1.getBytes());
        this.username2 = DatatypeConverter.printHexBinary(userName2.getBytes());
        this.key = DatatypeConverter.printHexBinary(Key.getBytes());
        System.out.println(Key);
        maintainSize();
        generateUniqueKey();

    }

    public void maintainSize() {
        if (username1.length()>username2.length()){
            if (username1.length()>key.length()){
                username2 = padding(username1,username2);
                key = padding(username1,key);
            }
            else {
                username1 = padding(key,username1);
                username2 = padding(key,username2);
            }
        }
        else if (username2.length()>username1.length()){
            if (username2.length()>key.length()){
                username1 = padding(username2,username1);
                key = padding(username2,key);
            }
            else {
                username1 = padding(key,username1);
                username2 = padding(key,username2);
            }
        }
        else if (username1.length()==username2.length()){
            if (username1.length()>key.length()){
                key = padding(username1,key);
            }
        }
    }

    private String padding(String p, String q) {
        while ((q.length() % p.length()) != 0) {
            q += 0;
        }
        return q;
    }

    private void generateUniqueKey() {
        CBC cbc = new CBC();
        String[] random = cbc.XorOperation(username1.split(""), username2.split(""));
        uniqueKey = cbc.ArrayToString(cbc.XorOperation(random, key.split("")));

        String temp = "";
        String ntemp = "00000000";
        if (uniqueKey.length() > 8) {
            while ((uniqueKey.length() % 8) != 0) {
                uniqueKey += 0;
            }
            for (int i = 0; i < uniqueKey.length(); i++) {
                temp += uniqueKey.charAt(i);
                if ((temp.length() % 8) == 0) {
                    ntemp = cbc.ArrayToString(cbc.XorOperation(ntemp.split(""), temp.split("")));
                    temp = "";
                }
            }
            uniqueKey = ntemp;
        }
    }

    public String getUniqueKey() {
        return uniqueKey;
    }
}

