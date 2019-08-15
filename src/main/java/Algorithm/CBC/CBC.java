package Algorithm.CBC;

import Algorithm.AES.AESMain;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class CBC {
    private  byte[] byteValueOfFile;
    private  String hexValueOfFile;
    private  String name;
    private  String iv;

    public  String FileToHexa(Path path) throws IOException {
        byteValueOfFile = Files.readAllBytes(path);
        hexValueOfFile = DatatypeConverter.printHexBinary(byteValueOfFile);
        int i = 0;
        while ((hexValueOfFile.length() % 32) != 0) {
            hexValueOfFile += 0;
            i++;
        }
        return hexValueOfFile;
    }

    public  void FileGenerator(String hexInput,Path path) throws IOException {
        byte[] byteInput = DatatypeConverter.parseHexBinary(hexInput);
//        File dir = new File("User");
//        dir.mkdir();
//        File newFile = new File("user/" + name);
//        newFile.createNewFile();


        Files.write(path, byteInput, StandardOpenOption.APPEND);
    }

    public  String[] XorOperation(String[] p, String[] q) {
        String[] xorP = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            xorP[i] = Integer.toHexString(Integer.parseInt(p[i], 16) ^ Integer.parseInt(q[i], 16));
        }
        return xorP;

    }

    public  String[][] To2dArray(String p) {
        String[][] q = new String[p.length() / 32][32];
        int temp = 0;
        for (int i = 0; i < (p.length() / 32); i++) {
            for (int j = 0; j < 32; j++) {
                q[i][j] = Character.toString(p.charAt(temp));
                temp++;
            }
        }
        return q;
    }

    public  String InitialVector() {
        byte[] byteIV = new byte[16];
        new Random().nextBytes(byteIV);
//        return DatatypeConverter.printHexBinary(byteIV);
        return "12daf3d3a4b324c4e5f41fc3b23ed33b";
    }

    public  String ArrayToString(String[] p) {
        String q = "";
        for (int i = 0; i < p.length; i++) {
            q += p[i];
        }
        return q;
    }

    public  String[][] HexaOfFile(Path path) {
        String hexString = new String();
        try {
            hexString = FileToHexa(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return To2dArray(hexString);
    }

    public  void CbcEncrypt(Path path,String fileName,File storePath,String key) throws IOException {
        String[][] plain = HexaOfFile(path);
        String[] xorHexString;

        Path writePath=Paths.get(storePath+"/"+fileName);
        iv = "12daf3d3a4b324c4e5f41fc3b23ed33b";
        iv = iv.toUpperCase();
        AESMain aesMain = new AESMain();
        String input;
        String output;
        for (int i = 0; i < plain.length; i++) {
            xorHexString = XorOperation(plain[i], iv.split(""));
            input = ArrayToString(xorHexString);
            aesMain.main(input, 1,key);
            output = aesMain.getCipherhex();
            iv = output;
            FileGenerator(output,writePath);
        }
    }

    public  void CbcDecrypt(Path path, String fileName,File storePath,String key) throws IOException {
        String[][] cipher = HexaOfFile(path);
        String[] xorHexString;
        iv = "12daf3d3a4b324c4e5f41fc3b23ed33b";
        iv = iv.toUpperCase();
        Path writePath=Paths.get(storePath+"/"+fileName);
        AESMain aesMain = new AESMain();
        String input;
        String output;
        for (int i = 0; i < cipher.length; i++) {
            input = ArrayToString(cipher[i]).toLowerCase();
            aesMain.main(input, 0,key);
            output = aesMain.getCipherhex();
            xorHexString = XorOperation(output.split(""), iv.split(""));
            iv = input;
            FileGenerator(ArrayToString(xorHexString), writePath);
        }
    }

//    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("C:/Users/Anup/Desktop/SecureBit/src/111.png");
//        name = "encrypted.txt";
//        CbcEncrypt(path);
//        path = Paths.get("C:/Users/Anup/Desktop/SecureBit/User/" + name);
//        name = "decrypted.jpg";
//        CbcDecrypt(path);
//    }

}