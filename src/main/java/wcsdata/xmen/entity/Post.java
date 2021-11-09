package wcsdata.xmen.entity;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String content;

    @ManyToOne
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
}
