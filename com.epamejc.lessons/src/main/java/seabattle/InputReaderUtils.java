package seabattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class InputReaderUtils {

    private InputReaderUtils() {
    }

    public static String nextString() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = "";
        try {
            inputString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputString;
    }

    public static String nextString(BufferedReader bufferedReader) {
        String inputString = "";
        try {
            inputString = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputString;
    }

    public static int nextInt(BufferedReader bufferedReader) {
        int value = 0;
        try {
            value = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Wrong number format");
            System.out.println("Input val = 0 now");
            e.printStackTrace();
        }
        return value;
    }

}
