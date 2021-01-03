package textdecorators.driver;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.io.IOException;

public class DriverHelper {

    /**
     * Takes command line arguments from Driver code
     * processes Decorator pattern
     * @param args CommandLine arguments
     * @throws IOException
     */
    public void processInput(String[] args) throws IOException {
        try {
            MyLogger myLogger = MyLogger.getInstance();
            myLogger.setDebugValue(Integer.parseInt(args[4]));

            myLogger.writeMessage("READING FILE FROM FILEPROCESSOR..\n", MyLogger.DebugLevel.FILE_PROCESSOR);
            InputDetails inputD = new InputDetails(args);
            myLogger.writeMessage("FINISHED READING FILE!\n", MyLogger.DebugLevel.FILE_PROCESSOR);

            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD, myLogger);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD, myLogger);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD, myLogger);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD, myLogger);

            mostFreqWordDecorator.processInputDetails();
            ((FileDisplayInterface) inputD).addOutputToFile();

            myLogger.writeMessage("PROGRAM TERMINATED SUCCESSFULLY!\n", MyLogger.DebugLevel.SUCCESS);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}