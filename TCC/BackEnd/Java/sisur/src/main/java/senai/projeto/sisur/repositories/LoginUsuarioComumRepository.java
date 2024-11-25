package senai.projeto.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senai.projeto.sisur.entities.loginUsuarios.comum.LoginUsuarioComum;
import senai.projeto.sisur.entities.usuarios.comum.UsuarioComum;

import java.util.Optional;

@Repository
public interface LoginUsuarioComumRepository extends JpaRepository<LoginUsuarioComum, Long> {

    @Query("SELECT luc.usuario FROM LoginUsuarioComum luc WHERE luc.email = :email")
    Optional<UsuarioComum> findUsuarioByEmail(@Param("id") String email);

    Optional<LoginUsuarioComum> findByEmail(String email);

}
