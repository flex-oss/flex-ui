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
package org.cdlflex.ui.markup.html.repeater.data.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.util.io.IClusterable;

/**
 * Test model for tables.
 */
public class Person implements IClusterable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Date birthday;

    public Person(String name, String date) {
        this.name = name;
        try {
            this.birthday = new SimpleDateFormat("y-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Person(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static List<Person> createTestData() {
        return new ArrayList<>(Arrays.asList(new Person("A", "2001-01-01"), new Person("B", "2002-02-02"),
                new Person("C", "2003-03-03"), new Person("D", "2004-04-04"), new Person("E", "2005-05-05"),
                new Person("F", "2006-06-06"), new Person("G", "2007-07-07"), new Person("H", "2008-08-08")));
    }
}
