package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplMock {

    @Mock
    ProduitRepository produitRepository;

    CategorieProduit categorieProduit = new CategorieProduit("CAT001", "Catégorie de test");
    Stock stock = new Stock("Stock de test", 100, 10);

    Produit produit = new Produit(1L, "ABC123", "Produit 1 de test", 50, new Date(), new Date(), categorieProduit, stock);

    List<Produit> listProduits = new ArrayList<Produit>() {{
        add(new Produit(2L,"ABC123", "Produit 1 de test", 50, new Date(), new Date(), categorieProduit, stock));
        add(new Produit(3L,"DEF456", "Produit 2 de test", 60, new Date(), new Date(), categorieProduit, stock));
    }};

    @InjectMocks
    ProduitServiceImpl produitService;

    @Test
    public void testRetreiveProduit(){
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        Produit produit1 = produitService.retrieveProduit(1L);
        Assertions.assertNotNull(produit1);
    }
    @Test
    public void testRetrieveAllProduits() {
        // Mock the behavior of produitRepository.findAll to return the expectedProduits list
        Mockito.when(produitRepository.findAll()).thenReturn(listProduits);

        // Call the retrieveAllProduits method
        List<Produit> retrievedProduits = produitService.retrieveAllProduits();

        // Assert that the returned list is not null and matches the expectedProduits list
        Assertions.assertNotNull(retrievedProduits);
        Assertions.assertEquals(listProduits, retrievedProduits);
    }

    @Test
    public void testAddProduit() {


        // Mock the behavior of produitRepository.save to return the same produit
        Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(produit);

        // Call the addProduit method
        Produit addedProduit = produitService.addProduit(produit);

        // Assert that the returned Produit is not null and matches the input Produit
        Assertions.assertNotNull(addedProduit);
        Assertions.assertEquals(produit, addedProduit);
    }
    @Test
    public void testUpdateProduit() {


        // Mock the behavior of produitRepository.save to return the same updated Produit
        Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(produit);

        // Call the updateProduit method
        Produit updatedProduit = produitService.updateProduit(produit);

        // Assert that the returned updated Produit is not null and matches the input Produit
        Assertions.assertNotNull(updatedProduit);
        Assertions.assertEquals(produit, updatedProduit);
    }
    @Test
    public void testDeleteProduit() {
        // Define the produitId to be deleted
        Long produitId = 1L; // Replace with the actual ID you want to delete

        // Call the deleteProduit method
        produitService.deleteProduit(produitId);

        // Verify that produitRepository.deleteById was called with the specified produitId
        Mockito.verify(produitRepository).deleteById(produitId);
    }
}



