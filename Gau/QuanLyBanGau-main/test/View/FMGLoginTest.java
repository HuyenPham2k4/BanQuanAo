/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package View;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author qivub
 */
public class FMGLoginTest {
    FMGLogin login = new FMGLogin();
    public FMGLoginTest() {
    }

    @Test
    public void testSomeMethod() {
        String log = login.Login("1","123");
        String user = "1";
        String pass = "123";
        String kiemtra = user +":"+ pass;
        assertEquals(user, log);
    }
    
}
