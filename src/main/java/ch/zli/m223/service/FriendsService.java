package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Friends;

@ApplicationScoped
public class FriendsService {

    @Inject
    private EntityManager entityManager;

    
    @Transactional
    public Friends createFriend(Friends friends) {
        entityManager.persist(friends);
        return friends;
    }

    @Transactional
    public void deleteFriend(Long friendId) {
        Friends friendToDelete = entityManager.find(Friends.class, friendId);
        if (friendToDelete != null) {
            entityManager.remove(friendToDelete);
        }
    }
}
