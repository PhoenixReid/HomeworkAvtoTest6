import Page.Auth;
import Page.ChoosingBankCards;
import Page.ToppingUpTheCard;
import Page.VerifyCode;
import com.codeborne.selenide.Condition;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Data.DataHelper;
import org.junit.jupiter.api.TestInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
//java -jar ./artifacts/app-ibank-build-for-testers.jar
public class MoneyTransferTest {
    int firstBalance;
    int secondBalance;
    DataHelper.CardInfo firstCard;
    DataHelper.CardInfo secondCard;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        var authInfo = new DataHelper().getAuthInfo();
        var auth = new Auth().verifyLogin(authInfo);
        var codeInfo = new DataHelper().getCodeInfo();
        var authCode = new VerifyCode().verifyCode(codeInfo);

        firstCard = new DataHelper().getFirstCard();
        firstBalance = ChoosingBankCards.getCardBalance(firstCard.getId());
        secondCard = new DataHelper().getSecondCard();
        secondBalance = ChoosingBankCards.getCardBalance(secondCard.getId());
        ChoosingBankCards.choosingCard(firstCard);
    }


    @Test
    void TheTranslationValidSummaTest() {
        int amount = DataHelper.randomSumma(firstBalance);
        new ToppingUpTheCard().moneyTransfer(secondCard, amount);
        int actualFirstBalance = ChoosingBankCards.getCardBalance(firstCard.getId());
        Assertions.assertEquals(firstBalance + amount, actualFirstBalance);
        int actualSecondBalance = ChoosingBankCards.getCardBalance(secondCard.getId());
        Assertions.assertEquals(secondBalance - amount, actualSecondBalance);
    }

    @Test
    void TheTranslationNoValidSummaTest() {
        int amount = DataHelper.noValidRandomSumma(firstBalance);
        new ToppingUpTheCard().moneyTransfer(secondCard, amount);
        $("[data-test-id=\"error-notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(5));
    }
    
}