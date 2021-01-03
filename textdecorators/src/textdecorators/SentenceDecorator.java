package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.io.IOException;

public class SentenceDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd;
    private InputDetails id;
    MyLogger myLogger;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn, MyLogger myLogger) {
        atd = atdIn;
        id = idIn;
        this.myLogger = myLogger;
    }

    /**
     * Overrides method from AbstractTextDecorator
     * to process start and end of Sentences
     */
    @Override
    public void processInputDetails() {
        try {
            myLogger.writeMessage("SENTENCE_DECORATOR\n",MyLogger.DebugLevel.SENTENCEDECORATOR);
            int index = 0;
            for (String element : id.getStoreSentence()) {
                myLogger.writeMessage("ANALYZING BEGINNING AND ENDING OF SENTENCE " + (index + 1) + "\n", MyLogger.DebugLevel.SENTENCEDECORATOR);
                element = "BEGIN_SENTENCE__" + element + "__END_SENTENCE";
                myLogger.writeMessage("UPDATING SENTENCE " + (index + 1) + "\n", MyLogger.DebugLevel.SENTENCEDECORATOR);
                id.updateSentence(index, element);
                myLogger.writeMessage("UPDATED SENTENCE!\n", MyLogger.DebugLevel.SENTENCEDECORATOR);
                index ++;
            }

            String paragraph = "";
            for (String sentence : id.getStoreSentence()){
                paragraph = paragraph + sentence + ".";
            }
            myLogger.writeMessage("SENTENCE_DECORATOR\n",MyLogger.DebugLevel.SENTENCEDECORATOR);
            System.out.println(paragraph);
        } catch (IOException e){
            e.printStackTrace();
        }

        if (null != atd) {
            atd.processInputDetails();
        }
    }
}
