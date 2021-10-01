package cpen221.mp1;

import cpen221.mp1.exceptions.NoSuitableSentenceException;
import cpen221.mp1.sentiments.SentimentAnalysis;
import org.checkerframework.checker.units.qual.A;

import java.net.URL;
import java.text.CharacterIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.Scanner;

public class Document {
    String doc_ID;
    String document;
    HashMap<String, Integer> wordCounts;
    int totalWordCount = 0;
    int totalNumSentences;
    ArrayList<ArrayList<String>> doc_array = new ArrayList<ArrayList<String>>();


//private final String cleanDoc;
    /* ------- Task 0 ------- */
    /*  all the basic things  */

    /**
     * Create a new document using a URL
     *
     * @param docId  the document identifier
     * @param docURL the URL with the contents of the document
     */
    public Document(String docId, URL docURL) {
        doc_ID = docId;

    }

    /**
     * @param docId    the document identifier
     * @param fileName the name of the file with the contents of
     *                 the document
     */
    public Document(String docId, String fileName) {
        doc_ID = docId;
        StringBuilder seed = new StringBuilder();

        try {
            Scanner docScanner = new Scanner(new FileReader(fileName));
            while (docScanner.hasNext()) {
                seed.append(docScanner.nextLine());
            }
        } catch (FileNotFoundException ioe) {
            System.out.println("Error reading file.");
        }

        document = seed.toString();
        wordCounts = instanceCounter(document);
    }

    private HashMap<String, Integer> instanceCounter(String seed) {
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        ArrayList<String> sentence = new ArrayList<String>();
        for (int i = 0; i < doc_array.size(); i++) {
            sentence = doc_array.get(i);
            for (int k = 0; k < sentence.size(); k++) {
                String word = sentence.get(k);
                if (wordMap.containsKey(word)) {
                    int count = wordMap.get(word);
                    wordMap.replace(word, count++);
                } else {
                    wordMap.put(word, 1);
                }
                totalWordCount++;
            }
        }
        return wordCounts;
    }


    public String getDocId() {

        return doc_ID;
    }

    /**
     * @param
     */


    /* ------- Task 1 ------- */
    public double averageWordLength() {
        int wordCount = 0;
        int charCount = 0;
        ArrayList<String> sentence = new ArrayList<String>();
        for (int i = 0; i < doc_array.size(); i++) {
            sentence = doc_array.get(i);
            for (int k = 0; k < sentence.size(); k++) {
                String word = sentence.get(k);
                charCount += word.length();
            }
        }
        return charCount / totalWordCount;
    }

    public double totalWords() {
        List<Integer> values = new ArrayList<Integer>(wordCounts.values());
        return (totalWordCount);
    }

    public double uniqueWordRatio() {

        int numUniqueWords = wordCounts.keySet().size();
        return numUniqueWords / totalWordCount;
    }

    public double hapaxLegomanaRatio() {
        ArrayList<Integer> counts = new ArrayList<Integer>(wordCounts.values());
        int countExactlyOnce = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) == 1) {
                countExactlyOnce++;
            }
        }
        return countExactlyOnce / totalWordCount;
    }

    /* ------- Task 2 ------- */

    /**
     * Obtain the number of sentences in the document
     *
     * @return the number of sentences in the document
     */
    public int numSentences() {
        int size = doc_array.size();
        return size;
    }

    /**
     * Obtain a specific sentence from the document.
     * Sentences are numbered starting from 1.
     *
     * @param sentence_number the position of the sentence to retrieve,
     *                        {@code 1 <= sentence_number <= this.getSentenceCount()}
     * @return the sentence indexed by {@code sentence_number}
     */
    public String getSentence(int sentence_number) {
        StringBuilder sentenceString = new StringBuilder();
        String word;
        ArrayList<String> sentence = new ArrayList<String>();
        int size = sentence.size();
        for (int i = 0; i < size; i++) {
            word = sentence.get(i);
            sentenceString.append(word);
        }
        return sentenceString.toString();
    }

    public double averageSentenceLength() {
        ArrayList<String> sentence = new ArrayList<String>();
        int counter = 0;
        int sentenceLength = 0;
        while (counter < doc_array.size()) {
            sentence = doc_array.get(counter);
            sentenceLength += sentence.size();
            counter++;
        }
        return sentenceLength / counter;
    }


    public double averageSentenceComplexity() {
        ArrayList<String> sentence = new ArrayList<String>();
        int counter = 0;
        int complexity = 0;
        while (counter < doc_array.size()) {
            sentence = doc_array.get(counter);
            complexity += sentence.numPhrase;
        }
        return complexity / counter;
    }
}

    /* ------- Task 3 ------- */

//    To implement these methods while keeping the class
//    small in terms of number of lines of code,
//    implement the methods fully in sentiments.SentimentAnalysis
//    and call those methods here. Use the getSentence() method
//    implemented in this class when you implement the methods
//    in the SentimentAnalysis class.

//    Further, avoid using the Google Cloud AI multiple times for
//    the same document. Save the results (cache them) for
//    reuse in this class.

//    This approach illustrates how to keep classes small and yet
//    highly functional.

    /**
     * Obtain the sentence with the most positive sentiment in the document
     * @return the sentence with the most positive sentiment in the
     * document; when multiple sentences share the same sentiment value
     * returns the sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a positive sentiment
     */
   /* public String getMostPositiveSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

    /**
     * Obtain the sentence with the most negative sentiment in the document
     * @return the sentence with the most negative sentiment in the document;
     * when multiple sentences share the same sentiment value, returns the
     * sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a negative sentiment
     */
   /* public String getMostNegativeSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
       //return null;
    }

}
