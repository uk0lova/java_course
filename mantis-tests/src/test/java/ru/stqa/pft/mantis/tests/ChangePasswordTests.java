package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String password = "new_password";
        String[] userData = app.db().getUserData();

        if (userData == null) {
            System.out.println("No user found for changing password");
        } else {

            String user = userData[0];
            String email = userData[1];

            app.registration().login("administrator", "root");
            app.registration().resetPassword(user);

            List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().updatePassword(confirmationLink, user, password);
            assertTrue(app.newSession().login(user, password));
        }
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
