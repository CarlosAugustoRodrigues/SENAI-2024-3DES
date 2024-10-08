package senai.atividade.fabricaautomoveis.dtos.automoveis;

import senai.atividade.fabricaautomoveis.entidades.Area;
import senai.atividade.fabricaautomoveis.entidades.Automoveis;

public record AutomoveisResponse(
        Long id,
        String modelo,
        Float preco,
        String situacao,
        Area area
) {
    public AutomoveisResponse(Automoveis automoveis) {
        this(
                automoveis.getId(),
                automoveis.getModelo(),
                automoveis.getPreco(),
                automoveis.getSituacao(),
                automoveis.getArea()
        );
    }
}
