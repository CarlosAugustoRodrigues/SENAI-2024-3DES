package senai.projeto.sisur.entities.loginUsuarios.comum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import senai.projeto.sisur.entities.usuarios.comum.UsuarioComum;
import senai.projeto.sisur.enums.Role;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_login_usuario_comum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class LoginUsuarioComum implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    private UsuarioComum usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getRole()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
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
