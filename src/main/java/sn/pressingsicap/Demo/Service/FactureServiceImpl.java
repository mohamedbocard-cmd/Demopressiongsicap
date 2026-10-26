package sn.pressingsicap.Demo.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sn.pressingsicap.Demo.Models.Facture;
import sn.pressingsicap.Demo.Repository.FactureRepository;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = "factures")
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public void CreerFacture(Facture facture) {
        factureRepository.save(facture);
    }

    @Override
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    @Override
    public List<Facture> listFacture() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFactureById(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public Page<Facture> getAllFactureByPage(int page, int size) {
        return factureRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public byte[] genererFacturePdf(Long id) {

        // Récupérer la facture
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Facture introuvable : " + id
                        )
                );

        try {

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            // Création du document PDF
            Document document = new Document();

            // Écriture du PDF dans le flux
            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            // =========================
            // TITRE
            // =========================

            Font titre = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    20
            );
            Paragraph paragraphTitre =
                    new Paragraph("Listes des Factures", titre);

            paragraphTitre.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraphTitre);

            document.add(new Paragraph(" "));

            // =========================
            // INFORMATIONS FACTURE
            // =========================

            document.add(
                    new Paragraph(
                            "Numéro facture : "
                                    + facture.getNumeroFacture()
                    )
            );

            document.add(
                    new Paragraph(
                            "Date : "
                                    + facture.getDateCreation()
                    )
            );

            document.add(new Paragraph(" "));

            // =========================
            // CLIENT
            // =========================

            if (facture.getClient() != null) {

                document.add(
                        new Paragraph(
                                "Client : "
                                        + facture.getClient().getNom()
                                        + " "
                                        + facture.getClient().getPrenom()
                        )
                );

                document.add(
                        new Paragraph(
                                "Téléphone : "
                                        + facture.getClient().getTelephone()
                        )
                );

                /// Commande/////
                if (facture.getCommande() != null) {

                    document.add(
                            new Paragraph(
                                    "Commande : "
                                            + facture.getCommande().getId()
                                            + " "
                                            + facture.getCommande().getNumero_cmd()
                            )

                    );
                    /// commande
                }

                /// Article/////
                if (facture.getArticle() != null) {

                    document.add(
                            new Paragraph(
                                    "Article : "
                                            + facture.getArticle().getId()
                                            + " "
                                            + facture.getArticle().getNom())

                    );
                    /// Article/////

                }




            }

            document.add(new Paragraph(" "));

            // =========================
            // MONTANTS
            // =========================

            document.add(
                    new Paragraph(
                            "Quantité : "
                                    + facture.getQuantite()
                    )
            );

            document.add(
                    new Paragraph(
                            "Prix : "
                                    + facture.getPrix()
                                    + " FCFA"
                    )
            );




            document.add(
                    new Paragraph(
                            "Montant total : "
                                    + facture.getMontant_total()
                                    + " FCFA"
                    )
            );

            document.add(
                    new Paragraph(
                            "Statut paiement : "
                                    + facture.getStatutPaiement()


                    )

            );


            // =========================
            // FIN DU DOCUMENT
            // =========================

            document.close();


            return outputStream.toByteArray();

        } catch (DocumentException e) {

            throw new RuntimeException(
                    "Erreur lors de la génération du PDF",
                    e
            );
        }
    }
}