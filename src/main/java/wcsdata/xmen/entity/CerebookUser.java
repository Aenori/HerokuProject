package wcsdata.xmen.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class CerebookUser implements Comparable<CerebookUser> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    private String name;
    private String humanName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="cerebook_user_friends")
    private final Set<CerebookUser> friends = new TreeSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private final Set<CerebookPost> posts = new HashSet<>();

    // <editor-fold desc="constructors region">
    public CerebookUser() {}

    public CerebookUser(String username, String password, String name, String humanName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.humanName = humanName;
    }

    public CerebookUser(Integer id, String username, String password, String name, String humanName) {
        this(username, password, name, humanName);
        this.id = id;
    }
    // </editor-fold>

    // <editor-fold desc="getter-setter region">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHumanName() {
        return humanName;
    }

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public Set<CerebookUser> getFriends() {
        return friends;
    }

    public Set<CerebookPost> getPosts() {
        return posts;
    }
    // </editor-fold>

    public void addFriend(CerebookUser friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    @Override
    public int compareTo(CerebookUser other) {
        return id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CerebookUser that = (CerebookUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CerebookUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", humanName='" + humanName + '\'' +
                '}';
    }

    public CerebookPost createPost() {
        CerebookPost post = new CerebookPost();
        post.setAuthor(this);
        posts.add(post);

        return post;
    }
}
