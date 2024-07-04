package org.example.DDT;

import org.example.Utils.UtilExcel;
import org.testng.annotations.Test;

public class LoginwithDDT {

    @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
    public void testLoginData(String username, String password) {
        System.out.println("UserName - " + username);
        System.out.println("Password - " + password);
        // Add your test code here, e.g., Rest Assured code
    }
}