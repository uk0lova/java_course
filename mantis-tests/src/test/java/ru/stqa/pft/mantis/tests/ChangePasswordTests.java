package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String password = "new_password";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery
                    ("select username, email from mantis_user_table");
            rs.first();
            rs.next();
            String user=rs.getString("username");
            String email =rs.getString("email");
            System.out.println(rs.getString("username"));

            app.registration().login("administrator", "root");
            app.registration().resetPassword(user);

            List<MailMessage> mailMessages = app.mail().waitForMail(2, 20000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().updatePassword(confirmationLink, user, password);
            assertTrue(app.newSession().login(user, password));

            rs.close();
            st.close();
            conn.close();

         } catch (
    SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
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
