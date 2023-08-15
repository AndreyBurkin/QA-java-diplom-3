package url;

import Constants.Constant;
import io.restassured.RestAssured;


public class UrlApiBase {

    protected static void setUp() {
        RestAssured.baseURI = Constant.BURGER_URL;
    }
}
