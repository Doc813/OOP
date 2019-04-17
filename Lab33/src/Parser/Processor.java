package Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Processor implements INIoperation {
    Map<String, Map<String, String>> data;


    Map<String, String> currSection;

    Processor(){
        data = new HashMap<>();
        currSection = null;
    }


    @Override
    public void addSection(String ident) {
        currSection = new HashMap<>();
        data.put(ident, currSection);
    }

    @Override
    public void addEntry(String name, String value) {
        currSection.put(name, value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Objects.equals(data, processor.data);
    }
}
