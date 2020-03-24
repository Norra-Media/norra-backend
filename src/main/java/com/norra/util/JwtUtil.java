package com.norra.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;

import com.norra.constants.Constants;

import java.util.Date;

public class JwtUtil {

    private JwtUtil() {}

    /**
     * This method is used to return UserId by decoding the JWT token and checking the expiry of the token.
     *
     * @param : jwtToken - JWT token of type String
     * @return : returns user Id of type String
     */
    public static Long getUserIdFromAuthToken(String jwtToken) {
        Long userId;
        String body;
        try {
            String[] splitString = jwtToken.split("\\.");
            String base64EncodedHeader = splitString[0];
            String base64EncodedBody = splitString[1];

            Base64 base64Url = new Base64(true);
            String header = new String(base64Url.decode(base64EncodedHeader));
            if (header.equals(null) || header.equals("")) {
                throw new BadCredentialsException(Constants.BAD_CREDENTIALS_EXCEPTION);
            }
            body = new String(base64Url.decode(base64EncodedBody));
        } catch (Exception e) {
            throw new BadCredentialsException(Constants.BAD_CREDENTIALS_EXCEPTION);
        }

        JSONObject dateExpiry = null;
        try {
            dateExpiry = new JSONObject(new String(body));
            String epochString = dateExpiry.get("exp").toString();
            long epoch = Long.parseLong(epochString);
            Date expiryDateTime = new Date(epoch * Constants.JWT_TOKEN_EPOCH);
            Date currentDateTime = new Date();
            int checkExpiry = expiryDateTime.compareTo(currentDateTime);
            if (checkExpiry < 0) {
                throw new BadCredentialsException(Constants.BAD_CREDENTIALS_EXCEPTION);
            }
            userId = Long.valueOf(dateExpiry.get("sub").toString());
        } catch (JSONException e) {
            throw new BadCredentialsException(Constants.BAD_CREDENTIALS_EXCEPTION);
        }


        return userId;
    }

}
