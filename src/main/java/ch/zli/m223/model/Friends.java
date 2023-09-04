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

}
