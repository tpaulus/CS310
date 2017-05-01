import java.util.Iterator;

import data_structures.*;

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
        dictionary = new HashTable<>(8320);
//        dictionary = new RedBlackTree<>();
//        dictionary = new BinarySearchTree<>();
//        dictionary = new OrderedArrayDictionary<>(8500);
    }

    /**
     * reads the key=value pairs from the datafile and builds a dictionary structure
     *
     * @param fileName {@link String} File to read in
     */
    public void loadDictionary(String fileName) {
        DictionaryEntry[] dictionaryEntries = DictionaryReader.getDictionaryArray(fileName);
        for (DictionaryEntry entry : dictionaryEntries) {
            if (entry == null || entry.getKey() == null || entry.getValue() == null) continue;
            insertWord(entry.getKey(), entry.getValue());
        }
    }

    /**
     * inserts a new Latin word and its definition
     *
     * @param word       {@link String} Word to Insert
     * @param definition {@link} Matching Definition
     * @return If insertion was successful
     */
    public boolean insertWord(String word, String definition) {
        return dictionary.add(word, definition);
    }

    /**
     * Removes the key value pair that is identified by the key from the dictionary
     *
     * @param word {@link String} Word to remove from dictionary
     * @return If the word was removed successfully
     */
    public boolean deleteWord(String word) {
        return dictionary.delete(word);
    }

    /**
     * Looks up the definition of the Latin word
     *
     * @param word {@link String } Word to Locate in Dictionary
     * @return {@link String} Matching Definition
     */
    public String getDefinition(String word) {
        return dictionary.getValue(word);
    }

    /**
     * Check if a word is contained within the dictionary.
     *
     * @param word {@link String} Word to Look For
     * @return if the Latin word is already in the dictionary
     */
    public boolean containsWord(String word) {
        return dictionary.contains(word);
    }

    /**
     * returns all of the keys in the dictionary within the range start .. finish
     * inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
     * dictionary.  Returns null if there are no keys in the range specified.
     *
     * @param start  {@link String} Lower Bound
     * @param finish {@link String} Upper Bound
     * @return All Keys within the given range
     */
    public String[] getRange(String start, String finish) {
        Iterator<String> keyIter = words();
        String[] keys = new String[dictionary.size()];
        int i = 0;

        while (keyIter.hasNext()) {
            String next = keyIter.next();
            if (start.compareTo(next) >= 0) continue;
            if (next.compareTo(finish) >= 0) break;
            keys[i++] = next;
        }

        String[] keysTrunk = new String[i];
        System.arraycopy(keys, 0, keysTrunk, 0, i);
        return keysTrunk.length > 0 ? keysTrunk : null;
    }

    /**
     * returns an Iterator of the latin words (the keys) in the dictionary,
     * in sorted order.
     *
     * @return {@link Iterator} All words in the Dictionary, in alpha order
     */
    public Iterator<String> words() {
        return dictionary.keys();
    }

    /**
     * returns the definitions in the dictionary, in exactly the same order
     * as the words() Iterator
     */
    public Iterator<String> definitions() {
        return dictionary.values();
    }
}   