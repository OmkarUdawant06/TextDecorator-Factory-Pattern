package textdecorators.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class InputDetails implements FileDisplayInterface {
    List<String> storeSentence = new ArrayList<String>();
    String fileName, outputFile;
    FileProcessor inputFp;
    File file;
    FileWriter fileWriter;
    public String[] args;

    /**
     * Initializes Input file and Output file
     * @param args CommandLine arguments
     * @throws IOException
     */
    public InputDetails(String[] args) throws IOException {
        this.args = args;
        this.fileName = args[0];
        this.outputFile = args[3];
        this.inputFp = new FileProcessor(fileName);
        file = new File(this.outputFile);
        fileWriter = new FileWriter(file);
        fileWriter.flush();
        processSentence();
    }

    /**
     * Read Input file and store in Data Structure
     * @throws IOException
     */
    public void processSentence() throws IOException {
        String words;
        String pattern = "[a-zA-Z0-9\\.,\\s]+";
        int index = 0;

        BreakIterator breakIterator = BreakIterator.getSentenceInstance();
        try {
            inputFp = new FileProcessor(fileName);

            while ((words = inputFp.poll()) != null){

                if (words.matches(pattern)){
                    breakIterator.setText(words);
                    index = 0;
                    while (breakIterator.next() != BreakIterator.DONE) {
                        String sentence = words.substring(index, breakIterator.current());
                        sentence = sentence.replace(".","");
                        setStoreSentence(sentence);
                        index = breakIterator.current();
                    }
                }
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        } finally {
            inputFp.close();
        }
    }

    /**
     * Set to ArrayList
     * @param sentence
     */
    public void setStoreSentence(String sentence){
        storeSentence.add(sentence);
    }

    /**
     * Updated changes in ArrayList
     * @param location location of element to update
     * @param sentence data to update
     */
    public void updateSentence(int location, String sentence){
        storeSentence.set(location, sentence);
    }

    /**
     * Returns Updated ArrayList
     * @return ArrayList
     */
    public List<String> getStoreSentence() {
        return storeSentence;
    }

    /**
     * Write the output in a File
     */
    @Override
    public void addOutputToFile() {
        try {
            StringBuilder paragraph = new StringBuilder();
            for (String sentence : storeSentence) {
                paragraph.append(sentence).append(".");
            }

            if(file.length() == 0) {
                fileWriter = new FileWriter(file, false);
            }
            else {
                fileWriter = new FileWriter(file, true);
            }
            fileWriter.write(paragraph + " ");
            fileWriter.close();
        } catch (IOException e) {
            e.getMessage();
            System.out.println(e);
        }
    }
}
