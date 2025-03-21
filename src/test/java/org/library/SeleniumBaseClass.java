package org.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.page.LoginPage;
import org.pageManager.PageManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumBaseClass {

	public static WebDriver driver;
	public JavascriptExecutor javascriptExecutor;
	public Actions actions;
	public static SeleniumBaseClass seleniumBaseClass = new SeleniumBaseClass();
	public static PageManager pageManager = new PageManager();

	@BeforeClass
	public static void LoginPageDetails() {

		try {

			seleniumBaseClass.initDriver(getDataFromWorkbook("input", 1, 3));

			seleniumBaseClass.launchUrl(getDataFromProperties("url"));
			seleniumBaseClass.winMax();

			// To Validate User is in Login Page

			if (pageManager.getLoginPage().getLoginText().isDisplayed()) {
				System.out.println("User is Login Page");

				if (pageManager.getLoginPage().getLoginText().getText().equals("Build 1")) {

					System.out.println("User is In Build 1 as well");

				}

			} else {
				System.out.println("User Not in Login Page");
			}

			// Enter Crdentials
			WebElement userName = pageManager.getLoginPage().getUserName();
			seleniumBaseClass.sendkeysByJSE(userName, getDataFromWorkbook("input", 1, 0));

			WebElement password = pageManager.getLoginPage().getPassword();
			seleniumBaseClass.sendKeysByJava(password, getDataFromWorkbook("input", 1, 1));

			// Click Button
			if (pageManager.getLoginPage().getLoginButton().isDisplayed()) {

				if (pageManager.getLoginPage().getLoginButton().isEnabled()) {

					seleniumBaseClass.clickByJSE(pageManager.getLoginPage().getLoginButton());

				}

			}

		} catch (Exception e) {

		}

	}

	

	@AfterClass
	public static void CloseBrowser() {

		seleniumBaseClass.quiteAndClose("quite");
	}
	
	
	

	public void initDriver(String browserType) {

		switch (browserType) {
		case "Chrome":

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;

		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			break;

		default:
			System.out.println("In Valid BrowserType");
			break;
		}
	}

	public void launchUrl(String browserUrl) {
		driver.get(browserUrl);

	}

	public void sendKeysByJava(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void clickByJava(WebElement element) {
		element.click();
	}

	public void sendkeysByJSE(WebElement element, String keysToSend) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute( 'value' ,'" + keysToSend + "')", element);

	}

	public void clickByJSE(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public void scrollType(WebElement element, String scrollType) {

		javascriptExecutor = (JavascriptExecutor) driver;

		switch (scrollType) {
		case "Up":
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(false)", element);
			break;

		case "Down":
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
			break;

		default:
			System.out.println("InValid Scroll Type");
			break;
		}

	}

	public String getAttributeByJava(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}

	public Object getAttributeByJSE(WebElement element) {
		javascriptExecutor = (JavascriptExecutor) driver;
		Object executeScript = javascriptExecutor.executeScript("return arguments[0].getAttribute('value')", element);

		return executeScript;
	}

	public String getText(WebElement element) {

		String text = element.getText();

		return text;
	}

	public void screenCapture(String name) {

		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
			File target = new File(
					"C:\\Users\\hp\\eclipse-workspace\\Jan2025Framework12.00PMBatch\\ErrorImage\\" + name + ".png");
			FileUtils.copyFile(source, target);

		} catch (Exception e) {

		}

	}

	public void screenCapture2() {

		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
			File target = new File("C:\\Users\\hp\\eclipse-workspace\\Jan2025Framework12.00PMBatch\\ErrorImage\\"
					+ System.currentTimeMillis() + ".png");
			FileUtils.copyFile(source, target);

		} catch (Exception e) {

		}

	}

	public void selectBy(WebElement element, String value, String selectType) {

		Select select = new Select(element);

		switch (selectType) {
		case "value":
			select.selectByValue(value);
			break;

		case "text":
			select.selectByVisibleText(value);
			break;

		default:
			break;
		}

	}

	public void winMax() {
		driver.manage().window().maximize();
	}

	public void windowsHandling(int requiredWindowsIndexNumber) {

		String currentWindowsID = driver.getWindowHandle();
		Set<String> allWindowsID = driver.getWindowHandles();

		List<String> list = new LinkedList<String>();
		list.addAll(allWindowsID);

		String requiredWindowsID = list.get(requiredWindowsIndexNumber);
		driver.switchTo().window(requiredWindowsID);

	}

	// OR

	public String getWindowsID(int requiredWindowsIndexNumber) {

		String currentWindowsID = driver.getWindowHandle();
		Set<String> allWindowsID = driver.getWindowHandles();

		List<String> list = new LinkedList<String>();
		list.addAll(allWindowsID);

		String requiredWindowsID = list.get(requiredWindowsIndexNumber);

		return requiredWindowsID;
	}

	public void switchToWindows(String requiredWindowsID) {
		driver.switchTo().window(requiredWindowsID);
	}

	public void navigation(String commands) {

		switch (commands) {

		case "refresh":
			driver.navigate().refresh();
			break;

		case "back":
			driver.navigate().back();
			break;

		case "forward":
			driver.navigate().forward();
			break;

		default:

			break;
		}

	}

	public void sleep(long millis) {

		try {

			Thread.sleep(millis);

		} catch (Exception e) {

		}

	}

	public void clear(WebElement element) {
		element.clear();
	}

	public void quiteAndClose(String quite_close) {

		switch (quite_close) {

		case "quite":
			driver.quit();
			break;

		case "close":
			driver.close();
			break;

		default:
			break;
		}
	}

	public void alert(String accept_dismiss) {

		switch (accept_dismiss) {

		case "accept":
			driver.switchTo().alert().accept();
			break;

		case "dismiss":
			driver.switchTo().alert().dismiss();
			break;

		default:
			break;
		}

	}

	public void promptAlert(String keysToSend, String accept_dismiss) {

		driver.switchTo().alert().sendKeys(keysToSend);

		switch (accept_dismiss) {

		case "accept":
			driver.switchTo().alert().accept();
			break;

		case "dismiss":
			driver.switchTo().alert().dismiss();
			break;

		default:
			break;
		}

	}

	public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {

		actions = new Actions(driver);

		actions.dragAndDrop(sourceElement, targetElement).build().perform();

	}

	public void mouseOverACtions(WebElement targetElement) {

		actions = new Actions(driver);

		actions.moveToElement(targetElement).build().perform();
	}

	public void switchToFrames(WebElement frameRefElement) {
		driver.switchTo().frame(frameRefElement);
	}

	public static String getDataFromWorkbook(String sheetName, int rownum, int cellnum) {

		String value = null;

		try {

			// To Locate Where the Workbook (Excel)
			File file = new File("DataBase\\inputDatas.xlsx");

			// To get File as a Input
			FileInputStream fileInputStream = new FileInputStream(file); // throws FileNotFoundException

			// To Define Workbook XSSFWorkbook || HSSFWorkbook'
			Workbook book = new XSSFWorkbook(fileInputStream); // throws IOException

			// to get sheet from book
			Sheet sheet = book.getSheet(sheetName);

			// To get Row from Sheet
			Row row = sheet.getRow(rownum);

			// To get Cell from Row
			Cell cell = row.getCell(cellnum);

			// To Define Cell Type
			CellType cellType = cell.getCellType();

			switch (cellType) {
			case STRING:
				// to get String value from Cell
				value = cell.getStringCellValue();

				break;

			case NUMERIC:

				// to get Date format numeric value
				if (DateUtil.isCellDateFormatted(cell)) {

					// to get Date cell value from Cell
					Date dateCellValue = cell.getDateCellValue();

					// To define Date Format for our desired
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					value = simpleDateFormat.format(dateCellValue);

				} else {

					// To get numeric value
					double numericCellValue = cell.getNumericCellValue();
					// To convert numeric double into BigDecimal
					BigDecimal valueOf = BigDecimal.valueOf(numericCellValue);
					// To Convert BigDecimal to String
					value = valueOf.toString();

				}

				break;

			default:
				break;
			}

		} catch (Exception e) {

		}

		return value;
	}

	public static String getDataFromProperties(String propertyKey) {

		String property = null;

		try {

			// To Locate where the properties file will be
			File file = new File("DataBase\\Config.properties");

			// To read the File
			FileReader fileReader = new FileReader(file); // Throws FileNotFoundException

			// To Define Properties File Class by using Object
			Properties properties = new Properties();

			// To Load File into Properties Class
			properties.load(fileReader); // throws IOException

			// To get required / desired value from File by using key
			property = properties.getProperty(propertyKey);

		} catch (Exception e) {

		}

		return property;

	}

}
