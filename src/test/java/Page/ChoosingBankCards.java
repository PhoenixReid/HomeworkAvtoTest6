package Page;

import Data.DataHelper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.impl.Html.text;

public class ChoosingBankCards {
    private static String balanceStart = "баланс: ";
    private static String balanceFinish = " р.";
    private final SelenideElement button = $("[data-test-id=\"action-deposit\"]");


    public static  int getCardBalance(String id) {
        String text = $(by("data-test-id", id)).getText();
        int balance = extractBalance(text);
        return balance;
    }

    private static  int extractBalance(String text) {

        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

//
    public  static void choosingCard(DataHelper.CardInfo card){
        $(by("data-test-id", card.getId())).$("[data-test-id=\"action-deposit\"]").click();
        $("[data-test-id=\"dashboard\"]").shouldBe(Condition.visible, Duration.ofSeconds(5));
    }





}

