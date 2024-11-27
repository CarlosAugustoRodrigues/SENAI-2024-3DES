package senai.projetofinal.sisur.dtos.mensagem;

public record MensagemRequest(
        String mensagem,
        Long ocorrencia,
        String perfil,
        Long responsavel
) {
}
