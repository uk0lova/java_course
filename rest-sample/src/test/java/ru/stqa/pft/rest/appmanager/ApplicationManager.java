package ru.stqa.pft.rest.appmanager;

public class ApplicationManager {

    private RestHCHelper restHCHelper;
    private RestAssuredHelper restAssuredHelper;

    public RestHCHelper restHC() {
        if (restHCHelper == null) {
            restHCHelper = new RestHCHelper(this);
        }
        return restHCHelper;
    }

    public RestAssuredHelper restAssured() {
        if (restAssuredHelper == null) {
            restAssuredHelper = new RestAssuredHelper(this);
        }
        return restAssuredHelper;
    }
}

