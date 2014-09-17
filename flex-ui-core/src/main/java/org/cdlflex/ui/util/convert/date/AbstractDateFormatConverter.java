/**
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.cdlflex.ui.util.convert.date;

/**
 * Abstract implementation of a DateFormatConverter.
 */
public abstract class AbstractDateFormatConverter implements DateFormatConverter {

    @Override
    public String convert(String javaDateFormat) {
        StringBuilder str = new StringBuilder(javaDateFormat.length() * 2);

        for (String token : DateFormatTokenizer.tokenize(javaDateFormat)) {
            if (!hasMappedToken(token)) {
                str.append(token);
            } else {
                String mapped = getMappedToken(token);
                if (mapped == null) {
                    onNullMappedToken(token);
                } else {
                    str.append(mapped);
                }
            }
        }

        if (isConvertingEscapes()) {
            convertEscapes(str);
        }

        return str.toString();
    }

    /**
     * Called by {@link #convert(String)} when {@link #hasMappedToken(String)} returns true, but the return value of
     * {@link #getMappedToken(String)} is null, meaning that the given token can not be mapped.
     * <p/>
     * Throws an UnsupportedOperationException by default.
     * 
     * @param token the token for which no mapping exists
     */
    protected void onNullMappedToken(String token) {
        throw new UnsupportedOperationException("Can not map token " + token);
    }

    /**
     * Returns true if this DateFormatConverter has special escape characters that should be converted also. In that
     * case, the {@link #convertEscapes(StringBuilder)} method is called.
     * <p/>
     * Returns false by default, can be overridden by sub-classes.
     * 
     * @return true if this DateFormatConverter should convert escape characters
     */
    protected boolean isConvertingEscapes() {
        return false;
    }

    /**
     * SimpleDateFormat uses the single quote <code>'</code> character to escape character sequences, where as other
     * formats may use different quote block characters. This method converts these different escape characters.
     * 
     * @param str the StringBuilder containing the string to convert
     */
    protected void convertEscapes(StringBuilder str) {
        int o = -1; // index where the current quote block opened

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\'') {
                if (o == -1) {
                    o = i;
                } else {
                    // found a closing quote
                    convertEscape(o, i, str);
                    o = -1;
                }
            }
        }
    }

    /**
     * Hook to convert an escape block.
     * 
     * @param o the index of the quote beginning a quoted block
     * @param c the index of the quote closing a quoted block
     * @param str the string builder containing the pattern
     */
    protected void convertEscape(int o, int c, StringBuilder str) {
        // hook
    }

    /**
     * Returns the pattern token the given pattern token is converted to.
     * 
     * @param token the SimpleDateFormat token, e.g. 'YYYY'
     * @return the mapped token
     */
    public abstract String getMappedToken(String token);

    /**
     * Checks whether the given token is a mapped token.
     * 
     * @param token the token returned from DateFormatTokenizer (e.g. 'YYYY' or '-' or '.')
     * @return true if a mapping exists
     */
    public abstract boolean hasMappedToken(String token);

}
