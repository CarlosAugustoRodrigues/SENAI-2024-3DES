package senai.atividade.fabricaautomoveis.dtos.area;

import senai.atividade.fabricaautomoveis.entidades.Area;
import senai.atividade.fabricaautomoveis.entidades.Automoveis;

import java.util.Set;

public record AreaResponse(
        Long id,
        String nome,
        Long numeroArea,
        Set<Automoveis> automoveis
) {
    public AreaResponse (Area area){
        this(
                area.getId(),
                area.getNome(),
                area.getNumeroArea(),
                area.getAutomoveis()
        );
    }
}
