package org.cdlflex.ui.util.convert.date;

import java.util.HashMap;
import java.util.Map;

/**
 * DateFormatConverter that Converts SimpleDataFormat pattern strings into equivalent patterns used in <a
 * href="http://momentjs.com">moment.js</a>.
 */
public class MomentJsDateFormatConverter extends AbstractDateFormatConverter {

    private static final Map<String, String> TOKEN_MAP = new HashMap<>();

    static {
        map("G", null);

        map("y", "YYYY");
        map("yy", "YY");
        map("yyyy", "YYYY");

        map("Y", "gggg");
        map("YY", "gg");
        map("YYYY", "gggg");

        map("M", "M");
        map("MM", "MM");
        map("MMM", "MMM");
        map("MMMM", "MMMM");

        map("w", "w");
        map("ww", "ww");

        map("W", null);

        map("D", "DDD");
        map("DDD", "DDDD");

        map("d", "D");
        map("dd", "DD");

        map("F", null);

        map("u", "E");

        map("E", "ddd");
        map("EE", "ddd");
        map("EEE", "ddd");
        map("EEEE", "dddd");

        map("a", "A");

        map("H", "H");
        map("HH", "HH");

        map("h", "h");
        map("hh", "hh");

        map("k", null);
        map("kk", null);

        map("K", null);
        map("KK", null);

        map("m", "m");
        map("mm", "mm");

        map("s", "s");
        map("ss", "ss");

        // this is actually not correct, but the closest we can get (will work (999-99)/999 * 100 % of the time ;-)
        map("S", "SSS");
        map("SSS", "SSS");

        map("z", null);
        map("zz", null);
        map("zzzz", null);

        map("X", null);
        map("XX", "ZZ");
        map("XXX", "Z");

        map("Z", "ZZ");
        map("ZZ", "ZZ");

        map("''", "'"); // special quoting case of SimpleDateFormat
    }

    @Override
    public String getMappedToken(String token) {
        return TOKEN_MAP.get(token);
    }

    @Override
    public boolean hasMappedToken(String token) {
        return TOKEN_MAP.containsKey(token);
    }

    /**
     * SimpleDateFormat uses the single quote <code>'</code> character to escape character sequences, where as moment.js
     * uses brackets <code>[</code> <code>]</code>. This method converts these different escape characters. A special
     * case is <code>''</code> which is covered by the token conversion (as it shows up as a single <code>'</code> in a
     * SimpleDateFormat formatted date.
     * 
     * @param o the index of the quote beginning a quoted block
     * @param c the index of the quote closing a quoted block
     * @param str the string builder containing the pattern
     */
    @Override
    protected void convertEscape(int o, int c, StringBuilder str) {
        str.setCharAt(o, '[');
        str.setCharAt(c, ']');
    }

    @Override
    protected boolean isConvertingEscapes() {
        return true;
    }

    private static void map(String simpleDateFormat, String momentJsFormat) {
        TOKEN_MAP.put(simpleDateFormat, momentJsFormat);
    }
}
