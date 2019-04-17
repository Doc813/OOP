package parser;

import xml.Tag;
import java.lang.reflect.InvocationTargetException;

public interface ObjectParser {
    Tag parse(Object var1) throws IllegalAccessException, InvocationTargetException;
}
