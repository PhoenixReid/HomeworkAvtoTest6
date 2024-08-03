package Page;
import Data.DataHelper;
import Data.DataHelper.AuthInfo;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Auth {
    private final SelenideElement inputLogin =$("[data-test-id=login] input");
    private final SelenideElement inputPassword =$("[data-test-id=password] input");
    private final SelenideElement  bullonlogin = $("[data-test-id=\"action-login\"]");


   public Auth verifyLogin(DataHelper.AuthInfo getAuthInfo){
       inputLogin.setValue(getAuthInfo.getLogin());
       inputPassword.setValue(getAuthInfo.getPassword());
       bullonlogin.click();

        return new Auth();
   }

}
