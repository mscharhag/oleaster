Oleaster
=====

Oleaster allows you to write JUnit tests like you would write [Jasmine](http://jasmine.github.io/) tests.

An Oleaster JUnit test looks like this:

```java
@RunWith(OleasterRunner.class)
public class OleasterIntroductionTest {{
	describe("A suite", () -> {
		it("contains a spec with an expectation", () -> {
			expect(40 + 2).toEqual(42);
		});
	});
}}
```

Oleaster consists out of two independent libraries:

The [Oleaster JUnit Runner](https://github.com/mscharhag/oleaster/tree/master/oleaster-runner) gives you the option
 to write JUnit tests in the format shown above. Java 8 Lambda expressions are used to structure a test in suites
 and specifications.``
 
[Oleaster-Matcher](https://github.com/mscharhag/oleaster/tree/master/oleaster-matcher)
 provides Jasmine-like Matchers (`expect(..).toEqual(..)`) to validate test results. These Matchers can be used
 as a replacement (or extension) for standard JUnit assertions.

## Documentation and examples

[Oleaster JUnit Runner Documentation](https://github.com/mscharhag/oleaster/blob/master/oleaster-runner/README.md)

[Oleaster Matcher Documentation](https://github.com/mscharhag/oleaster/blob/master/oleaster-matcher/README.md)

[Source of the AudioPlayer example](https://github.com/mscharhag/oleaster/blob/master/oleaster-examples/src/test/java/com/mscharhag/oleaster/examples/AudioPlayerExampleTest.java) from the Oleaster Runner documentation.

Oleaster tests are written with Oleaster (see: [Oleaster JUnit Runner Tests](https://github.com/mscharhag/oleaster/tree/master/oleaster-runner/src/test/java/com/mscharhag/oleaster/runner) and [Oleaster Matcher Tests](https://github.com/mscharhag/oleaster/tree/master/oleaster-matcher/src/test/java/com/mscharhag/oleaster/matcher/matchers)).

Travis CI builds can be found [here](https://travis-ci.org/mscharhag/oleaster)

## Maven dependencies
```xml
<dependencies>

	<!-- Oleaster Matchers -->
	<dependency>
    	<groupId>com.mscharhag.oleaster</groupId>
    	<artifactId>oleaster-matcher</artifactId>
    	<version>0.1.0</version>
    </dependency>
    
	<!-- Oleaster JUnit runner -->
	<dependency>
		<groupId>com.mscharhag.oleaster</groupId>
		<artifactId>oleaster-runner</artifactId>
		<version>0.1.0</version>
	</dependency>

	<!-- Of course a JUnit runner can only be used with JUnit -->
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.11</version>
	</dependency>
	
</dependencies>
```

## License

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
