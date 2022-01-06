package wcsdata.xmen.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CerebookUser author;

    @ManyToMany
    private final Set<CerebookUser> recipient = new TreeSet<CerebookUser>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CerebookUser getAuthor() {
        return author;
    }

    public void setAuthor(CerebookUser author) {
        this.author = author;
    }

    public Set<CerebookUser> getRecipient() {
        return recipient;
    }
}
