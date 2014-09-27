### Introduction
TODO:
* static import of `Matchers.*`
* can be used without oleaster runner
* java 8
* maven dependency

### Primitive types

#### Numbers
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

* [IntegerNumberMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/IntegerNumberMatcher.java) is used to compare integer values (`int`, `long`, `short`, `byte`)
* [FloatingPointNumberMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/FloatingPointNumberMatcher.java) is used to compare floating point values (`float`, `double`)

#### Boolean values
The following samples show how boolean values can be compared.

```java
boolean value = true;

// check for a given parameter
expect(value).toEqual(true);

// check for true/false
expect(value).toBeTrue();
expect(value).toBeFalse();
```

For comparing boolean values [BooleanMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/BooleanMatcher.java) will be used.
 
### Strings
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

 
### Exceptions
To test exceptions you just have to wrap the code that throws the expected exception into a lambda expression and pass
it to `expect()`. The lambda expression will be executed and thrown exceptions will be caught.
The thrown exception can be checked with `toFailWith()` as shown bellow:

```java
// check if an exception is thrown
expect(() -> {
	// code that throws IllegalArgumentException
}).toFailWith(IllegalArgumentException.class);

// with exception message
expect(() -> {
	// code that throws IllegalArgumentException
}).toFailWith(IllegalArgumentException.class, "An argument is invalid");
```

For testing exceptions [ExceptionMatcher](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/src/main/java/com/mscharhag/oleaster/matcher/ExceptionMatcher.java) will be used.