package ruslan.kovshar.mmdb.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<UserRole> roles;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<MovieNote> movieNotes;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<MovieWatchList> movieWatchLists;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<MovieNote> getMovieNotes() {
        return movieNotes;
    }

    public void setMovieNotes(List<MovieNote> movieNotes) {
        this.movieNotes = movieNotes;
    }

    public List<MovieWatchList> getMovieWatchLists() {
        return movieWatchLists;
    }

    public void setMovieWatchLists(List<MovieWatchList> movieWatchLists) {
        this.movieWatchLists = movieWatchLists;
    }
}
