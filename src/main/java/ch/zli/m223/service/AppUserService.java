package ch.zli.m223.service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.AppUser;
import io.smallrye.jwt.build.Jwt;

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

    @Transactional
    public List<AppUser> findAll() {
        var query = entityManager.createQuery("FROM AppUser", AppUser.class);
        return query.getResultList();
    }

    @Transactional
    public Optional<AppUser> findyByEmail(String email) {
        return entityManager
                .createNamedQuery("AppUser.findByEmail", AppUser.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    private String createJwtToken(String email, Long userId, Set<String> groups) {

        return Jwt.upn(email)
                .groups(groups)
                .claim("user_id", userId)
                .expiresIn(Duration.ofHours(24))
                .sign();
    }

    @Transactional
    public String login(String email, String password) {
        var query = entityManager.createQuery(
                "SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class);

        query.setParameter("email", email);
        AppUser user = query.getSingleResult();

        if (user.getPassword().equals(password)) {
            Set<String> groups = new HashSet<>();
            if (user.isAdmin()) {
                groups.add("admin");
            } else {
                groups.add("user");
            }
            return createJwtToken(email, user.getId(), groups);
        }
        return null;
    }

}
