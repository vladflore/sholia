# shopping-list-app a.k.a. sholia

This is the small web application - sholia - a shopping list application.

To verify the build, execute this: `mvn clean verify`

To run the mutation tests too, execute this: `mvn clean verify -Pmutation`

To upload the test reports(code coverage, mutation, if available) to sonar, execute this: `mvn sonar:sonar -Dsonar.host.url=<your-sonar-instance>`, where `<your-sonar-instance>` should point to a running sonar instance, like: `http://18.216.27.0:9000`

To run the application, execute this: `mvn spring-boot:run -f backend/pom.xml`
