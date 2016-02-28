package Util;

/**
 * Created by s on 2/28/16.
 */
public class Util {

    public static String firstWord(String x) {
        int n = 0;
        while(n < x.length()) {
            char c = x.charAt(n);
            if(c == ' ' || c == '<' || c == '>')
                break;
            n++;
        }
        return x.substring(0, n);
    }
}
