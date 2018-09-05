import { AppPage } from './app.po';
import { browser, by, element } from 'protractor';
import { protractor } from 'protractor';

describe('MovieCruiserApp App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserApp');
  });

  it('should be redirected to /login page upon launching the application', () =>
  {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register page when clicking register button', () =>
  {
     element(by.cssContainingText('button', 'Register')).click();
     expect(browser.getCurrentUrl()).toContain('/register');
  });

  var EC = protractor.ExpectedConditions;
  it('should be able to register user', () =>
  {
     element(by.name('firstName')).sendKeys("Arulmurugan");
     element(by.name('lastName')).sendKeys("Sahnmugam");
     element(by.name('userName')).sendKeys("admin");
     var password = element(by.name('password'));
     browser.actions().mouseMove(password).perform();
     password.sendKeys("admin123");
     var registerBtn = element(by.cssContainingText('button', 'Register'));
     browser.actions().mouseMove(registerBtn).click().perform();
     expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login and navigate to popular movies', () =>
  {
     element(by.name('userName')).sendKeys("admin");
     element(by.name('password')).sendKeys("admin123");
     var loginBtn = element(by.cssContainingText('button', 'Login'));
     browser.actions().mouseMove(loginBtn).click().perform();
     expect(browser.getCurrentUrl()).toContain('/movies/popular');
  });

  it('should be able to search movies', () =>
  {
     element(by.cssContainingText('button', 'Search')).click();
     expect(browser.getCurrentUrl()).toContain('/movies/search');
     element(by.name('search-input')).sendKeys("man");
     element(by.name('search-input')).sendKeys(protractor.Key.ENTER);
     const searchItems = element.all(by.css(".movieTitle"));
     expect(searchItems.count()).toBe(20);
     expect(searchItems.get(0).getText()).toContain('Man');
  });

  it('should be able to add movie to watchlist', async() =>
  {
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    const addButtons = element.all(by.cssContainingText('button', 'Add to Watchlist'));
    browser.actions().mouseMove(addButtons.get(0)).click().perform();
    
  });

  it('should be able to navigate to watchlist', () =>
  {
    var watchListBtn = element(by.name('watchlist-button'));
    browser.actions().mouseMove(watchListBtn).click().perform();
    expect(browser.getCurrentUrl()).toContain('/movies/watchlist');
    const searchItems = element.all(by.css(".movieTitle"));
    expect(searchItems.count()).toBe(1);
    expect(searchItems.get(0).getText()).toContain('Ant-Man');
  });

  it('should be able to update movie comments', async() =>
  {
    var updateBtn = element(by.cssContainingText('button','Update'));
    browser.actions().mouseMove(updateBtn).click().perform();
    element(by.name('movie-comments-input')).sendKeys("lets watch it next week");
    var dialogUpdateBtn = element(by.name('dialog-update-button'));
    browser.actions().mouseMove(dialogUpdateBtn).click().perform();
    browser.sleep(1000);
  });

  it('should be able to delete movie from watchlist', async() =>
  {
    var deleteBtn = element(by.name('thumbnail-delete-button'));
    browser.actions().mouseMove(deleteBtn).click().perform();
    browser.sleep(1000);
    const searchItems = element.all(by.css(".movieTitle"));
    expect(searchItems.count()).toBe(0);
  });

  it('should be able to logout of application', () =>
  {
    var deleteBtn = element(by.cssContainingText('button','Logout'));
    browser.actions().mouseMove(deleteBtn).click().perform();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

});
