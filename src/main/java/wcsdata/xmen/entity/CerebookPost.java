package wcsdata.xmen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class CerebookPost {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String content;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private CerebookUser author;

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

    @Override
    public String toString() {
        return "CerebookPost{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author=" + (author == null ? "null" : author.getUsername()) +
                '}';
    }
}
