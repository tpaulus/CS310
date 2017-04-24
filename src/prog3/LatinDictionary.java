import java.util.Iterator;

import data_structures.*;

/**
 * TODO JDOC
 */
public class LatinDictionary {
    private DictionaryADT<String, String> dictionary;

    /**
     * constructor takes no arguments.  Size depends on the datafile.
     * creates an instance of the DictionaryADT. Use your HashTable
     * implementation in this class (though all four should work).
     * Methods that make modifications to the dictionary modify the
     * DictionaryADT object, not the datafile.
     */
    public LatinDictionary() {
        // TODO
    }

    /**
     * reads the key=value pairs from the datafile and builds a dictionary structure
     *
     * @param fileName
     */
    public void loadDictionary(String fileName) {
        // TODO
    }

    /**
     * inserts a new Latin word and its definition
     *
     * @param word
     * @param definition
     * @return
     */
    public boolean insertWord(String word, String definition) {
        // TODO
        return false;
    }

    /**
     * removes the key value pair that is identified by the key from the dictionary
     *
     * @param word
     * @return
     */
    public boolean deleteWord(String word) {
        // TODO
        return false;
    }

    /**
     * looks up the definition of the Latin word
     *
     * @param word
     * @return
     */
    public String getDefinition(String word) {
        // TODO
        return null;
    }

    /**
     * returns true if the Latin word is already in the dictionary
     *
     * @param word
     * @return
     */
    public boolean containsWord(String word) {
        // TODO
        return false;
    }

    /**
     * returns all of the keys in the dictionary within the range start .. finish
     * inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
     * dictionary.  Returns null if there are no keys in the range specified.
     *
     * @param start
     * @param finish
     * @return
     */
    public String[] getRange(String start, String finish) {
        // TODO
        return null;
    }

    /**
     * returns an Iterator of the latin words (the keys) in the dictionary,
     * in sorted order.
     *
     * @return
     */
    public Iterator<String> words() {
        // TODO
        return null;
    }

    /**
     * returns the definitions in the dictionary, in exactly the same order
     * as the words() Iterator
     *
     * @return
     */
    public Iterator<String> definitions() {
        // TODO
        return null;
    }
}   