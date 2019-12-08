package ua.nure.securityservice;

import ua.nure.entity.User;
import ua.nure.repository.TokenRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * This class represents a security service, that checks and generates a security token.
 */
public class SecurityService {

    private final TokenRepository tokenRepository;

    /**
     * Creates security service with token repository.
     *
     * @param tokenRepository the instance of {@link TokenRepository}
     */
    public SecurityService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /**
     * Creates security token to a user, that is authorized.
     *
     * @param user the user entity
     * @return the security token {@link SecurityToken} for a user
     */
    public SecurityToken createSecurityToken(User user) {
        String notEncryptedUserInformation = user.login() + user.password();
        SecurityToken securityToken = new SecurityToken.Builder()
                .withToken(MD5Generator(notEncryptedUserInformation)).build();
        Token token = new Token();
        token.setUserId(user.id());
        token.setSecurityToken(securityToken.securityToken());
        tokenRepository.add(token);
        return securityToken;
    }

    /**
     * Checks a security token. If it is invalid the {@link CheckTokenException} will be thrown.
     *
     * @param securityToken the user's security token
     * @throws CheckTokenException is thrown if a security token is invalid.
     */
    public void checkSecurityToken(SecurityToken securityToken) throws CheckTokenException {
        Token token = tokenRepository.findBySecurityString(securityToken.securityToken());
        if (token == null) {
            throw new CheckTokenException("The security token is not valid");
        }
        if (!securityToken.securityToken().equals(token.securityToken())) {
            throw new CheckTokenException("The security token is not valid");
        }
    }


    /**
     * Returns the encrypted string by MD5 algorithm.
     *
     * @param notEncryptedData the not encrypted string
     * @return the encrypted string by MD5 algorithm
     */
    public String MD5Generator(String notEncryptedData) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(notEncryptedData.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;

    }

}

