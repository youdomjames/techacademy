package ca.techacademy.users.model;

import ca.techacademy.users.util.enums.Role;
import ca.techacademy.users.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
    @Id
    @Column(updatable = false)
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

    public void setProfile(Profile profile) {
        this.profile = profile;
        this.profile.setUser(this);
        this.setStatus(profile.getStatus());
    }

    public void setStatus(Status status) {
        this.status = status;
        this.profile.setStatus(status);
    }
}
