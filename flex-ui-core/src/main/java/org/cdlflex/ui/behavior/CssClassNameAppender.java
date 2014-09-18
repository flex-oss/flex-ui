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
package org.cdlflex.ui.behavior;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.util.Strings;

/**
 * A CssClassNameAppender is an AttributeAppender that appends given strings to the <code>class</code> html attribute.
 * It keeps classes unique within one attribute, making the operation idempotent.
 * <p/>
 * Example: <br/>
 * The markup
 * 
 * <pre>
 * &lt;span class="className" wicket:id="foo"&gt;
 * </pre>
 *
 * Using the following CssClassNameAppenders
 * 
 * <pre>
 * label.add(new CssClassNameAppender(&quot;className2&quot;, &quot;className3&quot;));
 * label.add(new CssClassNameAppender(&quot;className2&quot;));
 * </pre>
 * 
 * will yield
 * 
 * <pre>
 * &lt;span class=&quot;className className2 className3&quot; wicket:id=&quot;foo&quot; &gt;
 * </pre>
 * 
 * (Not necessarily in that order)
 *
 */
public class CssClassNameAppender extends AttributeAppender {

    /**
     * The name of the html class attribute name.
     */
    public static final String ATTRIBUTE_NAME = "class";

    public CssClassNameAppender(IModel<?> replaceModel) {
        this(ATTRIBUTE_NAME, replaceModel, " ");
    }

    public CssClassNameAppender(String... values) {
        this(Strings.join(" ", values));
    }

    public CssClassNameAppender(Serializable value) {
        this(ATTRIBUTE_NAME, Model.of(value), " ");
    }

    protected CssClassNameAppender(String attribute, IModel<?> appendModel, String separator) {
        super(attribute, appendModel, separator);
    }

    @Override
    protected String newValue(String currentValue, String appendValue) {
        return normalize(getSeparator(), currentValue, appendValue);
    }

    /**
     * Takes a string of separated values, interprets them as a collection, retains unique values and joins them using
     * the separator again.
     * <p/>
     * Example: A string "a b a", using a whitespace as separator, would yield "a b".
     *
     * @param separator the separator
     * @param values the values to normalize together
     * @return a normalized string
     */
    protected static String normalize(String separator, String... values) {
        Set<String> set = new HashSet<>(values.length);

        for (String value : values) {
            if (Strings.isEmpty(value)) {
                continue;
            }
            String[] split = value.trim().split(separator);
            set.addAll(Arrays.asList(split));
        }

        return Strings.join(separator, set);
    }

    /**
     * Appends the given CSS classes to the given ComponentTag. It preserves the class attributes set in the
     * ComponentTag. CSS classes are never duplicated.
     *
     * @param tag the component tag
     * @param cssClasses the classes to append
     */
    public static void append(ComponentTag tag, String... cssClasses) {
        String currentValue = tag.getAttribute("class");
        String appendValue = Strings.join(" ", cssClasses);

        tag.put("class", normalize(" ", currentValue, appendValue));
    }

}
