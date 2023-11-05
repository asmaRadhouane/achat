package tn.esprit.rh.Service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;


import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@Slf4j
public class CategorieProduitServiceMockitoTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;
    @Test
    public void testAddCategorieProduit() {
        // Arrange
        CategorieProduit categorieProduit = new CategorieProduit();
        Mockito.when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);

        // Act
        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);

        // Assert
        Assertions.assertEquals(categorieProduit, result);
    }
    @Test
    public void testRetrieveInstructor() {
        // Arrange
        Long idCategorieProduit = 1L;
        CategorieProduit categorieProduit = new CategorieProduit();
        Mockito.when(categorieProduitRepository.findById(idCategorieProduit)).thenReturn(Optional.of(categorieProduit));

        // Act
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(idCategorieProduit);

        // Assert
        Assertions.assertEquals(categorieProduit, result);
    }
    @Test
    public void testDeleteCategorieProduit() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        categorieProduitService.deleteCategorieProduit(idToDelete);

        // Assert
        Mockito.verify(categorieProduitRepository, Mockito.times(1)).deleteById(idToDelete);
    }

    @Test
    public void testUpdateCategorieProduit() {
        // Arrange
        CategorieProduit updatedCategorieProduit = new CategorieProduit();

        // Act
        CategorieProduit result = categorieProduitService.updateCategorieProduit(updatedCategorieProduit);

        // Assert
        Mockito.verify(categorieProduitRepository, Mockito.times(1)).save(updatedCategorieProduit);
        Assertions.assertEquals(updatedCategorieProduit, result);
    }

}
