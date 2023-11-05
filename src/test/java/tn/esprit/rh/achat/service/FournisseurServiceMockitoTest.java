package tn.esprit.rh.achat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class FournisseurServiceMockitoTest {
    @InjectMocks
    private FournisseurServiceImpl fournisseurService;
    @Mock
    private FournisseurRepository fournisseurRepository;
    @Test
    public void testAddFournisseur() {
        // Arrange
        Fournisseur ajoutFournisseur = new Fournisseur();
        Mockito.when(fournisseurRepository.save(ajoutFournisseur)).thenReturn(ajoutFournisseur);

        // Act
        Fournisseur result = fournisseurService.addFournisseur(ajoutFournisseur);

        // Assert
        Assertions.assertEquals(ajoutFournisseur, result);
    }
    @Test
    public void testRetrieveInstructor() {
        // Arrange
        Long idFournisseur = 1L;
        Fournisseur fournisseur = new Fournisseur();
        Mockito.when(fournisseurRepository.findById(idFournisseur)).thenReturn(Optional.of(fournisseur));

        // Act
        Fournisseur result = fournisseurService.retrieveFournisseur(idFournisseur);

        // Assert
        Assertions.assertEquals(fournisseur, result);
    }
    @Test
    public void testDeleteCategorieProduit() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        fournisseurService.deleteFournisseur(idToDelete);

        // Assert
        Mockito.verify(fournisseurRepository, Mockito.times(1)).deleteById(idToDelete);
    }

    @Test
    public void testUpdateCategorieProduit() {
        // Arrange
        Fournisseur fournisseurUpdated = new Fournisseur();

        // Act
        Fournisseur result = fournisseurService.updateFournisseur(fournisseurUpdated);

        // Assert
        Mockito.verify(fournisseurRepository, Mockito.times(1)).save(fournisseurUpdated);
        Assertions.assertEquals(fournisseurUpdated, result);
    }

}
