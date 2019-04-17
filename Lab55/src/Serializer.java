import parser.ObjectParser;
import parser.ObjectPrinter;
import xml.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Serializer {
    private ObjectParser parser;
    private ObjectPrinter printer;

    public Serializer(ObjectParser parser, ObjectPrinter printer) {
        this.parser = parser;
        this.printer = printer;
    }

    public void serialize(Object obj) throws InvocationTargetException, IllegalAccessException {
        Tag tag = this.parser.parse(obj);
        this.printer.printObj(tag);
    }

    public void serialize(Object obj, String path) throws IOException, InvocationTargetException, IllegalAccessException {
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter writer = new FileWriter(f);
        Tag tag = this.parser.parse(obj);
        this.printer.printObj(tag, writer);
    }
}
