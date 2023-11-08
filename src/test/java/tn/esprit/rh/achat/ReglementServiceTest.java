package tn.esprit.rh.achat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ReglementServiceTest{
    @InjectMocks
    private ReglementServiceImpl reglementService;
    @Mock
    private ReglementRepository reglementRepository;
    @Test
    public void testRetrieveAllReglements() {
        Reglement reglement1 = new Reglement();
        reglement1.setIdReglement(1L);
        reglement1.setMontantPaye(100.0f);
        reglement1.setMontantRestant(200.0f);
        reglement1.setPayee(true);
        reglement1.setDateReglement(new Date());
        Reglement reglement2 = new Reglement();
        reglement2.setIdReglement(2L);
        reglement2.setMontantPaye(150.0f);
        reglement2.setMontantRestant(250.0f);
        reglement2.setPayee(true);
        reglement2.setDateReglement(new Date());
        List<Reglement> reglementList = new ArrayList<>();
        reglementList.add(reglement1);
        reglementList.add(reglement2);
        when(reglementRepository.findAll()).thenReturn(reglementList);
        List<Reglement> retrievedReglements = reglementService.retrieveAllReglements();
        assertEquals(2, retrievedReglements.size());

    }

    @Test
    public void testAddReglement() {
        Reglement reglementToSave = new Reglement();
        reglementToSave.setIdReglement(1L);
        reglementToSave.setMontantPaye(100.0f);
        reglementToSave.setMontantRestant(200.0f);
        reglementToSave.setPayee(true);
        reglementToSave.setDateReglement(new Date());
        when(reglementRepository.save(Mockito.any(Reglement.class))).thenReturn(reglementToSave);
        Reglement addedReglement = reglementService.addReglement(reglementToSave);
        assertEquals(1L, addedReglement.getIdReglement());
    }

    @Test
    public void testRetrieveReglement() {
        Reglement reglement = new Reglement();
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(100.0f);
        reglement.setMontantRestant(200.0f);
        reglement.setPayee(true);
        reglement.setDateReglement(new Date());
        Mockito.when(reglementRepository.findById(1L)).thenReturn(Optional.of(reglement));
        Reglement retrievedReglement = reglementService.retrieveReglement(1L);
        assertNotNull(retrievedReglement);
        assertEquals(1L, retrievedReglement.getIdReglement());
    }


    @Test
    public void testRetrieveReglementByFacture() {
        Long idFacture = 1L;
        Reglement reglement1 = new Reglement();
        reglement1.setIdReglement(1L);
        reglement1.setMontantPaye(100.0f);
        reglement1.setMontantRestant(200.0f);
        reglement1.setPayee(true);
        reglement1.setDateReglement(new Date());
        Reglement reglement2 = new Reglement();
        reglement2.setIdReglement(2L);
        reglement2.setMontantPaye(150.0f);
        reglement2.setMontantRestant(250.0f);
        reglement2.setPayee(true);
        reglement2.setDateReglement(new Date());
        List<Reglement> reglementList = List.of(reglement1, reglement2);
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(reglementList);
        List<Reglement> retrievedReglements = reglementService.retrieveReglementByFacture(idFacture);
        assertEquals(2, retrievedReglements.size());
    }

    @Test
    public void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedChiffreAffaire = 1000.0f;
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(expectedChiffreAffaire);
        float chiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        assertEquals(expectedChiffreAffaire, chiffreAffaire);
    }

    @Test
    @Order(1)
    public void testRetrieveAllReglementsJU() {
        List<Reglement> listReglements = reglementService.retrieveAllReglements();
        Assertions.assertEquals(1, listReglements.size());
    }

    @Test
    @Order(2)
    public void testAddReglementJU() {
        Reglement reglement = new Reglement();
        reglement.setMontantPaye(100.0f);
        reglement.setMontantRestant(200.0f);
        reglement.setPayee(true);
        reglement.setDateReglement(new Date());
        Reglement addedReglement = reglementService.addReglement(reglement);
        Assertions.assertNotNull(addedReglement.getIdReglement());
    }

    @Test
    @Order(3)
    public void testRetrieveReglementJU() {
        Long reglementId = 1L;
        Reglement retrievedReglement = reglementService.retrieveReglement(reglementId);
        Assertions.assertNull(retrievedReglement);
    }

    @Test
    @Order(4)
    public void testRetrieveReglementByFactureJU() {

        Long factureId = 1L;
        List<Reglement> reglements = reglementService.retrieveReglementByFacture(factureId);
        Assertions.assertNotNull(reglements);

    }

    @Test
    @Order(5)
    public void testGetChiffreAffaireEntreDeuxDateJU() {
        Date startDate = java.sql.Date.valueOf("2023-01-01");
        Date endDate = java.sql.Date.valueOf("2024-01-01");
        float chiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        Assertions.assertNotNull(chiffreAffaire);
    }


}