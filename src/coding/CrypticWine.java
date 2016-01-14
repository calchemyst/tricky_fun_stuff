package coding;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.*;

/**
 * Created by annafuller on 12/20/15.
 */
public final class CrypticWine {

    private static char[][] vigenereSquare = generateVigenere("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    static int letterFrequency(String phrase, char letter) {
        int freq = 0;
        char[] letters = phrase.toCharArray();
        for (char c : letters) {
            if (c == letter) {
                freq++;
            }
        }
        return freq;
    }

    public static String decode(String decoderText, String toDecipher) {
        char[] text = toDecipher.toCharArray();
        char[] decoder = decoderText.toCharArray();
        int decoderLength = decoder.length;
        int counter = 0;
        char[] decodedText = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            if (counter < decoderLength) {
                decodedText[i] = getCharAtRow(decoder[counter], text[i]);
                counter++;
            } else {
                counter = 0;
                decodedText[i] = getCharAtRow(decoder[counter], text[i]);
                counter++;
            }
        }
        return Arrays.toString(decodedText);
    }

    private static char getCharAtRow(char r, char b) {
        int row = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (r == alphabet[i]) {
                row = i;
            }
        }
        char[] chars = vigenereSquare[row];
        final char c = '\0';
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == b) {
                return alphabet[i];
            }
        }
        System.out.println(" ");
        return c;
    }

    public static void main(String[] args) {
        String signatureBlend = "BENECATRGIUNSVAONTEPTEIRIHSADALNIFZEN";
        System.out.println(decode("CALIFORNIAREDWINE", signatureBlend));
    }

    static char[][] generateVigenere(String alphabetString) {
        char[] alphabet = alphabetString.toCharArray();
        char[][] square = new char[26][26];
        int counter = 0;
        for (int i =0 ; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                square[i][j] = alphabet[(counter+j)%26];
            }
            counter++;
        }
        return square;
    }
}


