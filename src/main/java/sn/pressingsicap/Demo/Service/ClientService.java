package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sn.pressingsicap.Demo.Exception.ClientNotFoundException;
import sn.pressingsicap.Demo.Models.Client;
import sn.pressingsicap.Demo.Repository.ClientRepository;

import java.util.List;
@Transactional
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "clients")
public class ClientService {
    private ClientRepository clientRepository;


    public Client enregistrerClient(@RequestBody Client client) {
        // Vérifie si le numéro de téléphone est déjà utilisé en base
        if (clientRepository.findByTelephone(client.getTelephone()).isPresent()) {
            throw new ClientNotFoundException("Un client existe déjà avec ce numéro de téléphone dans la BDD.");
        }
        return clientRepository.save(client);
    }

    public List<Client> listerTousLesClients() {
        return clientRepository.findAll();
    }

    public Client chercherParTelephone(String telephone) {
        return clientRepository.findByTelephone(telephone)
                .orElseThrow(() -> new ClientNotFoundException("Client introuvable avec le téléphone : " + telephone));
    }

    public Client modifierClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client non trouvé"));

        return clientRepository.saveAndFlush(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }


}
