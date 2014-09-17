package org.cdlflex.ui.util.convert.date;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts Javas SimpleDateFormat patterns into different formats.
 */
public interface DateFormatConverter {
    /**
     * Convert the given SimpleDateFormat pattern string into a different format.
     * 
     * @param javaDateFormat the SimpleDateFormat pattern string
     * @return the converted date format pattern string
     */
    String convert(String javaDateFormat);

    /**
     * A tokenizer for DateFormat strings. Essentially it groups equivalent adjacent characters.
     * <p/>
     * <strong>Example:</strong> <br/>
     * "yyyy-mm-dd" would result in 5 tokens ["yyyy", "-", "mm", "-", "dd"]
     * 
     * <p/>
     * 
     * Based on https://svn.apache.org/repos/asf/poi/trunk/src/java/org/apache/poi/ss/util/DateFormatConverter.java
     */
    public static class DateFormatTokenizer {
        private String format;
        private int pos;

        /**
         * Creates a new DateFormatTokenizer for the given SimpleDateFormat pattern string.
         * 
         * @param format a SimpleDateFormat pattern string
         */
        public DateFormatTokenizer(String format) {
            this.format = format;
        }

        /**
         * Gets the next token in the format.
         * 
         * @return the next token in the given pattern
         */
        public String getNextToken() {
            if (pos >= format.length()) {
                return null;
            }
            int subStart = pos;
            char curChar = format.charAt(pos);
            ++pos;
            if (curChar == '\'') {
                while ((pos < format.length()) && ((format.charAt(pos)) != '\'')) {
                    ++pos;
                }
                if (pos < format.length()) {
                    ++pos;
                }
            } else {
                while ((pos < format.length()) && ((format.charAt(pos)) == curChar)) {
                    ++pos;
                }
            }
            return format.substring(subStart, pos);
        }

        /**
         * Static utility method that creates a new DateFormatTokenizer instance and returns all tokens as an array.
         * 
         * @param format the SimpleDateFormat pattern string
         * @return a list of tokens
         */
        public static String[] tokenize(String format) {
            List<String> result = new ArrayList<>();

            DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
            String token;
            while ((token = tokenizer.getNextToken()) != null) {
                result.add(token);
            }

            return result.toArray(new String[result.size()]);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();

            DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
            String token;
            while ((token = tokenizer.getNextToken()) != null) {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append("[").append(token).append("]");
            }

            return result.toString();
        }
    }

}
