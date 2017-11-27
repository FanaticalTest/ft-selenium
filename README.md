# ft-selenium

## Run

### Start selenium
There is a docker container with specific version of Selenium server.
```
docker-compose up -d
```

### Start the build and run
```
./gradlew build
```

### Install in local repository
```
gradle install
```

## Use the library

Mainly we use By to access an element. We could use id, xpath, name, etc. Here in the example we use only id, but it could be any other attribute.

### Load a page
```
BasePage bp = new BasePage(driver);
bp.loadPage(ftdemoUrl);
```

### Enter value in field
```
BasePage bp = new BasePage(driver);
bp.fillFieldBy("username",By.id("username-field"))
```

### Click on element 
```
BasePage bp = new BasePage(driver);
bp.clickElementBy(By.id("login-submit"))
```

### Assert page title
```
BasePage bp = new BasePage(driver);
bp.assertPageTitle("Page title","http://page-url")
```

### Assert text in element
```
BasePage bp = new BasePage(driver);
bp.assertTextInElementBy("Text to validate",By.id("field-id-name"))
```

### Assert text in element
If the element take some time before appearing
```
BasePage bp = new BasePage(driver);
bp.waitAndAssertTextInElementBy("Text to validate",By.id("field-id-name"))
```

### Assert a value in a non standard attribute
```
BasePage bp = new BasePage(driver);
bp.assertAttributeInElementBy("Attribute-name", "value-to-assert",By.id("field-id-name"))
```