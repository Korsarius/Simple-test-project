package nure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.nure.securityservice.SecurityToken;

public class SecurityTokenTest {

    @Test
    void testCreateAuthorizationToken() throws IllegalArgumentException {
        SecurityToken token = new SecurityToken.Builder().withToken("Token").build();
        Assertions.assertEquals("Token", token.securityToken(), "Wrong token id was set!");
    }

}
