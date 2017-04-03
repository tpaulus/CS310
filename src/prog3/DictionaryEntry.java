/*  DictionaryEntry.java
    A wrapper class that holds a key and a value
    For use in project #3
    Alan Riggins
    CS310 Spring 2017
*/        
    
    public class DictionaryEntry {
        private String key;
        private String value;
        
        public DictionaryEntry(String k, String v) {
            key = k;
            value = v;
            }
            
        public String getKey() {
            return key;
            }
            
        public String getValue() {
            return value;
            }            
        }
