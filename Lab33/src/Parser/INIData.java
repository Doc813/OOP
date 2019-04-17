package Parser;


import java.io.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class INIData {
    private final static Pattern command = Pattern.compile("find (\\w+) (\\w+) from (\\w+)");
    private final static Pattern fileFormat = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
    private final File file;

    private final Processor processor;

    //Логер
    private static final Logger log = Logger.getLogger(INIData.class.getName());


    public INIData(String pathName){
       Matcher matcher = fileFormat.matcher(pathName);
        try {
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Bad file: " + "'" + pathName + "'");
            }
        }
        catch(IllegalArgumentException e){
            log.warning(e.getMessage());
        }
        file = new File(pathName);
        processor = new Processor();
    }

    public void parse(){
        try{
            Parser.parse(file, processor);
        }
        catch(IOException | IllegalArgumentException e){
            log.warning(e.getMessage());
        }
    }

    private Object find(String cmd){
        Matcher matcher = command.matcher(cmd);
        try{
            if(matcher.matches()){
                String value = processor.data.get(matcher.group(3)).get(matcher.group(2));
                if(matcher.group(1).equals("int")){
                    if(!isIntNum(value)){
                        log.warning("IllegalArgumentException - is not an integer");
                    }
                    else return Integer.valueOf(value);
                }
                else if(matcher.group(1).equals("double")){
                    if (!isDoubleNum(value)){
                        log.warning("IllegalArgumentException - is not a double");
                    }
                    else return Double.valueOf(value);
                }
                else if(matcher.group(1).equals("string")){
                    return value;
                }
            }
            else throw new IllegalArgumentException("Unknown command: " + cmd);
        }
        catch (IllegalArgumentException e){
            log.warning(e.getMessage());
        }
        return null;
    }

    public Object searching(String stopCommand) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!(line = reader.readLine()).equals(stopCommand)){
            System.out.println(find(line));
        }
        return null;
    }

    public boolean isIntNum(String value){
        try{
            int num = Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        return true;
    }

    public boolean isDoubleNum (String value){
        try{
            double d = Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}

