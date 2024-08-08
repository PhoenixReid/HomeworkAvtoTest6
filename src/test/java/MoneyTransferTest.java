


import page.*;
import com.codeborne.selenide.Condition;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.*;

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
        var authInfo = new data.DataHelper().getAuthInfo();
        var auth = new Auth().verifyLogin(authInfo);
        var codeInfo = new DataHelper().getCodeInfo();
        var authCode = new VerifyCode().verifyCode(codeInfo);

        firstCard = new DataHelper().getFirstCard();
        firstBalance = new ChoosingBankCards().getCardBalance(firstCard.getId());
        secondCard = new DataHelper().getSecondCard();
        secondBalance = new ChoosingBankCards().getCardBalance(secondCard.getId());
        ChoosingBankCards.choosingCard(firstCard);
    }


    @Test
    void theTranslationValidSummaTest() {
        int amount = DataHelper.randomSumma(firstBalance);
        new ToppingUpTheCard().moneyTransfer(secondCard, amount);
        int actualFirstBalance = new ChoosingBankCards().getCardBalance(firstCard.getId());
        Assertions.assertEquals(firstBalance + amount, actualFirstBalance);
        int actualSecondBalance =new ChoosingBankCards().getCardBalance(secondCard.getId());
        Assertions.assertEquals(secondBalance - amount, actualSecondBalance);
    }

    @Test
    void theTranslationNoValidSummaTest() {
        int amount = DataHelper.noValidRandomSumma(firstBalance);
        new ToppingUpTheCard().moneyTransfer(secondCard, amount);
        new ToppingUpTheCard().visibleError();
    }

}