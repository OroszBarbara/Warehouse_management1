package org.example.model.BLL;

import org.example.DAO.ClientDAO;
import org.example.bussnesslogic.validators.Age;
import org.example.bussnesslogic.validators.Email;
import org.example.bussnesslogic.validators.Validator;
import org.example.model.BLL.AbstractBLL;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;
/**
 * <p> client BLL</p>
 */
public class ClientBLL extends AbstractBLL<Client> {

    private final ClientDAO clientDAO = new ClientDAO();
    private final List<Validator<Client>> validators = new ArrayList<>();

    /**
     * <p>constructor adding the validators</p>
     */
    public ClientBLL() {
        validators.add(new Age());
        validators.add(new Email());
    }

    /**
     * <p>Tries to validate a client and, if the client is valid, makes call to insert it into our  database</p>
     * @param newClient client for adding
     * @return inserted client id
     */
    public int createClient(Client newClient) {
        for (Validator<Client> crtValidator : validators) {
            crtValidator.validate(newClient);
        }
        return clientDAO.insert(newClient);
    }

    /**
     * <p>validates a client and, if the client it is indeed valid, makes call to update an existing client from database</p>
     * @param toUpdate updated client
     * @return boolean value representing success or fail
     */
    public boolean editClient(Client toUpdate) {
        for (Validator<Client> crtValidator : validators) {
            crtValidator.validate(toUpdate);
        }
        return clientDAO.update(toUpdate);
    }

    /**
     * <p>earches a client in database</p>
     * @param clientId id of client that needs to be searched
     * @return the found client or null
     */

    public Client searchClient(int clientId) {
        return clientDAO.findById(clientId);
    }

    /**
     * <p>Searches a client in database</p>
     * @param clientName id of client that needs to be searched
     * @return the found client or null
     */
    public Client searchClient(String clientName) {
        return clientDAO.findByName(clientName);
    }

    /**
     * <p>removes a client from database</p>
     * @param clientId id of the client to be removed
     * @return boolean value representing success or fail
     */
    public boolean removeClient(int clientId) {
        return clientDAO.remove(clientId);
    }

    /**
     * <p>selects all the clients from database</p>
     * @return a list of all the clients existing in database
     */
    public ArrayList<Client> viewClients() {
        return clientDAO.findAll();
    }
}
