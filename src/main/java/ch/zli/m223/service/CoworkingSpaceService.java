package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.CoworkingSpace;

@ApplicationScoped
public class CoworkingSpaceService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public CoworkingSpace createCoworkingSpace(CoworkingSpace coworkingSpace) {
        entityManager.persist(coworkingSpace);
        return coworkingSpace;
    }

    @Transactional
    public void deleteFriend(Long coworkingSpaceId) {
        CoworkingSpace coworkingSpaceToDelete = entityManager.find(CoworkingSpace.class, coworkingSpaceId);
        if (coworkingSpaceToDelete != null) {
            entityManager.remove(coworkingSpaceToDelete);
        }
    }

    @Transactional
    public CoworkingSpace updateCoworkingSpace(Long coworkingSpaceId, CoworkingSpace updatedCoworkingSpace) {
        CoworkingSpace existingCoworkingSPace = entityManager.find(CoworkingSpace.class, coworkingSpaceId);
        if (existingCoworkingSPace != null) {
            existingCoworkingSPace.setAvailability(updatedCoworkingSpace.getAvailability());
            existingCoworkingSPace.setFavorite(updatedCoworkingSpace.getFavorite());
            existingCoworkingSPace.setName(updatedCoworkingSpace.getName());
            existingCoworkingSPace.setStreet(updatedCoworkingSpace.getStreet());
            return existingCoworkingSPace;
        }
        return updatedCoworkingSpace;
    }
}
