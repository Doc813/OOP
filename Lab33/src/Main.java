import Parser.*;

import Parser.INIData;

import java.io.*;

public class Main {
    public static void main(String[] argc) throws IOException {
        INIData data = new INIData("G:\\Учеба\\University\\2год\\OOP\\ALL\\Lab33\\qwert.ini");

        data.parse();

        data.searching("q");
    }
}
