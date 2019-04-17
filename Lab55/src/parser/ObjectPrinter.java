package parser;

import xml.Tag;

import java.io.FileWriter;
import java.io.IOException;

public interface ObjectPrinter {
    void printObj(Tag var1);
    void printObj(Tag var1, FileWriter var2) throws IOException;
}
