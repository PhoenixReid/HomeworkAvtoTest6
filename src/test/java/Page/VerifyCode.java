package Page;
import Data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
public class VerifyCode {
    private final SelenideElement inpulVerefyCode = $("[data-test-id='code'] input");
    private final SelenideElement bottonCode = $("[data-test-id=\"action-verify\"]");

    public VerifyCode verifyCode(DataHelper.CodeInfo getCodeInfo){
        inpulVerefyCode.setValue(getCodeInfo.getCode());
        bottonCode.click();
        $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").shouldBe(Condition.visible, Duration.ofSeconds(7));
        return new VerifyCode();

    }

}
