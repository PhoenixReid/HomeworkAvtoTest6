package page;

import com.codeborne.selenide.Condition;
import data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ToppingUpTheCard {
    private final SelenideElement summa = $("[data-test-id=\"amount\"] input");
    private  final SelenideElement from = $("[data-test-id=\"from\"] input");
    private  final SelenideElement to = $("[data-test-id=\"to\"] input");
    private final SelenideElement button = $("[data-test-id=\"action-transfer\"]");

    public void moneyTransfer(DataHelper.CardInfo card , int amount){

        summa.setValue(String.valueOf(amount));
        from.setValue(card.getNumber());
        button.click();

    }

    public static void visibleError(){
        $("[data-test-id=\"error-notification\"").shouldBe(Condition.visible,Duration.ofSeconds(5));
    }
}
