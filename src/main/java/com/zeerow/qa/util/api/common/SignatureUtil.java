package com.zeerow.qa.util.api.common;



import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public final class SignatureUtil {

    private static final String NEW_LINE = "\n";
    private static final String SIGNATURE_ALGORITHM = "HmacSHA1";
//    private static final Logger logger = Logger.getLogger(SignatureUtil.class);


    public static void main(String[] args) throws Exception {
        String password = sign("password", "devit-1-admin");

        System.out.println("password = " + password);
    }

    /**
     * Sign the secret
     *
     * @return encoded signature
     */
    public static String sign(String secret, String key)
            throws Exception {

        // converts MWSSecretKey into crypto instance.
        Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);
        byte[] keyBytes = key.getBytes(RestConstants.ENCODE_TYPE);
        Key signingKey = new SecretKeySpec(keyBytes, SIGNATURE_ALGORITHM);
        mac.init(signingKey);

        // Signed String must be BASE64 encoded.
        byte[] signBytes = mac.doFinal(secret.getBytes(RestConstants.ENCODE_TYPE));
        //            logger.debug("Signature Before encode: " + Arrays.toString(signBytes));
        byte[] signature = Base64.encodeBase64(signBytes);
        //            logger.debug("Signature After encode : [" + signature + "]");

        return new String(signature);

    }

    /**
     * Computes the signature from the given input data.
     *
     * @param password     Password
     * @param httpHeaderTO Http header information
     * @return Computed signature
     * @throws Exception If an exception occurs
     */
    public static String computeSignature(String password,HttpHeaderTO httpHeaderTO)
            throws Exception {

        // Data needed for signature
        String method = null;
        String contentMD5 = null;
        String contentType = null;
        String path = null;
        String timestamp = null;

        if (httpHeaderTO != null) {
            contentMD5 = httpHeaderTO.getContentMD5();
            contentType = httpHeaderTO.getContentType();
            method = httpHeaderTO.getMethod();
            path = httpHeaderTO.getPath();
            timestamp = httpHeaderTO.getTimestamp();
        }

        // Generate signature
        StringBuilder buf = new StringBuilder();
        buf.append(method == null ? RestConstants.EMPTY_STRING : method).append(NEW_LINE);

        if (RequestTypeEnum.GET.name().equals(method) || RequestTypeEnum.DELETE.name().equals(method)) {

            buf.append(contentMD5 == null ? RestConstants.EMPTY_STRING : contentMD5).append(NEW_LINE);
            buf.append(contentType == null ? RestConstants.EMPTY_STRING : contentType).append(NEW_LINE);
        } else {

            buf.append(contentMD5).append(NEW_LINE);
            buf.append(contentType).append(NEW_LINE);
        }

        if (timestamp == null) {
            buf.append(System.currentTimeMillis() / 1000).append(NEW_LINE);
        } else {
            buf.append(timestamp).append(NEW_LINE);
        }
        buf.append(path == null ? RestConstants.EMPTY_STRING : path);

        return sign(buf.toString(), password);
    }

    /**
     * Method to generate the client authorization key
     *
     * @param username     the username
     * @param password     the password
     * @param httpHeaderTO the to object which contains the details required to generate the authorization key
     * @return returns the generated client authorization key
     */
    public static String generateClientAuthorizationKey(String username, String password, HttpHeaderTO httpHeaderTO) {

        String authKey = null;

        try {
            String signature = computeSignature(password, httpHeaderTO);

            // Auth key should be in the following format. AuthorizationMWS=<AccountAccessId?>:<Signature>:<Date>
            authKey =
                    RestConstants.MWS_KEY_NAME + RestConstants.EQUAL_SIGN + username + RestConstants.COLON + signature +
                            RestConstants.COLON + httpHeaderTO.getTimestamp();
        } catch (Exception e) {
//            logger.error("Error in constructing signature.", e);
        }

        return authKey;
    }
}
