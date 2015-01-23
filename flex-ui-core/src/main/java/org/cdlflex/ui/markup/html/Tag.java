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
package org.cdlflex.ui.markup.html;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cdlflex.ui.util.Strings;

/**
 * Helper class to build markup tag strings.
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private CharSequence name;
    private Map<String, Object> attributes;
    private List<Tag> children;

    private boolean shortTag;

    public Tag(CharSequence name) {
        this(name, new HashMap<String, Object>(), (Tag) null);
    }

    public Tag(CharSequence name, CharSequence body) {
        this(name, new HashMap<String, Object>(), body);
    }

    public Tag(CharSequence name, Tag body) {
        this(name, new HashMap<String, Object>(), body);
    }

    public Tag(CharSequence name, Map<String, Object> attributes, CharSequence body) {
        this(name, attributes, new TextNode(body));
    }

    public Tag(CharSequence name, Map<String, Object> attributes, Tag body) {
        this.name = name;
        this.attributes = attributes;
        this.children = new ArrayList<>();
        add(body);
    }

    /**
     * Adds a new attribute with the given key and value.
     * 
     * @param key attribute key
     * @param value attribute value
     * @return this for chaining
     */
    public Tag attr(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    /**
     * Adds a new attribute with the given key and value if the given condition is true.
     * 
     * @param key attribute key
     * @param value attribute value
     * @param condition condition
     * @return this for chaining
     */
    public Tag attr(String key, Object value, boolean condition) {
        return condition ? attr(key, value) : this;
    }

    /**
     * Adds all entries of the given map as attributes to this tag.
     *
     * @param attributeMap a map containing key/value pairs.
     * @return this for chaining
     */
    public Tag attrs(Map<? extends String, ?> attributeMap) {
        if (attributeMap != null) {
            for (Map.Entry<? extends String, ?> entry : attributeMap.entrySet()) {
                attr(entry.getKey(), entry.getValue());
            }
        }

        return this;
    }

    /**
     * Adds the given char sequence as a text node as child.
     *
     * @param text the child as string
     * @return this for chaining
     */
    public Tag add(CharSequence text) {
        return add(new TextNode(text));
    }

    /**
     * Adds the given Tag as child.
     * 
     * @param child the child node
     * @return this for chaining
     */
    public Tag add(Tag child) {
        if (child != null) {
            getChildren().add(child);
        }
        return this;
    }

    /**
     * Sets whether the tag should be rendered as short tag.
     * <p>
     * A short tag is for example: <code>&lt;script/&gt;</code> vs. <code>&lt;script&gt;&lt;/script&gt;</code>
     * 
     * @param isShortTag a flag
     * @return this for chaining
     */
    public Tag setShortTag(boolean isShortTag) {
        this.shortTag = isShortTag;
        return this;
    }

    public boolean isShortTag() {
        return shortTag;
    }

    public List<Tag> getChildren() {
        return children;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public CharSequence getName() {
        return name;
    }

    /**
     * Generates Markup code from the current tag.
     * 
     * @return a markup string
     */
    public String toMarkup() {
        StringBuilder str = new StringBuilder();

        str.append("<").append(name);

        appendAttributes(str);

        if (isShortTag()) {
            str.append("/>");
        } else {
            str.append(">");
            List<Tag> childrenTags = getChildren();
            if (childrenTags != null && !childrenTags.isEmpty()) {
                Strings.joinInto(str, "", childrenTags);
            }
            str.append("</").append(name).append(">");
        }

        return str.toString();
    }

    @Override
    public String toString() {
        return toMarkup();
    }

    private void appendAttributes(StringBuilder str) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            str.append(" ");
            appendAttribute(entry.getKey(), entry.getValue(), str);
        }
    }

    private void appendAttribute(String key, Object value, StringBuilder str) {
        str.append(key).append("=").append('"');
        appendAttributeValue(value, str);
        str.append('"');
    }

    private void appendAttributeValue(Object value, StringBuilder str) {
        if (value.getClass().isArray()) {
            Strings.joinInto(str, " ", (Object[]) value);
        } else if (value instanceof Iterable) {
            Strings.joinInto(str, " ", (Iterable<?>) value);
        } else {
            str.append(String.valueOf(value));
        }
    }

    /**
     * Special case Tag that only consists of text.
     */
    public static class TextNode extends Tag {
        private static final long serialVersionUID = 1L;

        public TextNode(CharSequence text) {
            super(text);
        }

        @Override
        public String toMarkup() {
            return String.valueOf(getName());
        }
    }

}
