package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.CoworkingSpace;
import ch.zli.m223.model.Friends;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void generateTestData(@Observes StartupEvent event) {
        // AppUser
        var appUserA = new AppUser();
        appUserA.setEmail("admin@gmail.com");
        appUserA.setLastname("Gwerder");
        appUserA.setPassword("1234");
        appUserA.setUsername("admin");
        appUserA.setIsAdmin(true);

        entityManager.persist(appUserA);

        var appUserB = new AppUser();
        appUserB.setEmail("user@gamil.com");
        appUserB.setLastname("Schmidt");
        appUserB.setPassword("4321");
        appUserB.setUsername("user");
        appUserB.setIsAdmin(false);

        entityManager.persist(appUserB);

        // Booking
        var bookingA = new Booking();
        bookingA.setStatus("Verfügbar");
        bookingA.setType("HalbTag");
        bookingA.setDate(null);

        entityManager.persist(bookingA);

        var bookingB = new Booking();
        bookingB.setStatus("Nicht Verfügbar");
        bookingB.setType("GanzerTag");
        bookingB.setDate(null);

        entityManager.persist(bookingB);

        // CoworkingSpace
        var coworkingSpaceA = new CoworkingSpace();
        coworkingSpaceA.setAvailability(true);
        coworkingSpaceA.setFavorite(true);
        coworkingSpaceA.setName("CoworkingSpaceGreen");
        coworkingSpaceA.setStreet("Zurcherstrasse");

        entityManager.persist(coworkingSpaceA);

        var coworkingSpaceB = new CoworkingSpace();
        coworkingSpaceB.setAvailability(false);
        coworkingSpaceB.setFavorite(false);
        coworkingSpaceB.setName("CoworkingSpaceBlue");
        coworkingSpaceB.setStreet("Winterthurstrasse");

        entityManager.persist(coworkingSpaceB);

        // Friend
        var friendA = new Friends();

        friendA.setFriend(appUserA);
        friendA.setUser(appUserB);

        entityManager.persist(friendA);
    }
}
