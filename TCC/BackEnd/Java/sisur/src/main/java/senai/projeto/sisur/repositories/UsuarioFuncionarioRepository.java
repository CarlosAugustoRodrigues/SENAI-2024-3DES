package senai.projeto.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.sisur.entities.usuarios.funcionario.UsuarioFuncionario;

@Repository
public interface UsuarioFuncionarioRepository extends JpaRepository<UsuarioFuncionario, Long> {
}
