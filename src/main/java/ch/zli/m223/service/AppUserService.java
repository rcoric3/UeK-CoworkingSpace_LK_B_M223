package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.AppUser;

@ApplicationScoped
public class AppUserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public AppUser createUser(AppUser user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public Optional<AppUser> findUserById(Long userId) {
        AppUser user = entityManager.find(AppUser.class, userId);
        return Optional.ofNullable(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        AppUser userToDelete = entityManager.find(AppUser.class, userId);
        if (userToDelete != null) {
            entityManager.remove(userToDelete);
        }
    }

    @Transactional
    public AppUser updateUser(Long userId, AppUser user) {
        AppUser existingAppUser = entityManager.find(AppUser.class, userId);
        if (existingAppUser != null) {
            existingAppUser.setEmail((user.getEmail()));
            existingAppUser.setIsAdmin((user.getIsAdmin()));
            existingAppUser.setLastname(user.getLastname());
            existingAppUser.setPassword(user.getPassword());
            existingAppUser.setUsername(user.getUsername());
            return existingAppUser;
        }
        return user;
    }

    public List<AppUser> findAll() {
        var query = entityManager.createQuery("FROM AppUser", AppUser.class);
        return query.getResultList();
    }
}