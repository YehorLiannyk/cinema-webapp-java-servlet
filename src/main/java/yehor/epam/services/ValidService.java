package yehor.epam.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Service for data validation
 */
public interface ValidService {
    /**
     * Validate field only letters containing
     *
     * @param errorList    List with handled errors
     * @param field        Field to check
     * @param minLength    Min field length
     * @param maxLength    Max field length
     * @param emptyParam   Error param for empty field
     * @param invalidParam Error param for invalid field
     * @param lengthParam  Error param for length error field
     */
    void validLettersField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                           String invalidParam, String lengthParam);

    /**
     * Validate field only date containing
     *
     * @param errorList    List with handled errors
     * @param field        Field to check
     * @param minDate      Min field date
     * @param maxDate      Max field date
     * @param emptyParam   Error param for empty field
     * @param invalidParam Error param for invalid field
     * @param rangeParam   Error param for range error field
     */
    void validDateField(List<String> errorList, String field, LocalDate minDate, LocalDate maxDate, String emptyParam,
                        String invalidParam, String rangeParam);

    /**
     * Validate field only time containing
     *
     * @param errorList    List with handled errors
     * @param field        Field to check
     * @param minTime      Min field time
     * @param maxTime      Max field time
     * @param emptyParam   Error param for empty field
     * @param invalidParam Error param for invalid field
     * @param rangeParam   Error param for range error field
     */
    void validTimeField(List<String> errorList, String field, LocalTime minTime, LocalTime maxTime, String emptyParam,
                        String invalidParam, String rangeParam);

    /**
     * Validate field only digits containing
     *
     * @param errorList    List with handled errors
     * @param field        Field to check
     * @param min          Min field value
     * @param max          Max field value
     * @param emptyParam   Error param for empty field
     * @param invalidParam Error param for invalid field
     * @param rangeParam   Error param for range error field
     */
    void validDigitsField(List<String> errorList, String field, int min, int max, String emptyParam,
                          String invalidParam, String rangeParam);

    /**
     * Validate field only letters containing
     *
     * @param errorList   List with handled errors
     * @param field       Field to check
     * @param minLength   Min field length
     * @param maxLength   Max field length
     * @param emptyParam  Error param for empty field
     * @param lengthParam Error param for length error field
     */
    void validStringField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                          String lengthParam);

    /**
     * Validate ordinary String field which can be empty or null
     *
     * @param errorList   List with handled errors
     * @param field       Field to check
     * @param maxLength   Max field length
     * @param lengthParam Error param for length error field
     */
    void validNullableStringField(List<String> errorList, String field, int maxLength, String lengthParam);

    /**
     * Validate field email containing
     *
     * @param errorList List with handled errors
     * @param field     Field to check
     */
    void validEmailField(List<String> errorList, String field);

    /**
     * Validate field password containing
     *
     * @param errorList List with handled errors
     * @param field     Field to check
     */
    void validPassField(List<String> errorList, String field);

    /**
     * Validate password and password confirmation equality
     *
     * @param errorList   List with handled errors
     * @param pass        password
     * @param confirmPass password conformation
     */
    void validPassConfirmField(List<String> errorList, String pass, String confirmPass);

    /**
     * Validate field phone number containing
     *
     * @param errorList List with handled errors
     * @param field     Field to check
     */
    void validPhoneNumberField(List<String> errorList, String field);

    /**
     * Check matching value to regex
     *
     * @param value Value to check
     * @param regex Regex
     * @return true - value matches regex, otherwise false
     */
    boolean isInvalid(String value, String regex);
}
