package ru.careportal.core.db.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.careportal.core.db.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotNull
    @Size(min=2, message="Имя должно быть больше 2 символов")
    @NonNull private String username;
    @NotNull
    @Size(min=2, message="Пароль должно быть больше 2 символов")
    @NonNull private String password;
    @NotNull
    private Role role;
    private Date created;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Pass> passList = new ArrayList<>();

    @PrePersist
    private void setCreated() {
        this.created = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
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
