package org.example.bussnesslogic.validators;
/**
 * <p>Interface for validating objects</p>
 * @param <T> type of object
 */
public interface Validator<T> {
    /**
     * <p>Generic method to validate objects manipulated by the application</p>
     * @param crtObject current object
     */
    void validate(T crtObject);
}
