package senai.projetofinal.sisur.dtos.comentario;

import senai.projetofinal.sisur.entities.Comentario;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.entities.Usuario;

import java.time.Instant;

public record ComentarioResponse(
        Long id,
        String comentario,
        Instant data,
        Ocorrencia ocorrencia,
        Usuario usuario
) {

    public ComentarioResponse (Comentario comentario) {
        this(
                comentario.getId(),
                comentario.getComentario(),
                comentario.getData(),
                comentario.getOcorrencia(),
                comentario.getUsuario()
        );
    }
}
