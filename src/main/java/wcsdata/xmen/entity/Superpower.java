package wcsdata.xmen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Superpower {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "superpowers")
    private final Set<CerebookUser> cerebookUsers = new TreeSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CerebookUser> getCerebookUsers() {
        return cerebookUsers;
    }
}
