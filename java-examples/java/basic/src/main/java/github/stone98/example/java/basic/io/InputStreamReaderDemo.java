package github.stone98.example.java.basic.io;

import github.stone98.example.java.basic.config.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(Constants.PATH), "utf-8");
        int len;
        while ((len = reader.read()) != -1) {
            System.out.print((char) len);
        }
        reader.close();
    }
}
