package ch.zli.m223.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Friends;

@ApplicationScoped
public class FriendsService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private AppUserService userService;

    @Transactional
    public Friends createFriend(Long userId ,Long friendId) {
        Optional<AppUser> user = userService.findUserById(userId);
        Optional<AppUser> friend = userService.findUserById(friendId);

    if (user.isPresent() && friend.isPresent()) {
        Friends friends = new Friends();
        friends.setUser(user.get());
        friends.setFriend(friend.get());
        entityManager.persist(friends);
        return friends;
    }else{
        return null;
    }
    }

    @Transactional
    public void deleteFriend(Long friendId) {
        Friends friendToDelete = entityManager.find(Friends.class, friendId);
        if (friendToDelete != null) {
            entityManager.remove(friendToDelete);
        }
    }

    @Transactional
    public Optional<Friends> findFriendByUsername(String username) {
        return entityManager
        .createNamedQuery("Friends.findByUsername", Friends.class)
        .setParameter("username", username)
        .getResultStream()
        .findFirst();
    }
}
