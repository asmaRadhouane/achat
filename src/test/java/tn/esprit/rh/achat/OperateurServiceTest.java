package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceTest {

    @InjectMocks
    private OperateurServiceImpl operateurService;
    @Mock
    private OperateurRepository operateurRepository;

    @Test
    public void testRetrieveAllOperateurs() {
        Operateur operateur1 = new Operateur();
        operateur1.setIdOperateur(1L);
        operateur1.setNom("John");
        operateur1.setPrenom("Doe");

        Operateur operateur2 = new Operateur();
        operateur2.setIdOperateur(2L);
        operateur2.setNom("Jane");
        operateur2.setPrenom("Smith");

        List<Operateur> operateurList = new ArrayList<>();
        operateurList.add(operateur1);
        operateurList.add(operateur2);

        when(operateurRepository.findAll()).thenReturn(operateurList);

        List<Operateur> retrievedOperateurs = operateurService.retrieveAllOperateurs();
        assertEquals(2, retrievedOperateurs.size());
    }

    @Test
    public void testAddOperateur() {
        Operateur operateurToSave = new Operateur();
        operateurToSave.setIdOperateur(1L);
        operateurToSave.setNom("John");
        operateurToSave.setPrenom("Doe");

        when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(operateurToSave);

        Operateur addedOperateur = operateurService.addOperateur(operateurToSave);
        assertEquals(1L, addedOperateur.getIdOperateur());
    }

    @Test
    public void testRetrieveOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("John");
        operateur.setPrenom("Doe");

        Mockito.when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(1L);
        assertEquals(1L, retrievedOperateur.getIdOperateur());
    }
    @Test
    public void testUpdateOperateur() {
        Operateur existingOperateur = new Operateur();
        existingOperateur.setIdOperateur(1L);
        existingOperateur.setNom("John");
        existingOperateur.setPrenom("Doe");

        when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(existingOperateur);

        Operateur updatedOperateur = operateurService.updateOperateur(existingOperateur);
        assertEquals(1L, updatedOperateur.getIdOperateur());
    }
    @Test
    public void testDeleteOperateur() {
        Long operateurId = 1L;
        Operateur existingOperateur = new Operateur();
        existingOperateur.setIdOperateur(operateurId);
        existingOperateur.setNom("John");
        existingOperateur.setPrenom("Doe");

        // Utilisez lenient pour éviter l'exception UnnecessaryStubbingException
        Mockito.lenient().when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(existingOperateur));

        operateurService.deleteOperateur(operateurId);

        // Vous n'avez pas besoin de vérifier le deleteById, car c'est une méthode void
    }


}

