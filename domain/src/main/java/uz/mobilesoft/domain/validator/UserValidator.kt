package uz.mobilesoft.domain.validator

import java.util.regex.Pattern

/**
 * A utility class for validating user inputs.
 *
 * This class provides methods to validate email, phone numbers and passwords according to specified rules.
 **/
object UserValidator {

    /**
     * A lazily-initialized regex pattern for validating email addresses.
     *
     * This regex pattern checks if the provided string is a valid email address
     * according to the general structure of typical email addresses. The email
     * should consist of alphanumeric characters, dots, underscores, or hyphens
     * before the "@" symbol, followed by a domain name that can contain
     * alphanumeric characters and dots, and a top-level domain that is at least
     * two characters long.
     *
     * Pattern:
     * - Begins with one or more alphanumeric characters, dots, underscores, or hyphens.
     * - Contains the "@" symbol.
     * - Followed by one or more alphanumeric characters or dots.
     * - Contains a dot before the top-level domain.
     * - Ends with a top-level domain that is at least two characters long.
     *
     * Example valid email addresses:
     * - user@example.com
     * - user.name@example.uz
     * - user-name@example.ru
     * - xx@yy.zz
     */
    private val emailRegex by lazy(LazyThreadSafetyMode.NONE) {
        Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    }

    /**
     * A lazily-initialized regex pattern for validating US phone numbers.
     *
     * This regex pattern checks if the provided string is a valid US phone number
     * in the format `xxx-xxx-xxxx`, where `x` represents a digit.
     *
     * Pattern:
     * - Begins with three digits.
     * - Followed by a hyphen.
     * - Followed by three digits.
     * - Followed by a hyphen.
     * - Ends with four digits.
     *
     * Example valid US phone numbers:
     * - 123-456-7890
     * - 555-123-4567
     * - xxx-xxx-xxxx
     */
    private val usPhoneNumberRegex by lazy(LazyThreadSafetyMode.NONE) {
        Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$")
    }

    /**
     * A lazily-initialized regex pattern for validating Uzbek phone numbers.
     *
     * This regex pattern checks if the provided string is a valid Uzbek phone number
     * in the format `+998 xx xxx xx xx`, where `x` represents a digit.
     *
     * Pattern:
     * - Begins with the literal characters `+998`.
     * - Followed by a space.
     * - Followed by two digits.
     * - Followed by a space.
     * - Followed by three digits.
     * - Followed by a space.
     * - Followed by two digits.
     * - Followed by a space.
     * - Ends with two digits.
     *
     * Example valid Uzbek phone numbers:
     * - +998 90 123 45 67
     * - +998 99 876 54 32
     * - +998 xx xxx xx xx
     */
    private val uzPhoneNumberRegex by lazy(LazyThreadSafetyMode.NONE) {
        Pattern.compile("^\\+998\\s\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}$")
    }

    /**
     * Validates if the provided email address is valid.
     *
     * This method checks if the provided email address is valid according to the
     * email regex pattern. The email is considered valid if it matches the regex
     * pattern and is not blank.
     *
     * @param email the email address to validate
     * @return `true` if the email address is valid, `false` otherwise
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && emailRegex.matcher(email).matches()
    }

    /**
     * Validates if the provided phone number is valid based on the specified country code.
     *
     * This method checks if the provided phone number is valid according to the regex pattern
     * associated with the given country code. By default, the country code is set to `CountryCode.UZ`.
     * The phone number is considered valid if it matches the appropriate regex pattern and is not blank.
     *
     * @param phoneNumber the phone number to validate
     * @param countryCode the country code used to determine the validation regex pattern (default is `CountryCode.UZ`)
     * @return `true` if the phone number is valid, `false` otherwise
     */
    fun isValidPhoneNumber(
        phoneNumber: String,
        countryCode: CountryCode = CountryCode.UZ
    ): Boolean {
        val phoneNumberRegex = when (countryCode) {
            CountryCode.UZ -> uzPhoneNumberRegex
            CountryCode.US -> usPhoneNumberRegex
        }
        return phoneNumber.isNotBlank() && phoneNumberRegex.matcher(phoneNumber).matches()
    }

    /**
     * Validates if the provided password is valid.
     *
     * The password is considered valid if it is at least 5 characters long.
     *
     * @param password the password to validate
     * @return `true` if the password is valid, `false` otherwise
     **/
    fun isValidPassword(password: String): Boolean {
        return password.isNotBlank() && password.length >= 5
    }

    /**
     * Validates if the provided password matches the confirmation password.
     *
     * @param password the password to validate
     * @param confirmPassword the confirmation password to compare against
     * @return `true` if the passwords match, `false` otherwise
     **/
    fun isValidConfirmPassword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password.isNotBlank() && password == confirmPassword
    }
}

/**
 * We use this Enum class to get the phone numbers
 * of the desired countries
 **/
enum class CountryCode {
    UZ, US
}