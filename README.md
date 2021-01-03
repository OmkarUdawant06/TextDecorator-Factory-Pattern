# CSX42: Assignment 5
## Name: Omkar Udawant

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Darg1="input.txt" -Darg2="misspelled.txt" -Darg3="keywords.txt" -Darg4="output.txt" -Darg6=<Int value(between 0-7)>
```
Note: Arguments accept the absolute path of the files.


## Description:
Input file is processed by InputDetails constructor. It reads the file using FileProcessor, and sentence is stored in ArrayList. The period operator is ignored.
AbstractTextDecorator objects are programmed by decorator classes SentenceDecorator, SpellCheckDecorator, KeywordDecorator, MostFrequentDecorator.
First, MostFrequentDecorator is executed. It scans for the words from input file and stores their number of occurrences in all sentences. TreeMap is used to do same.
Then, KeywordDecorator checks for keywords from input file and locates them in sentences stored. It adds Prefix and Suffix when keyword is found.
Next, SpellCheckDecorator scans for misspelled words from input file and adds a Prefix and Suffix to the located word.
Lastly, SentenceDecortor scans for Beginning and Ending of sentence. It appends Prefix and Suffix to start and end of a sentence.
Driver class accepts 5 commandline arguments. It checks for number of command line arguments matching.
Apart from Driver class, DriverHelper class is simple concise code which makes call to Decorators using AbstractTextDecorator object.

MyLogger creates a file named log.txt. It contains debugging details as per current Debugging Level set.
MyLogger class used to debug the program: 
`Case 0: NONE`
`Case 1: CONSTRUCTOR (To check if Constructors are called appropriately)`
`Case 2: FILE_PROCESSOR (To check if FileProcessor is executing)`
`Case 3: KEYWORDDECORATOR (To check if Keyword Decorator is working)`
`Case 4: MOSTFREQUENTDECORATOR (To check if Most frequent decorator is working)`
`Case 5: SENTENCEDECORATOR (To check if Sentence decorator is working)`
`Case 6: SPELLCHECKDECORATOR (To check if SpellCheck decorator is working)`
`Case 7: SUCCESS (To check if program executes Successfully)`

Exception handling done to trace appropriate errors. Code is well commented for better understanding of function's purpose.
Created class for custom Exception handling called TextDecorators.

Data Structure used:    `ArrayList to store words from input files.`
                        `TreeMap to calculate most frequent word in sentences`

TreeMap: Its time complexity for insertion and lookup is O(log N). It is efficient because it does not allow null values and allows to stores case-insensitive keys.
ArrayList's time Complexity for insertion is O(1).

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 08/5/2020