package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.exceptions.VerifyException;
import yehor.epam.services.VerifyService;
import yehor.epam.utilities.LoggerManager;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

import static yehor.epam.utilities.constants.OtherConstants.CAPTCHA_SECRET_KEY;
import static yehor.epam.utilities.constants.OtherConstants.CAPTCHA_URL;

/**
 * Class service for Google Recaptcha verification
 */
public class VerifyServiceImpl implements VerifyService {
    private static final Logger logger = LoggerManager.getLogger(VerifyServiceImpl.class);
    private static final String CLASS_NAME = VerifyServiceImpl.class.getName();
    private static final String USER_AGENT = "Mozilla/5.0";

    @Override
    public void captchaValidation(HttpServletRequest request, HttpServletResponse response) {
        try {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            logger.info("gRecaptchaResponse=" + gRecaptchaResponse);
            boolean verify = verify(gRecaptchaResponse);
            if (!verify) throw new VerifyException("Couldn't verify the captcha, try again");
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Verification of Google Recaptcha
     *
     * @param gRecaptchaResponse Google recaptcha string response
     * @return true if Google Recaptcha is successful and false if not
     */
    private boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            logger.warn("gRecaptchaResponse is null or empty, couldn't verify");
            return false;
        }
        try {
            URL obj = new URL(CAPTCHA_URL);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + CAPTCHA_SECRET_KEY + "&response=" + gRecaptchaResponse;
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(postParams);
            dataOutputStream.flush();
            dataOutputStream.close();

            int responseCode = connection.getResponseCode();
            logger.debug("Sending 'POST' request to URL : " + CAPTCHA_URL);
            logger.debug("Post parameters: " + postParams + ", response Code: " + responseCode);
            return readFromConnection(connection);
        } catch (Exception e) {
            logger.error("Exception while verifying recaptcha", e);
            throw new VerifyException("Exception while verifying recaptcha");
        }
    }

    /**
     * Reading received info from connection
     *
     * @param connection HttpsURLConnection
     * @return true if Google Recaptcha is successful and false if not
     * @throws IOException
     */
    private boolean readFromConnection(HttpsURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject.getBoolean("success");
    }
}
