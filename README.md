How to start the project from command line:
1. mvn package
2. mvn exec:java


REAST API is documented by Swagger-UI (OpenAPI). 
After starting the project the documentation is available on localhost:8080/swagger-ui/index.html

In package com.vassil.petkov.vendingmachine.controllers there is HardcodedDataController.java 
which is configured to add hardcoded products, to insert hardcoded coins, including invalid coins. 
All endpoints are available there.
