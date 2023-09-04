package ch.zli.m223.service;

import java.util.Optional;

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
        CoworkingSpace existingCoworkingSpace = entityManager.find(CoworkingSpace.class, coworkingSpaceId);
        if (existingCoworkingSpace != null) {
            existingCoworkingSpace.setAvailability(updatedCoworkingSpace.getAvailability());
            existingCoworkingSpace.setFavorite(updatedCoworkingSpace.getFavorite());
            existingCoworkingSpace.setName(updatedCoworkingSpace.getName());
            existingCoworkingSpace.setStreet(updatedCoworkingSpace.getStreet());
            return existingCoworkingSpace;
        }
        return updatedCoworkingSpace;
    }

    @Transactional
    public CoworkingSpace addToFavorite(Long coworkingSpaceId, CoworkingSpace updatedCoworkingSpace) {
        CoworkingSpace existingCoworkingSpace = entityManager.find(CoworkingSpace.class, coworkingSpaceId);
        if (existingCoworkingSpace != null) {
            existingCoworkingSpace.setFavorite(updatedCoworkingSpace.getFavorite());
            return existingCoworkingSpace;
        }
        return updatedCoworkingSpace;
    }

    @Transactional
    public Optional<CoworkingSpace> findByEmail(String email) {
        return entityManager
                .createNamedQuery("CoworkingSpace.findByEmail", CoworkingSpace.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
