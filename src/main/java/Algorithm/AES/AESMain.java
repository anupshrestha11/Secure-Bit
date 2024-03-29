package Algorithm.AES;

import java.util.ArrayList;

public class AESMain {
    private String hexValueOfKey;
    public int encryptionAndDecryptionSeperator = 3;
    private Constants constants;
    private TextToHex textToHex;
    private String[][][] allRoundKeys = new String[11][4][4];
    private String hexValueofInput;
    private String inputState[][] = new String[4][4];
    private int roundNumber = 0;
    private ArrayList<String> Cipher = new ArrayList<>();
    private String cipherhex;
    private String key;

    public void setKey(String keyforAes) {

        this.key = keyforAes;
    }

    public String getKey() {
        return this.key;
    }

    public String getCipherhex() {
        return cipherhex;
    }

    public void main(String input, int a,String key) {

        setKey(key);

        if (a == 0) {
            encryptionAndDecryptionSeperator = 0;
            decrypt(input);
        }
        if (a == 1) {
            encryptionAndDecryptionSeperator = 1;
            keyToAes(getKey());
            inputToAes(input);
        }
    }

    public void decrypt(String input) {
//        aesMain.keyToAes("Thats my Kung Fu");
        keyToAes(getKey());
        String[][][] returnedAllKey = new String[11][4][4];
        String[][][] reversedKey = new String[11][4][4];

        returnedAllKey = getAllRoundKeys();
        reversedKey = reverseTheKeys(reversedKey, returnedAllKey);
        setAllRoundKeys(reversedKey);
        inputToAes(input);
    }

    private  String[][][] reverseTheKeys(String[][][] reversedKey, String[][][] returnedAllKey) {
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

    //////////////////////////////////key Expansion
    public void keyToAes(String key128Bit) {
//omly 128 bit key
        String key = key128Bit;
        textToHex = new TextToHex();
        hexValueOfKey = textToHex.asciiToHex(key);
        //making key 128 bit if its not of that size
        while ((hexValueOfKey.length() < 32)) {
            hexValueOfKey = hexValueOfKey + "0";


        }
        fillKeyState();
    }


    //filling 128 bit key into state
    private void fillKeyState() {

        int tempVariable = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char a = hexValueOfKey.charAt(tempVariable);
                char b = hexValueOfKey.charAt(tempVariable + 1);
                String ab = Character.toString(a) + Character.toString(b);
                allRoundKeys[0][j][i] = ab;
                tempVariable = tempVariable + 2;
            }
        }
        for (int k = 1; k < 11; k++) {
            keyExpansion(k);
        }
    }

    private void keyExpansion(int k) {
        String[] word1 = new String[4];
        String[] word2 = new String[4];
        String[] word3 = new String[4];
        String[] word4 = new String[4];
        String[] tempWOrd4 = new String[4];
        for (int j = 0; j < 4; j++) {
            word1[j] = allRoundKeys[k - 1][j][0];
            word2[j] = allRoundKeys[k - 1][j][1];
            word3[j] = allRoundKeys[k - 1][j][2];
            word4[j] = allRoundKeys[k - 1][j][3];
            tempWOrd4[j] = allRoundKeys[k - 1][j][3];
        }
        tempWOrd4 = rotWord(tempWOrd4);
        tempWOrd4 = byteSub(tempWOrd4);
        tempWOrd4 = addRoundConstant(tempWOrd4, k);
        word1 = xorWithOtherWords(tempWOrd4, word1);
        addWordtoAllRoundKey(0, k, word1);
        word2 = xorWithOtherWords(word2, word1);
        addWordtoAllRoundKey(1, k, word2);
        word3 = xorWithOtherWords(word3, word2);
        addWordtoAllRoundKey(2, k, word3);
        word4 = xorWithOtherWords(word4, word3);
        addWordtoAllRoundKey(3, k, word4);


    }

    private String[] rotWord(String wordFour[]) {
        String temp = wordFour[0];
        wordFour[0] = wordFour[1];
        wordFour[1] = wordFour[2];
        wordFour[2] = wordFour[3];
        wordFour[3] = temp;
        return wordFour;
    }

    private String[] byteSub(String wordFour[]) {
        for (int i = 0; i < 4; i++) {
            String temp = wordFour[i];
            constants = new Constants();
            wordFour[i] = constants.getSboxValue(Character.getNumericValue(temp.charAt(0)), Character.getNumericValue(temp.charAt(1)));
        }
        return wordFour;
    }

    private String[] addRoundConstant(String wordFour[], int k) {
        for (int i = 0; i < 4; i++) {
            String temp = wordFour[i];
            constants = new Constants();
            String temp1 = constants.getRoundConstat(i, k - 1);
            String addRoundFirst = Integer.toHexString(Character.getNumericValue(temp.charAt(0)) ^ Character.getNumericValue(temp1.charAt(0)));
            addRoundFirst = addRoundFirst + Integer.toHexString(Character.getNumericValue(temp.charAt(1)) ^ Character.getNumericValue(temp1.charAt(1)));
            wordFour[i] = addRoundFirst;


        }

        return wordFour;
    }

    private String[] xorWithOtherWords(String[] firstWord, String[] secondWord) {
        for (int i = 0; i < 4; i++) {
            String temp = firstWord[i];
            String temp1 = secondWord[i];
            String xorFirst = Integer.toHexString(Character.getNumericValue(temp.charAt(0)) ^ Character.getNumericValue(temp1.charAt(0)));
            xorFirst = xorFirst + Integer.toHexString(Character.getNumericValue(temp.charAt(1)) ^ Character.getNumericValue(temp1.charAt(1)));
            secondWord[i] = xorFirst;

        }

        return secondWord;

    }

    private void addWordtoAllRoundKey(int colNumber, int k, String[] word) {
        for (int j = 0; j < 4; j++) {

            allRoundKeys[k][j][colNumber] = word[j];
        }

    }

    public String[][][] getAllRoundKeys() {

        return allRoundKeys;

    }

    public void setAllRoundKeys(String[][][] reversedRoundKeys) {
        allRoundKeys = reversedRoundKeys;

    }
/////////////////////////keyExpanison ENds


    public void inputToAes(String inupt) {
        String tempInputOf128Bit = "";
        hexValueofInput = inupt;
        for (int i = 0; i < hexValueofInput.length(); i++) {
            tempInputOf128Bit += Character.toString(hexValueofInput.charAt(i));
            if ((i != 0) && ((i + 1) % 32 == 0)) {
                plainTextToState(tempInputOf128Bit);
                aesRounds();
                tempInputOf128Bit = "";
            }

        }

    }

    private void plainTextToState(String inputToState) {
        int tempVariable = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char a = inputToState.charAt(tempVariable);
                char b = inputToState.charAt(tempVariable + 1);
                String ab = Character.toString(a) + Character.toString(b);
                inputState[j][i] = ab;
                tempVariable = tempVariable + 2;
            }
        }


    }


    //Aes Rounds
    String[] keyword1 = new String[4];
    String[] keyword2 = new String[4];
    String[] keyword3 = new String[4];
    String[] keyword4 = new String[4];
    String[] inputword1 = new String[4];
    String[] inputword2 = new String[4];
    String[] inputword3 = new String[4];
    String[] inputword4 = new String[4];

    private void inputStateToWord() {
        for (int j = 0; j < 4; j++) {

            inputword1[j] = inputState[j][0];
            inputword2[j] = inputState[j][1];
            inputword3[j] = inputState[j][2];
            inputword4[j] = inputState[j][3];

        }


    }

    private void keyStateToWord(int roundNo) {
        for (int j = 0; j < 4; j++) {
            keyword1[j] = allRoundKeys[roundNo][j][0];
            keyword2[j] = allRoundKeys[roundNo][j][1];
            keyword3[j] = allRoundKeys[roundNo][j][2];
            keyword4[j] = allRoundKeys[roundNo][j][3];

        }
    }

    private void aesRounds() {

        inputStateToWord();
        if (roundNumber == 0) {
            keyStateToWord(roundNumber);
            xorInROunds();
            roundNumber++;
        }

        while (roundNumber != 10) {
            keyStateToWord(roundNumber);
            if (encryptionAndDecryptionSeperator == 1) {

                subBytesInROunds();
                rotWordsInRound(inputword1, inputword2, inputword3, inputword4);
                callMixColumns();
                xorInROunds();
            }
            if (encryptionAndDecryptionSeperator == 0) {
                rotWordsInRound(inputword1, inputword4, inputword3, inputword2);

                subBytesInROunds();
                xorInROunds();
                callMixColumns();
            }
            roundNumber++;
        }

        if (roundNumber == 10) {
            keyStateToWord(roundNumber);
            if (encryptionAndDecryptionSeperator == 1) {
                subBytesInROunds();
                rotWordsInRound(inputword1, inputword2, inputword3, inputword4);
                xorInROunds();
            }
            if (encryptionAndDecryptionSeperator == 0) {
                rotWordsInRound(inputword1, inputword4, inputword3, inputword2);
                subBytesInROunds();
                xorInROunds();
            }
        }
        roundNumber = 0;
        addStateToCipherList();
    }

    private void addStateToCipherList() {
        String cipherOfTheState = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    cipherOfTheState += inputword1[j];

                } else if (i == 1) {
                    cipherOfTheState += inputword2[j];

                } else if (i == 2) {

                    cipherOfTheState += inputword3[j];
                } else if (i == 3) {
                    cipherOfTheState += inputword4[j];
                }
            }


        }
        cipherhex = cipherOfTheState;
        textToHex = new TextToHex();
        Cipher.add(textToHex.convertHexToString(cipherOfTheState));
    }

    private void callMixColumns() {
        if (encryptionAndDecryptionSeperator == 1) {
            inputword1 = mixColumns(inputword1);
            inputword2 = mixColumns(inputword2);
            inputword3 = mixColumns(inputword3);
            inputword4 = mixColumns(inputword4);
        }
        if (encryptionAndDecryptionSeperator == 0) {
            inputword1 = InversemixColumns(inputword1);
            inputword2 = InversemixColumns(inputword2);
            inputword3 = InversemixColumns(inputword3);
            inputword4 = InversemixColumns(inputword4);
        }
    }

    private void subBytesInROunds() {
        if (encryptionAndDecryptionSeperator == 1) {
            inputword1 = byteSub(inputword1);
            inputword2 = byteSub(inputword2);
            inputword3 = byteSub(inputword3);
            inputword4 = byteSub(inputword4);
        }
        if (encryptionAndDecryptionSeperator == 0) {
            inputword1 = inverseByteSub(inputword1);
            inputword2 = inverseByteSub(inputword2);
            inputword3 = inverseByteSub(inputword3);
            inputword4 = inverseByteSub(inputword4);
        }
    }

    private void xorInROunds() {
        inputword1 = xorWithOtherWords(keyword1, inputword1);
        inputword2 = xorWithOtherWords(keyword2, inputword2);
        inputword3 = xorWithOtherWords(keyword3, inputword3);
        inputword4 = xorWithOtherWords(keyword4, inputword4);
    }

    private void rotWordsInRound(String[] word1, String[] word2, String[] word3, String[] word4) {
        //have to hard code due to manipulating words instead of matrix
        String temp, temp1, temp2;
        temp = word1[1];
        word1[1] = word2[1];
        word2[1] = word3[1];
        word3[1] = word4[1];
        word4[1] = temp;

        temp = word1[2];
        temp1 = word2[2];
        word1[2] = word3[2];
        word2[2] = word4[2];
        word3[2] = temp;
        word4[2] = temp1;

        temp = word1[3];
        temp1 = word2[3];
        temp2 = word3[3];
        word1[3] = word4[3];
        word2[3] = temp;
        word3[3] = temp1;
        word4[3] = temp2;
    }

    private String[] mixColumns(String[] word) {
        constants = new Constants();
        String tempString;
        String[] returnArray = new String[4];
        for (int i = 0; i < 4; i++) {
            tempString = "00000000";
            int[] getMixColRow = constants.getRoundMatrixRow(i);
            for (int j = 0; j < 4; j++) {
                int decimal = Integer.parseInt(word[j], 16);
                String binaryNo = Integer.toBinaryString(decimal);
                while (binaryNo.length() != 8) {
                    binaryNo = "0" + binaryNo;
                }
                if (getMixColRow[j] == 2) {
                    tempString = (xorTwoStrings(tempString, leftShiftString(binaryNo)));
                    continue;
                }
                if (getMixColRow[j] == 3) {
                    tempString = xorTwoStrings(tempString, xorTwoStrings(leftShiftString(binaryNo), binaryNo));
                    continue;
                }
                tempString = xorTwoStrings(tempString, binaryNo);
            }
            String finalHexValue = Integer.toHexString(Integer.parseInt(tempString, 2));
            while (finalHexValue.length() != 2) {
                finalHexValue = "0" + finalHexValue;
            }
            returnArray[i] = finalHexValue;
        }
        return returnArray;
    }

    private String xorTwoStrings(String string1, String string2) {
        String finalResult = "";
        for (int i = 0; i < string1.length(); i++) {
            finalResult += string1.charAt(i) ^ string2.charAt(i);
        }
        return finalResult;
    }

    private String leftShiftString(String shiftThisString) {
        String result = "";
        char checkFirstBit = shiftThisString.charAt(0);
        for (int i = 1; i < shiftThisString.length(); i++) {
            result += shiftThisString.charAt(i);
        }
        result = result + "0";
        String bitValueOf1b = "00011011";
        if (checkFirstBit == '1') {
            result = xorTwoStrings(result, bitValueOf1b);
        }
        return result;
    }

    private String[] inverseByteSub(String wordFour[]) {
        for (int i = 0; i < 4; i++) {
            constants = new Constants();
            String temp = wordFour[i];
            wordFour[i] = constants.getInverseSboxValue(Character.getNumericValue(temp.charAt(0)), Character.getNumericValue(temp.charAt(1)));
        }
        return wordFour;
    }

    private String[] InversemixColumns(String[] word) {
        constants = new Constants();
        String tempString;
        String[] returnArray = new String[4];
        for (int i = 0; i < 4; i++) {
            tempString = "00000000";
            int[] getInverseMixRow = constants.getInverseMixColROw(i);
            for (int j = 0; j < 4; j++) {
                int decimal = Integer.parseInt(word[j], 16);
                String binaryNo = Integer.toBinaryString(decimal);
                while (binaryNo.length() != 8) {
                    binaryNo = "0" + binaryNo;
                }
                if (getInverseMixRow[j] == 9) {
                    String temp = "";

                    temp = leftShiftString(binaryNo);
                    temp = leftShiftString(temp);
                    temp = leftShiftString(temp);

                    tempString = xorTwoStrings(tempString, xorTwoStrings(temp, binaryNo));
                    continue;
                }

                if (getInverseMixRow[j] == 11) {
                    String temp = "";

                    temp = leftShiftString(binaryNo);
                    temp = leftShiftString(temp);

                    temp = xorTwoStrings(temp, binaryNo);
                    temp = leftShiftString(temp);
                    tempString = xorTwoStrings(tempString, xorTwoStrings(temp, binaryNo));
                    continue;
                }

                if (getInverseMixRow[j] == 13) {
                    String temp = "";
                    temp = leftShiftString(binaryNo);
                    temp = xorTwoStrings(temp, binaryNo);

                    temp = leftShiftString(temp);
                    temp = leftShiftString(temp);

                    tempString = xorTwoStrings(tempString, xorTwoStrings(temp, binaryNo));
                    continue;
                }
                if (getInverseMixRow[j] == 14) {
                    String temp = "";
                    temp = leftShiftString(binaryNo);
                    temp = xorTwoStrings(temp, binaryNo);
                    temp = leftShiftString(temp);
                    temp = xorTwoStrings(temp, binaryNo);
                    temp = leftShiftString(temp);
                    tempString = xorTwoStrings(tempString, temp);
                    continue;
                }

            }

            String finalHexValue = Integer.toHexString(Integer.parseInt(tempString, 2));
            while (finalHexValue.length() != 2) {
                finalHexValue = "0" + finalHexValue;

            }
            returnArray[i] = finalHexValue;

        }

        return returnArray;
    }

}