package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Commande;
import sn.pressingsicap.Demo.Repository.CommandeRepository;
import sn.pressingsicap.Demo.Repository.FactureRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@CacheConfig(cacheNames = "commandes")
public class CommandeServiceimpl implements CommandeService{

    private final CommandeRepository commandeRepository;
    private final FactureRepository factureRepository;

    public CommandeServiceimpl(CommandeRepository commandeRepository, FactureRepository factureRepository) {
        this.commandeRepository = commandeRepository;
        this.factureRepository = factureRepository;
    }

    @Override
    public void saveCommande(Commande commande) {
        commandeRepository.save(commande);

    }

    @Override
    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> listCommande() {
        return commandeRepository.findAll();

    }

    @Override
    public void deleteCommandeById(Long id) {
        commandeRepository.deleteById(id);
    }

}
