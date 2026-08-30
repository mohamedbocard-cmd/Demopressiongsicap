package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Paiement;
import sn.pressingsicap.Demo.Repository.CommandeRepository;
import sn.pressingsicap.Demo.Repository.PaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PaiementServiceImpl implements PaiementService{
    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public void savePaiement(Paiement paiement) {
        paiementRepository.save(paiement);


    }

    @Override
    public Optional<Paiement> getPaiementById(Long id) {
        return paiementRepository.findById(id);
    }

    @Override
    public List<Paiement> listPaiement() {
        return paiementRepository.findAll();

    }

    @Override
    public void deletePaiementById(Long id) {
        paiementRepository.deleteById(id);

    }
}
