package yehor.epam.services.impl;

import org.slf4j.Logger;
import yehor.epam.services.ValidService;
import yehor.epam.utilities.LoggerManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static yehor.epam.utilities.constants.OtherConstants.*;

public class ValidServiceImpl implements ValidService {
    private static final Logger logger = LoggerManager.getLogger(ValidServiceImpl.class);

    @Override
    public void validLettersField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                                  String invalidParam, String lengthParam) {
        if (field == null || field.isBlank()) {
            errorList.add(emptyParam);
        } else {
            if (isInvalid(field, ONLY_LETTERS_PATTERN))
                errorList.add(invalidParam);
            else if (field.length() > maxLength || field.length() < minLength)
                errorList.add(lengthParam);
        }
        logger.debug("validLettersField: field: {}, minLength: {}, maxLength: {}, emptyParam: {}, invalidParam: {},lengthParam: {}",
                field, minLength, maxLength, emptyParam, invalidParam, lengthParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validDateField(List<String> errorList, String field, LocalDate minDate, LocalDate maxDate, String emptyParam,
                               String invalidParam, String rangeParam) {
        if (field == null || field.isBlank()) {
            errorList.add(emptyParam);
        } else {
            if (isInvalid(field, DATE_PATTERN)) {
                errorList.add(invalidParam);
            } else {
                LocalDate date = null;
                try {
                    date = LocalDate.parse(field);
                    if (date.isAfter(maxDate) || date.isBefore(minDate))
                        errorList.add(rangeParam);
                } catch (DateTimeParseException e) {
                    errorList.add(invalidParam);
                }
            }
        }
        logger.debug("validDateField: field: {}, min: {}, max: {}, emptyParam: {}, invalidParam: {}, rangeParam: {}",
                field, minDate, maxDate, emptyParam, invalidParam, rangeParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validTimeField(List<String> errorList, String field, LocalTime minTime, LocalTime maxTime, String emptyParam,
                               String invalidParam, String rangeParam) {
        if (field == null || field.isBlank()) {
            errorList.add(emptyParam);
        } else {
            if (isInvalid(field, TIME_PATTERN)) {
                errorList.add(invalidParam);
            } else {
                try {
                    LocalTime time = null;
                    time = LocalTime.parse(field);
                    if (time.isAfter(maxTime) || time.isBefore(minTime))
                        errorList.add(rangeParam);
                } catch (DateTimeParseException e) {
                    errorList.add(invalidParam);
                }
            }
        }
        logger.debug("validTimeField: field: {}, min: {}, max: {}, emptyParam: {}, invalidParam: {}, rangeParam: {}",
                field, minTime, maxTime, emptyParam, invalidParam, rangeParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validDigitsField(List<String> errorList, String field, int min, int max, String emptyParam,
                                 String invalidParam, String rangeParam) {
        if (field == null || field.isBlank()) {
            errorList.add(emptyParam);
        } else {
            if (isInvalid(field, ONLY_DIGITS_PATTERN))
                errorList.add(invalidParam);
            else if (Integer.parseInt(field) > max || Integer.parseInt(field) < min)
                errorList.add(rangeParam);
        }
        logger.debug("validDigitsField: field: {}, min: {}, max: {}, emptyParam: {}, invalidParam: {}, rangeParam: {}",
                field, min, max, emptyParam, invalidParam, rangeParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validStringField(List<String> errorList, String field, int minLength, int maxLength, String emptyParam,
                                 String lengthParam) {
        if (field == null || field.isBlank()) {
            errorList.add(emptyParam);
        } else {
            if (field.length() > maxLength || field.length() < minLength)
                errorList.add(lengthParam);
        }
        logger.debug("validStringField: field: {}, minLength: {}, maxLength: {}, emptyParam: {}, lengthParam: {}",
                field, minLength, maxLength, emptyParam, lengthParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validNullableStringField(List<String> errorList, String field, int maxLength, String lengthParam) {
        if (field != null) {
            if (field.length() > maxLength)
                errorList.add(lengthParam);
        }
        logger.debug("validStringField: field: {}, maxLength: {}, lengthParam: {}",
                field, maxLength, lengthParam);
        logger.debug("errorList: " + errorList);
    }

    @Override
    public void validEmailField(List<String> errorList, String field) {
        if (field == null || field.isBlank()) {
            errorList.add(VALID_EMAIL_EMPTY);
        } else {
            if (isInvalid(field, EMAIL_PATTERN))
                errorList.add(VALID_EMAIL_INVALID);
            else if (field.length() > MAX_EMAIL_LENGTH || field.length() < MIN_EMAIL_LENGTH)
                errorList.add(VALID_EMAIL_LENGTH);
        }
    }

    @Override
    public void validPassField(List<String> errorList, String field) {
        if (field == null || field.isBlank()) {
            errorList.add(VALID_PASS_EMPTY);
        } else {
            if (field.length() > MAX_PASS_LENGTH || field.length() < MIN_PASS_LENGTH)
                errorList.add(VALID_PASS_LENGTH);
        }
    }

    @Override
    public void validPassConfirmField(List<String> errorList, String pass, String confirmPass) {
        if (confirmPass == null || confirmPass.isBlank()) {
            errorList.add(VALID_PASS_CONFIRM_EMPTY);
        } else {
            if (!pass.equals(confirmPass))
                errorList.add(VALID_PASS_CONFIRM_NOT_EQUAL);
        }
    }

    @Override
    public void validPhoneNumberField(List<String> errorList, String field) {
        if (field != null && !field.isBlank()) {
            if (isInvalid(field, PHONE_NUMBER_PATTERN))
                errorList.add(VALID_PHONE_INVALID);
        }
    }

    @Override
    public boolean isInvalid(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return !matcher.matches();
    }
}
