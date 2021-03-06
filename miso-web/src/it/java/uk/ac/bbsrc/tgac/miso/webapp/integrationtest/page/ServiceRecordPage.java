package uk.ac.bbsrc.tgac.miso.webapp.integrationtest.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ServiceRecordPage extends FormPage<ServiceRecordPage.Field> {

  public static enum Field implements FormPage.FieldElement {
    ID(By.id("serviceRecordId"), FieldType.LABEL), //
    INSTRUMENT(By.id("instrumentName"), FieldType.LABEL), //
    TITLE(By.id("title"), FieldType.TEXT), //
    DETAILS(By.id("details"), FieldType.TEXTAREA), //
    SERVICED_BY(By.id("servicedByName"), FieldType.TEXT), //
    REFERENCE_NUMBER(By.id("referenceNumber"), FieldType.TEXT), //
    SERVICE_DATE(By.id("serviceDatePicker"), FieldType.DATEPICKER), //
    START_TIME(By.id("startTime"), FieldType.DATEPICKER), //
    OUT_OF_SERVICE(By.id("outOfService"), FieldType.CHECKBOX), //
    END_TIME(By.id("endTime"), FieldType.DATEPICKER);

    private final By selector;
    private final FieldType type;

    private Field(By selector, FieldType type) {
      this.selector = selector;
      this.type = type;
    }

    @Override
    public By getSelector() {
      return selector;
    }

    @Override
    public FieldType getType() {
      return type;
    }
  } // end Field enum

  @FindBy(id = "save")
  private WebElement saveButton;

  public ServiceRecordPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    waitWithTimeout().until(titleContains("Service Record "));
  }

  public static ServiceRecordPage get(WebDriver driver, String baseUrl, Long sequencerId, Long serviceRecordId) {
    if (sequencerId == null && serviceRecordId == null) {
      throw new IllegalArgumentException("Must specify either instrument ID or service record ID");
    } else if (serviceRecordId == null) {
      driver.get(baseUrl + "miso/instrument/servicerecord/new/" + sequencerId);
    } else {
      driver.get(baseUrl + "miso/instrument/servicerecord/" + serviceRecordId);
    }
    return new ServiceRecordPage(driver);
  }

  public ServiceRecordPage save() {
    WebElement html = getHtmlElement();
    saveButton.click();
    waitForPageRefresh(html);
    return new ServiceRecordPage(getDriver());
  }

}
