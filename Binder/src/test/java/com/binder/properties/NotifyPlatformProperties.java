package com.binder.properties;

/**
 * Notify platform properties.
 */
public class NotifyPlatformProperties {

    /**
     * Platform
     */
    private String platform;

    /**
     * Secret key. {@link NotifyPlatformProperties#token}
     */
    @Deprecated
    private String secretKey;

    /**
     * Token
     */
    private String token;

    /**
     * Secret
     */
    private String secret;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "NotifyPlatformProperties{" +
                "platform='" + platform + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", token='" + token + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
