package textdecorators;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellCheckDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd;
    private InputDetails id;
    MyLogger myLogger;

    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn, MyLogger myLogger) {
        atd = atdIn;
        id = idIn;
        this.myLogger = myLogger;
    }

    /**
     * Overrides method from AbstractTextDecorator
     * to process SpellChecker from misspelled words file
     */
    @Override
    public void processInputDetails() {
        try {
            myLogger.writeMessage("SPELLCHECK_DECORATOR\n",MyLogger.DebugLevel.SPELLCHECKDECORATOR);
            myLogger.writeMessage("READING MISSPELLED WORDS FILE\n",MyLogger.DebugLevel.SPELLCHECKDECORATOR);
            FileProcessor misspelledFp = new FileProcessor(id.args[1]);
            String words;
            while ((words = misspelledFp.poll()) != null) {
                int index = 0;
                Pattern pattern = Pattern.compile(words, Pattern.CASE_INSENSITIVE);
                for (String element : id.getStoreSentence()) {
                    Matcher matcher = pattern.matcher(element);
                    if (Pattern.compile(Pattern.quote(words), Pattern.CASE_INSENSITIVE).matcher(element).find()){
                        String temp = element.toLowerCase(Locale.US);
                        String temp2 = words.toLowerCase(Locale.US);
                        String temp3 = element;
                        int loc = temp.indexOf(temp2);
                        temp = temp3.substring(loc, loc + temp2.length());

                        myLogger.writeMessage("PROCESSING MISSPELLED WORDS...\n",MyLogger.DebugLevel.SPELLCHECKDECORATOR);
                        element = matcher.replaceAll("SPELLCHECK_" + temp + "_SPELLCHECK");
                        id.updateSentence(index,element);
                        myLogger.writeMessage("UPDATED SENTENCE!\n",MyLogger.DebugLevel.SPELLCHECKDECORATOR);
                    }
                    index ++;
                }
            }

            myLogger.writeMessage("SPELLCHECK_DECORATOR\n",MyLogger.DebugLevel.SPELLCHECKDECORATOR);
            misspelledFp.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
