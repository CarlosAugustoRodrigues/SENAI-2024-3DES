package senai.atividade.fabricaautomoveis.dtos.automoveis;

public record AutomoveisRequest(
        String modelo,
        Float preco,
        String situacao,
        Long area
) {
}
