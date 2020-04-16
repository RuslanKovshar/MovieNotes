package ruslan.kovshar.mmdb.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
