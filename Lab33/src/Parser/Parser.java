package Parser;


import java.io.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final static Pattern sectionPattern = Pattern.compile("\\[(\\w+)\\]\\s*[;\\w+]*");

    private final static Pattern entryPattern = Pattern.compile("(\\w+)\\s*=\\s*([\\w.]+)\\s*[;\\w+]*");

    private final static Pattern spacePattern = Pattern.compile("\\s*");

    private final static Pattern commentPattern = Pattern.compile("[;\\w+]*");
    private static final Logger log = Logger.getLogger(Parser.class.getName());

    public static void parse(File file, INIoperation operation) throws IOException{

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                line = line.trim();
                Matcher matcher = sectionPattern.matcher(line);
                if(matcher.matches()){
                    operation.addSection(matcher.group(1));
                    continue;
                }
                matcher = entryPattern.matcher(line);
                if(matcher.matches()){
                    operation.addEntry(matcher.group(1), matcher.group(2));
                    continue;
                }
                matcher = spacePattern.matcher(line);
                if(matcher.matches()){
                    continue;
                }
                matcher = commentPattern.matcher(line);
                if(matcher.matches()){
                    continue;
                }
                log.info("IllegalFormatException");
            }
        }
    }
}
