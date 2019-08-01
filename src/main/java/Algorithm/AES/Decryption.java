package Algorithm.AES;


import java.util.Arrays;

public class Decryption {
    private static AESMain aesMain = new AESMain();


    public static void decrypt(String input) {
//        aesMain.keyToAes("Thats my Kung Fu");
        aesMain.keyToAes(aesMain.getKey());
        System.out.println(aesMain.getKey());
        String[][][] returnedAllKey = new String[11][4][4];
        String[][][] reversedKey = new String[11][4][4];

        returnedAllKey = aesMain.getAllRoundKeys();
        reversedKey = reverseTheKeys(reversedKey, returnedAllKey);
        System.out.println(Arrays.deepToString(reversedKey));
        aesMain.setAllRoundKeys(reversedKey);
        aesMain.inputToAes(input);
    }

    private static String[][][] reverseTheKeys(String[][][] reversedKey, String[][][] returnedAllKey) {
        int k = 10;
        for (int l = 0; l < 11; l++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    reversedKey[k][i][j] = returnedAllKey[l][i][j];
                }
            }
            k--;
        }
        return reversedKey;


    }

}
