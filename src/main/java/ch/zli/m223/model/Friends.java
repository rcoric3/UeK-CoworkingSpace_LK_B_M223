package ch.zli.m223.model;

import javax.persistence.*;

@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user;
    @ManyToOne
    private AppUser friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppUser getFriend() {
        return friend;
    }

    public void setFriend(AppUser friend) {
        this.friend = friend;
    }

}
