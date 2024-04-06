package week8;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Bot.
 */
public class Bot {

    private WebDriver driver;
    private String url;
    private final int TIMEOUT = 5;
    private WebDriverWait wait;



    private Actions actionProvider;
    private ArrayList<String> movieNames;

    /**
     * Instantiates a new Bot.
     *
     * @param url the url
     */
    public Bot(String url) {
        movieNames = new ArrayList<>();
        if (validateUrl(url)) {
            this.url = url;
            initializeDriver();
            initializeWait();
            initializeActionProvider();
        }
    }

    private void removeCookieOption() {
        String buttonXPath = "/html/body/div[2]/div/div/div[2]/div/button[1]";
        WebElement button = wait.until(driver -> driver.findElement(By.xpath(buttonXPath)));
        WebElement clickableButton = wait.until(ExpectedConditions.elementToBeClickable(button));
        clickableButton.click();
    }


    private void populate() {
        String buttonClass = ".ipc-see-more__button";
        //String nextButtonXpath = "/html/body/div[2]/main/div[2]/div[3]/section/section/div/section/section/div[2]/div/section/div[2]/div[2]/div[2]/div/span/button";
        //WebElement button = wait.until(driver -> driver.findElement(By.xpath(nextButtonXpath)));
        WebElement button = wait.until(driver -> driver.findElement(By.cssSelector(buttonClass)));
        WebElement clickableButton = wait.until(ExpectedConditions.elementToBeClickable(button));
        actionProvider.moveToElement(clickableButton);
        actionProvider.click(clickableButton).build().perform();
        // Wait for new elements to be loaded
        actionProvider.pause(Duration.ofSeconds(TIMEOUT)).build().perform();
        actionProvider.sendKeys(Keys.END).perform();
    }

    private void storeData() {
        actionProvider.pause(Duration.ofSeconds(TIMEOUT)).build().perform();
        String listParentClassName = ".detailed-list-view.ipc-metadata-list--base";
        String movieTitleClass = ".ipc-title__text";
        WebElement parentDiv = wait.until(driver -> driver.findElement(By.cssSelector(listParentClassName)));
        List<WebElement> movies = wait.until(driver -> parentDiv.findElements(By.tagName("li")));
        for (WebElement movie : movies) {
            String movieName = movie.findElement(By.cssSelector(movieTitleClass)).getText();
            movieNames.add(movieName);
        }
    }

    public void run() {
        connect();
        removeCookieOption();
        //Somehow i<4 does not trigger the last button call
        for (int i = 0; i < 5; i++) {
            populate();
        }
        storeData();
        quit();
        System.out.println(movieNames);
        System.out.println(movieNames.size());
    }

    private boolean validateUrl(String url) {
        return true;
    }

    private void initializeActionProvider() {
        actionProvider = new Actions(driver);
    }

    private void initializeWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    private void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
    }

    private void connect() {
        driver.get(url);
    }

    private void quit() {
        actionProvider.pause(Duration.ofSeconds(TIMEOUT)).build().perform();
        driver.quit();
    }

    public ArrayList<String> getMovieNames() {
        return movieNames;
    }


}
