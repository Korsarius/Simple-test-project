package ua.nure.securityservice;

/**
 * This class represents a token entity.
 */
public class Token {

    private long tokenId;
    private long userId;
    private String securityToken;

    public Token() {
    }

    public Token(long tokenId, long userId, String securityToken) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.securityToken = securityToken;
    }

    public long tokenId() {
        return tokenId;
    }

    public long userId() {
        return userId;
    }

    public String securityToken() {
        return securityToken;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

}
