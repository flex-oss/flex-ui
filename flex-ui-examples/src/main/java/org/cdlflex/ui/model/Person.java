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
package org.cdlflex.ui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.util.io.IClusterable;

/**
 * A simple data type that represents a Person (having an id, a name and a birthday).
 */
public class Person implements IClusterable {

    private static final long serialVersionUID = 1L;

    private static final String DATE_FORMAT = "y-MM-dd";

    private int id;
    private String name;
    private Date birthday;

    public Person() {

    }

    public Person(int id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Person(int id, String name, String birthday) {
        this(id, name, parse(birthday));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private static Date parse(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * Static function that provides a new list of 5 Person objects.
     *
     * @return a list of Person objects
     */
    public static List<Person> createExampleData() {
        return Arrays.asList(new Person(1, "Carlos Cruz", "1973-12-26"), new Person(2, "Stephen Gilbert",
                "1981-09-04"), new Person(3, "Steve Mason", "1977-11-25"), new Person(4, "Cynthia Holmes",
                "1983-01-22"), new Person(5, "Steve Cunningham", "1975-07-30"));
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", birthday=" + birthday + '}';
    }
}
