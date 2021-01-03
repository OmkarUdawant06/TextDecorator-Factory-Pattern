package textdecorators;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
    private AbstractTextDecorator atd;
    private InputDetails id;
    MyLogger myLogger;
    Map<String,Integer> wordCount = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);

    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn, MyLogger myLogger) {
        atd = atdIn;
        id = idIn;
        this.myLogger = myLogger;
    }

    /**
     * Overrides method from AbstractTextDecorator
     * to process Most frequent words in all sentences
     */
    @Override
    public void processInputDetails() {
        try{
            myLogger.writeMessage("MOST_FREQUENT_DECORATOR\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
            myLogger.writeMessage("READING INPUT FILE\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
            FileProcessor inputFp = new FileProcessor("/Users/tod/IdeaProjects/csx42-summer-2020-assign5-Tod80077/textdecorators/input.txt");
            String words;
            String[] word = new String[0];
            while ((words = inputFp.poll()) != null){
                 word = words.split(" ");
            }


            myLogger.writeMessage("CALCULATING MOST FREQUENT WORD...\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
            for (String key : word) {
                // If word already exist in TreeMap then increase it's count by 1
                if (wordCount.containsKey(key)) {
                    wordCount.put(key, wordCount.get(key) + 1);
                }
                // Otherwise add word to TreeMap
                else {
                    wordCount.put(key, 1);
                }
            }

            Set<Map.Entry<String, Integer> > set = wordCount.entrySet();
            String key = "";
            int value = 0;

            for (Map.Entry<String, Integer> me : set) {
                // Check for word having highest frequency
                if (me.getValue() > value) {
                    value = me.getValue();
                    key = me.getKey();
                }
            }

            int index = 0;
            Pattern pattern = Pattern.compile("(?m)(^|\\s)" + key + "(\\s|$)", Pattern.CASE_INSENSITIVE);

            for (String element : id.getStoreSentence()){
                Matcher matcher = pattern.matcher(element);

                if (Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(element).find()){
                    String temp = element.toLowerCase(Locale.US);
                    String temp2 = key.toLowerCase(Locale.US);
                    String temp3 = element;
                    int loc = temp.indexOf(temp2);
                    temp = temp3.substring(loc, loc + temp2.length());

                    myLogger.writeMessage("IDENTIFYING MOST FREQUENT WORDS IN SENTENCE " + (index + 1) + "\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
                    element = matcher.replaceAll(" MOST_FREQUENT_" + temp + "_MOST_FREQUENT ");
                    id.updateSentence(index,element);
                    myLogger.writeMessage("UPDATED SENTENCE!\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
                }
                index ++;
            }

            myLogger.writeMessage("MOST_FREQUENT_DECORATOR\n",MyLogger.DebugLevel.MOSTFREQUENTDECORATOR);
            inputFp.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (null != atd) {
            atd.processInputDetails();
        }
    }
}