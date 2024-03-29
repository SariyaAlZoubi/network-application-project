package com.networkapplication.models;

import com.networkapplication.services.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "user_app",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "username_unique",
                        columnNames = "username"
                )
        },
        indexes = {
                @Index(name = "idx_user_name", columnList = "username")
        }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1)
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String username;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;


    @OneToMany(mappedBy = "admin")
    private List<Group> userGroups;


    @ManyToMany(
            mappedBy = "members"
    )
    private List<Group> groups;

    @OneToMany(mappedBy = "ownerFile")
    private List<File> files;

    @OneToMany(mappedBy = "checkin")
    private List<File> myFiles;

    @OneToMany(mappedBy = "user")
    private List<Auditing> logs;
    @Column(
            name = "fault_count"
    )
    private Integer faultCount;
    @Column(
            name = "Role"
    )
    private Utils.role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
