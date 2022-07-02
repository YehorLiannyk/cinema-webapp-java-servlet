package yehor.epam.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ValidService {
    void validLettersField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                           String invalidParam, String lengthParam);

    void validDateField(List<String> errorList, String field, LocalDate minDate, LocalDate maxDate, String emptyParam,
                        String invalidParam, String rangeParam);

    void validTimeField(List<String> errorList, String field, LocalTime minTime, LocalTime maxTime, String emptyParam,
                        String invalidParam, String rangeParam);

    void validDigitsField(List<String> errorList, String field, int min, int max, String emptyParam,
                          String invalidParam, String rangeParam);

    void validStringField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                          String lengthParam);

    void validNullableStringField(List<String> errorList, String field, int maxLength, String lengthParam);

    void validEmailField(List<String> errorList, String field);

    void validPassField(List<String> errorList, String field);

    void validPassConfirmField(List<String> errorList, String pass, String confirmPass);

    void validPhoneNumberField(List<String> errorList, String field);

    boolean isInvalid(String value, String regex);
}
