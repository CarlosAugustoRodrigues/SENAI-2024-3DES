package senai.projeto.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senai.projeto.sisur.entities.loginUsuarios.funcionario.LoginUsuarioFuncionario;
import senai.projeto.sisur.entities.usuarios.funcionario.UsuarioFuncionario;

import java.util.Optional;

@Repository
public interface LoginUsuarioFuncionarioRepository extends JpaRepository<LoginUsuarioFuncionario, Long> {

    @Query("SELECT luf.funcionario FROM LoginUsuarioFuncionario luf WHERE luf.email = :email")
    Optional<UsuarioFuncionario> findFuncionarioByEmail(@Param("id") String email);

    Optional<LoginUsuarioFuncionario> findByEmail(String email);
}
