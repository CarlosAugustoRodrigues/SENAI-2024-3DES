package senai.projetofinal.sisur.dtos.comentario;

public record ComentarioRequest(
        String comentario,
        Long ocorrencia,
        Long usuario
) {
}
