package textdecorators;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd;
    private InputDetails id;
    MyLogger myLogger;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn, MyLogger myLogger) {
        atd = atdIn;
        id = idIn;
        this.myLogger = myLogger;
    }

    /**
     * Overrides method from AbstractTextDecorator
     * to process Keywords from keywords.txt file
     */
    @Override
    public void processInputDetails() {
        try{
            myLogger.writeMessage("KEYWORD_DECORATOR\n",MyLogger.DebugLevel.KEYWORDDECORATOR);
            myLogger.writeMessage("READING KEYWORDS FILE\n",MyLogger.DebugLevel.KEYWORDDECORATOR);
            FileProcessor keywordsFp = new FileProcessor(id.args[2]);
            String words;
            while ((words = keywordsFp.poll()) != null){
                int index = 0;
                Pattern pattern = Pattern.compile(words, Pattern.CASE_INSENSITIVE);

                for (String element : id.getStoreSentence()){
                    Matcher matcher = pattern.matcher(element);

                    if (Pattern.compile(Pattern.quote(words), Pattern.CASE_INSENSITIVE).matcher(element).find()){
                        String temp = element.toLowerCase(Locale.US);
                        String temp2 = words.toLowerCase(Locale.US);
                        String temp3 = element;
                        int loc = temp.indexOf(temp2);
                        temp = temp3.substring(loc, loc + temp2.length());

                        myLogger.writeMessage("IDENTIFYING KEYWORDS...\n",MyLogger.DebugLevel.KEYWORDDECORATOR);
                        element = matcher.replaceAll("KEYWORD_" + temp + "_KEYWORD");
                        id.updateSentence(index, element);
                        myLogger.writeMessage("UPDATED SENTENCE!\n",MyLogger.DebugLevel.KEYWORDDECORATOR);
                    }
                    index ++;
                }
            }

            myLogger.writeMessage("KEYWORD_DECORATOR\n",MyLogger.DebugLevel.KEYWORDDECORATOR);
            keywordsFp.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
