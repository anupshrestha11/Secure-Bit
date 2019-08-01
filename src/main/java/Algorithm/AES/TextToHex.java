package Algorithm.AES;
public class TextToHex {
    //converting input String to hex value from ASCII
    public String asciiToHex(String inputString) {
        String hexValueOfInput = "";
        for (int i = 0; i < inputString.length(); i++) {
            int intValue = (int) (inputString.charAt(i));
            String part = Integer.toHexString(intValue);
            hexValueOfInput += part;

        }
        return hexValueOfInput;
    }

    public String convertHexToString(String hex){


//        for(int i=0;i<hex.length();i++)
//        {
//            int decimal=Integer.parseInt(hex.charAt(i));
//
//        }
        StringBuilder sb = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){

            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);


        }

        return sb.toString();
    }

}
