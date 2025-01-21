# VOM Sandbox

Java testing framework for frontend and backend tests.
The frontend tests are implemented using Selenium, while the backend tests are implemented using
RestAssured. The project also utilizes Lombok to reduce boilerplate code.

### Prerequisites

Java 21 or higher
Maven 3.6.0 or higher

### Installation

Clone the repository:

```
git clone https://github.com/silviuMr/vom-sandbox.git```
```

### Build

Navigate to the project directory, then build the project using Maven

```
cd ~\vom-sandbox
mvn clean install -DskipTests
```

### Running Tests

Use the following command to execute all tests:

```
mvn clean test
```

or they can be run from the IDE by running each individual class or test method from the `test/java`
package.

### Technologies Used

For frontend testing, Selenium https://www.selenium.dev/documentation/. For simplicity, ChromeDriver
was used to run the tests. Please make sure you have Chrome browser installed.

For backend testing, RestAssured: https://github.com/rest-assured/rest-assured/wiki/GettingStarted

Lombok: https://projectlombok.org/ for reducing boilerplate code

### Test summary notes

#### WebTablesTest

Frontend test that navigates to the banking app page, logs in as a Bank Manager, and adds a new
customer named
“John Wick” with postal code 888300. It then verifies the customer was added correctly, then deletes
the newly added customer and ensures they were removed from the list.

The test scenario uncovers a bug in the application.

#### BankingTest

Frontend test, navigates to the webtables app page, edits the user “Mark Novak” to change their role
from
Customer to Sales Team, and verifies the change is saved and displayed correctly. Then, it edits the
“test” user to change their company from “Company BBB” to “Company AAA” and ensures the change is
saved and displayed correctly.

#### AlbumCollectionTest

Backend test class, contains two tests. The first verifies that a new album can be created and
deleted,
validating album information in the process. The second test verifies that album information can be
updated, finishing with an album delete.

#### JsonPlaceholderTest

Backend test class, verifies the existence of a user with the username “Karianne,” then adds a new
post. A test for timing the response of an API endpoint, that fails if the response time exceeds a
given threshold, is also present in the class.
