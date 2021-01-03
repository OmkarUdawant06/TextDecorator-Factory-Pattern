package textdecorators.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {
    File file;
    FileWriter fileWriter;

    private static MyLogger myLogger = new MyLogger();

    /**
     * Creates new log.txt file
     */
    private MyLogger(){
        try{
            file = new File("log.txt");
            fileWriter = new FileWriter(file);
            fileWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Get instance of myLogger
     * @return object of MyLogger
     */
    public static MyLogger getInstance(){
        return myLogger;
    }

    /**
     * Enum stores Debug point required for assignment
     */
    public enum DebugLevel {
        CONSTRUCTOR,
        FILE_PROCESSOR,
        KEYWORDDECORATOR,
        MOSTFREQUENTDECORATOR,
        SENTENCEDECORATOR,
        SPELLCHECKDECORATOR,
        SUCCESS,
        NONE;
    };

    private DebugLevel debugLevel;

    /**
     * Set Debug value
     * @param levelIn value of DebugLevel to set
     */
    public void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 1: debugLevel = DebugLevel.CONSTRUCTOR;
                    break;
            case 2: debugLevel = DebugLevel.FILE_PROCESSOR;
                    break;
            case 3: debugLevel = DebugLevel.KEYWORDDECORATOR;
                    break;
            case 4: debugLevel = DebugLevel.MOSTFREQUENTDECORATOR;
                    break;
            case 5: debugLevel = DebugLevel.SENTENCEDECORATOR;
                    break;
            case 6: debugLevel = DebugLevel.SPELLCHECKDECORATOR;
                break;
            case 7: debugLevel = DebugLevel.SUCCESS;
                break;
            default: debugLevel = DebugLevel.NONE;
                    break;
        }
    }

    /**
     * Set the Debug level
     * @param levelIn Debug level value
     */
    public DebugLevel setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
        return debugLevel;
    }

    /**
     * Prints the message
     * @param message Message to be printed
     * @param levelIn Debug level
     */
    public void writeMessage (String message  , DebugLevel levelIn ) throws IOException {
        if(file.length() == 0) {
            fileWriter = new FileWriter(file, false);
        }
        else {
            fileWriter = new FileWriter(file, true);
        }

        if (levelIn == debugLevel){
            System.out.println(message);
            fileWriter.write(message);
        }
        fileWriter.close();
    }

    /**
     * Print for debugging
     * @return The debugging level set to
     */
    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}
