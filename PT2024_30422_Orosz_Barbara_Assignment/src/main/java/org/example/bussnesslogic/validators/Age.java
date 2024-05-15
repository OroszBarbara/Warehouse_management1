package org.example.bussnesslogic.validators;

import org.example.model.Client;
import org.example.presentation.Frame;
/**
 * Client age validator
 */
public class Age implements Validator<Client> {
    private static final int minAge = 18;
    private static int maxAge = 101;
    private static final String messageError = "The age of the client needs to be over 18 or under 101!";

    private static boolean changeEnabled = false;

    /**
     * <p>cheacks that a client's age is valid</p>
     * @param crtClient current client
     */
    @Override
    public void validate(Client crtClient) {
        if (crtClient.getAge() < minAge || crtClient.getAge() > maxAge) {
            Frame.showAlert(messageError);
            throw new IllegalArgumentException(messageError);
        }
    }

    /**
     * <p>Ensures that a client's age is valid</p>
     * @param newAge age to be changed
     */
    public void changeAgequalifications(Client newAge) {
        if (newAge.getAge() > 101 && changeEnabled == true) {
            maxAge = newAge.getAge();
        }


    }
}