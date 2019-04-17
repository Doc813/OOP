import parser.XMLParser;
import parser.XMLPrinter;
import test.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public Main() {
    }

    public static void main(String[] argc) {
        Test person = new Test("str");
        Serializer serializer = new Serializer(new XMLParser(), new XMLPrinter());

        try {
            serializer.serialize(person, "G:\\Учеба\\University\\2год\\OOP\\ALL\\Lab55\\test.xml");
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        } catch (InvocationTargetException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }
}
