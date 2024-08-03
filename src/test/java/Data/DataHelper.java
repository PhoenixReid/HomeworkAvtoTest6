
package Data;
import com.codeborne.selenide.ElementsCollection;
import lombok.Value;
import lombok.val;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.impl.Html.text;


public class DataHelper {



    public  AuthInfo getAuthInfo(){
        return new AuthInfo("vasya","qwerty123");
    }

    public  CodeInfo getCodeInfo(){
        return new CodeInfo("12345");
    }
    public CardInfo getFirstCard(){
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public CardInfo getSecondCard(){
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int randomSumma(int balance){

        return new Random().nextInt(Math.abs(balance));
    }

    public static int noValidRandomSumma(int balance){

        return Math.abs(balance) + new Random().nextInt(Math.abs(balance));
    }




    @Value
    public static class  AuthInfo{
        String login;
        String password;
    }
    @Value
    public static class  CodeInfo{
        String code;
    }
    @Value
    public static class CardInfo{
        String number;
        String id;
    }

}



