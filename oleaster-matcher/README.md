Oleaster Matcher
=====

Oleaster Matcher is a small library that provides [Jasmine-like Matchers](https://github.com/pivotal/jasmine/wiki/Matchers)
for Java. Oleaster Matchers can be used as a replacement (or extension) for standard JUnit assertions.

Expectations written with Oleaster Matchers look like this:

```java
import static com.mscharhag.oleaster.matcher.Matchers.*;

// same as JUnit's assertEquals(40 + 2, 42)
expect(40 + 2).toEqual(42);

// see if a String matches a regular expression
expect("foobar").toMatch("fo{2}\\w+");

// test exceptions with Java 8 Lambdas
expect(() -> {
	// code that throws IllegalArgumentException
	throw new IllegalArgumentException();
}).toThrow(IllegalArgumentException.class);
```
Oleaster Matchers do not depend on the Oleaster JUnit Runner. 
You can use Oleaster Matchers without using the Oleaster JUnit Runner.
  
## Getting started
To use Oleaster Matchers you need `oleaster-matcher.jar` in your classpath. 
If you are using Maven you just have to add the following dependency:

```xml
<dependency>
	<groupId>com.mscharhag.oleaster</groupId>
	<artifactId>oleaster-matcher</artifactId>
	<version>0.1.1</version>
</dependency>
```

Matcher objects can be created using one the various static `expect()` methods of the `Matchers` class.
To improve readability it is recommended to statically import `Matchers.*`
```java
import static com.mscharhag.oleaster.matcher.Matchers.*;
```

**Note:** You can find the source of all examples shown here in the `oleaster-examples` module  
(see: [MatcherExamplesTest.java](https://github.com/mscharhag/oleaster/blob/master/oleaster-examples/src/test/java/com/mscharhag/oleaster/examples/MatcherExamplesTest.java))

## Primitive types

### Numbers
The following samples show how numbers can be compared.

```java
int value = 42;

// check for exact value
expect(value).toEqual(42);

// check for greater/smaller values
expect(value).toBeGreaterThan(41);
expect(value).toBeSmallerThan(43);

// check for range
expect(value).toBeBetween(40, 45);

// floating point number can often not be precisely represented by float/double values.
// So make sure to compare them with an absolute error (delta)
expect(42.0000001).toBeCloseTo(42); // uses default delta of 0.00001
expect(42.0000001).toBeCloseTo(42, 0.000001);
```
For Numbers two different matcher classes are available:

* [IntegerNumberMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/IntegerNumberMatcher.java) 
	is used to compare integer values (`int`, `long`, `short`, `byte`)
* [FloatingPointNumberMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/FloatingPointNumberMatcher.java)
 	is used to compare floating point values (`float`, `double`)

### Boolean values
The following samples show how boolean values can be compared.

```java
boolean value = true;

// check for a given parameter
expect(value).toEqual(true);

// check if true
expect(value).toBeTrue();

// check if false
value = false;
expect(value).toBeFalse();
```

For comparing boolean values [BooleanMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/BooleanMatcher.java) will be used.

## Objects

```java
Person person = new Person("John", "Smith");

// check for equality, delegates to Person.equals()
expect(person).toEqual(new Person("John", "Smith"));

// check if not null
expect(person).toBeNotNull();

// check if null
person = null;
expect(person).toBeNull();
```

For comparing Objects [ObjectMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/ObjectMatcher.java) will be used.

## Strings
The following samples show how String values can be compared.

```java
// check for exact value
expect("foo").toEqual("foo");

// check string starting/ending
expect("foobar").toStartWith("foo");
expect("foobar").toEndWith("bar");

// check if a String contains a given substring
expect("foobar").toContain("oba");

// check if a String matches a regular expression
expect("foobar").toMatch(Pattern.compile("fo+\\w*"));
expect("foobar").toMatch("fo+\\w*");
```

For comparing Strings [StringMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/StringMatcher.java) will be used.

## Exceptions
To test exceptions you just have to wrap the code that throws the expected exception into a lambda expression and pass
it to `expect()`. The lambda expression will be executed and thrown exceptions will be caught.
The thrown exception can be checked with `toThrow()` as shown bellow:

```java
// check if an exception is thrown
expect(() -> {
	// code that throws IllegalArgumentException
	throw new IllegalArgumentException();
}).toThrow(IllegalArgumentException.class);

// with exception message
expect(() -> {
	// code that throws IllegalArgumentException
	throw new IllegalArgumentException("An argument is invalid");
}).toThrow(IllegalArgumentException.class, "An argument is invalid");
```

For testing exceptions [ExceptionMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/matchers/ExceptionMatcher.java) will be used.

## Licence

This software is licensed under the Apache 2 license, quoted below.

Copyright 2014 Michael Scharhag

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.