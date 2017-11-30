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
Or something more all in one
```
docker-compose up -d && ./gradlew build  && docker-compose down
```
### Install in local repository
```
gradle install
```

## Use the library

Mainly we use By to access an element. We could use id, xpath, name, etc. Here in the example we use only id, but it could be any other attribute.

### Load a page
This method will allow to open a url
```
BasePage bp = new BasePage(driver);
bp.loadPage(ftdemoUrl);
```

### Enter value in field
Enter value in type of field like textbox
```
BasePage bp = new BasePage(driver);
bp.fillFieldBy("username",By.id("username-field"))
```

### Click on element 
Click on any type of element like link, button, picture, etc...
```
BasePage bp = new BasePage(driver);
bp.clickElementBy(By.id("login-submit"))
```

### Assert page title
Validate page title. Checking what is in between the html tag `<title>` in the `<head>`
```
BasePage bp = new BasePage(driver);
bp.assertPageTitle("Page title","http://page-url")
```

### Assert text in element
Validate text in any standard element
```
BasePage bp = new BasePage(driver);
bp.assertTextInElementBy("Text to validate",By.id("field-id-name"))
```

### Assert text in element
If the element take some time before appearing. This should be used when the page is loaded asynchronously.
```
BasePage bp = new BasePage(driver);
bp.waitAndAssertTextInElementBy("Text to validate",By.id("field-id-name"))
```

### Assert a value in a non standard attribute
Validate text in any non-standard attribute.
```
BasePage bp = new BasePage(driver);
bp.assertAttributeInElementBy("Attribute-name", "value-to-assert",By.id("field-id-name"))
```

### Wait until element is present
It waits until the element is active and return the element when ready
```
BasePage bp = new BasePage(driver);
bp.waitUntilActive(By.id("field-id-name"));
```

### Get screenshot
You can call the method when it requires. Let assume you want to store the picture in the test resources folder. The file name will contains a timestamp.
```
BasePage bp = new BasePage(driver);
// without prefix  but with timestamp
bp.getScreenshot("./src/test/resources/");
// with prefix
bp.getScreenshot("./src/test/resources/", "prefix-value");
// without timestamp
bp.getScreenshot("./src/test/resources/", "filename-value", true);
```

### Select in a dropdown
Select an item in the dropdown based on the value, here the value is `2`
```
BasePage bp = new BasePage(driver);
bp.selectDropDownByValue(By.id("qt-selector"),"2");
```

Select an item in the dropdown based on the index, here we select the 3 rd value => index `2`
```
BasePage bp = new BasePage(driver);
bp.selectDropDownByIndex(By.id(dropDownQtSelector),2);
```

Select an item in the dropdown based on the text, here the visible text is `4 pieces .
```
BasePage bp = new BasePage(driver);
bp.selectDropDownByVisibleText(By.id(dropDownQtSelector),"4 pieces");
```

Get the selected value in the selected item
```
BasePage bp = new BasePage(driver);
bp.getDropDownSelectedValue(By.id(dropDownQtSelector));
```

Get the selected value by attribute in the selected item
```
BasePage bp = new BasePage(driver);
bp.getDropDownSelectedAttribute(By.id(dropDownQtSelector), "value");
```

### Get text inside html tag
If you need to get whole string between 2 html tag
```
BasePage bp = new BasePage(driver);
bp.getInnerHtmlValue(By.id("html-field-id"));
```

### Freeze test process
Just for continence, but not recommended except for work around you could pause a test a some seconds.
In this example we wait for 2 seconds. The L is to convert the value in Long.
```
BasePage bp = new BasePage(driver);
bp.freezeProcess(2L);
```