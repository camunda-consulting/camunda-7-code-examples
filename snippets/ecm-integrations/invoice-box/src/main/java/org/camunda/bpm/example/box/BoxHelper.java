package org.camunda.bpm.example.box;

import org.apache.commons.io.IOUtils;

import com.box.sdk.*;

/**
 * BoxHelper.  A Helper class for managing Box App User's/
 * Stores and retwieves names and is in the current HTTP Session Object
 * Ceates ne App users or looks up existin app users by name within the enterprise
 * associated with this applications.
 */
public final class BoxHelper {

    static final String CLIENT_ID = ConfigHelper.properties().getProperty("boxClientId");
    static final String CLIENT_SECRET = ConfigHelper.properties().getProperty("boxClientSecret");
    static final String PRIVATE_KEY_FILE = ConfigHelper.properties().getProperty("boxPrivateKeyFile");
    static final String PRIVATE_KEY_PASSWORD = ConfigHelper.properties().getProperty("boxPrivateKeyPassword");
    static final String PUBLIC_KEY_ID = ConfigHelper.properties().getProperty("boxPublicKeyId");

    static final int MAX_CACHE_ENTRIES = 100;

    private static IAccessTokenCache accessTokenCache;
    private static JWTEncryptionPreferences jwtEncryptionPreferences;

    /**
     * Private constructor since this is a utility class.
     */
    private BoxHelper() {

    }

    static {
        String privateKey;
        try {
        	System.out.println(PRIVATE_KEY_FILE);
            privateKey = IOUtils.toString(BoxHelper.class.getResourceAsStream("/conf/"+PRIVATE_KEY_FILE),"UTF-8");
        } catch (Exception ex) {
            throw new BoxAPIException("Unable to read private key file: " + PRIVATE_KEY_FILE);
        }

        jwtEncryptionPreferences = new JWTEncryptionPreferences();
        jwtEncryptionPreferences.setPublicKeyID(PUBLIC_KEY_ID);
        jwtEncryptionPreferences.setPrivateKey(privateKey);
        jwtEncryptionPreferences.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        jwtEncryptionPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
    }

    /**
     * Get a BoxDeveloperEditionAPIConnection that can be used by an App user to access Box.
     *
     * @param userId The UserId of a valid Box App User
     * @return BoxDeveloperEditionAPIConnection new Box Developer Edition connection with App User token
     */
    public static BoxDeveloperEditionAPIConnection userClient(String userId) {
        if (userId == null) { //   session data has expired
            return null;
        }
        System.out.format("userClient called with userId %s \n\n", userId);
        try {
            BoxDeveloperEditionAPIConnection userClient = BoxDeveloperEditionAPIConnection.getAppUserConnection(
                    userId, CLIENT_ID, CLIENT_SECRET, jwtEncryptionPreferences, accessTokenCache);
            return userClient;
        } catch (BoxAPIException apiException) {
            apiException.printStackTrace();
            throw apiException;
        }
    }
}
