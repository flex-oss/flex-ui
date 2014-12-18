flex-ui
=======

[![Build Status](https://travis-ci.org/flex-oss/flex-ui.png?branch=master)](https://travis-ci.org/flex-oss/flex-ui)

Flex-UI is a free, open source set of reusable [Wicket](http://wicket.apache.org/) components and extensions that are
used in the frontend solutions developed at the [CDL-Flex](http://cdlflex.org).

Together with [bootstrap](http://getbootstrap.com) and [wicket-js](http://rauschig.org/wicket-js), it covers common
development use-cases and greatly improves both developing new modular components as well as integrated web-ui
solutions.

Flex-UI largely aims to follow "the Wicket way" in terms of structure and design.

License
-------

Flex-UI is is distributed under the terms of the Apache Software Foundation license, version 2.0. The text is included
in the file LICENSE in the root of the project.

Main features
-------------

* Integration of [bootstrap](http://getbootstrap.com) as Wicket components and behaviors (Dialogs, Buttons, Icons,
...)
* AJAX extensions for many of these components
* Substantial extension of Wicket's `DataTable` API and functionality
* Numerous other re-usable Component and Behavior extensions that cover common development use-cases 
               
Usage
-----

Flex-UI is hosted on maven-central and can easily be integrated into your project using maven

    <dependency>
        <groupId>org.cdlflex</groupId>
        <artifactId>flex-ui-core</artifactId>
        <version>add current version here</version>
    </dependency>

Components
----------

* `flex-ui-core` contains all the core components of flex-ui
* `flex-ui-fruit` Wicket integration for [fruit](http://github.com/flex-oss/flex-fruit) repositories
* `flex-ui-examples` Wicket web application that provides a set of examples that showcase Flex-UI components 

Dependencies
------------

Flex-UI aims to be a pure Wicket extension and has few dependencies. Namely 

* `wicket-core`
* `wicket-extensions`
* `wicket-util`
* `wicket-js`

Building
--------

Build the entire project using Maven

    mvn clean install
