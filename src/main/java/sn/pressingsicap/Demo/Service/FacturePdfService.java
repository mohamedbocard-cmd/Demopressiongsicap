package sn.pressingsicap.Demo.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Facture;
import sn.pressingsicap.Demo.Repository.FactureRepository;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class FacturePdfService {

    private final FactureRepository factureRepository;

    public byte[] genererFacturePdf(Long id) throws Exception {

        // 1. Récupérer la facture
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Facture introuvable avec l'id : " + id));

        // 2. Vérifier la commande
        if (facture.getCommande() == null) {
            throw new RuntimeException(
                    "Aucune commande associée à cette facture"
            );
        }

        // 3. Charger le fichier JRXML
        InputStream inputStream = getClass()
                .getResourceAsStream("/reports/facture.jrxml");

        /// on va tester si le inputStream est isEmpty || null
        if (inputStream == null) {
            throw new RuntimeException(
                    "Fichier /reports/facture.jrxml introuvable"
            );
        }

        // 4. Compiler le JRXML
        ///JasperReport : il est utiliser pour generer un document , une facture etc...

        JasperReport jasperReport =
                JasperCompileManager.compileReport(inputStream);

        // 5. Paramètres du rapport
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(
                "numeroFacture",
                facture.getNumeroFacture()
        );

        parameters.put(
                "dateCreation",
                facture.getDateCreation()
        );

        parameters.put(
                "client",
                facture.getClient()
        );

        parameters.put(
                "commande",
                facture.getCommande()
        );

        parameters.put(
                "article",
                facture.getArticle()
        );

        parameters.put(
                "montantTotal",
                facture.getMontant_total()
        );

        // 6. DataSource
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(
                        Collections.singletonList(facture.getCommande())
                );

        // 7. Remplir le rapport
        JasperPrint jasperPrint =JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        dataSource
                );

        // 8. Générer le PDF
        //JasperExportManager:   est la classe JasperReports qui permet d'exporter un
        // rapport JasperPrint vers différents formats, notamment PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}