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