package page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
public class VerifyCode {
    private final SelenideElement inpulVerefyCode = $("[data-test-id='code'] input");
    private final SelenideElement bottonCode = $("[data-test-id=\"action-verify\"]");

    public ChoosingBankCards verifyCode(data.DataHelper.CodeInfo getCodeInfo){
        inpulVerefyCode.setValue(getCodeInfo.getCode());
        bottonCode.click();
        return new ChoosingBankCards();

    }

}
