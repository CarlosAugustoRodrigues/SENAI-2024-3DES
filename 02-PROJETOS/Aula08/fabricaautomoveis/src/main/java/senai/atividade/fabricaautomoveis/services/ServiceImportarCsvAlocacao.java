package senai.atividade.fabricaautomoveis.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import senai.atividade.fabricaautomoveis.entities.Alocacao;
import senai.atividade.fabricaautomoveis.repositories.AlocacaoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImportarCsvAlocacao {

    private final AlocacaoRepository alocacaoRepository;

    public ServiceImportarCsvAlocacao(AlocacaoRepository alocacaoRepository) {
        this.alocacaoRepository = alocacaoRepository;
    }

    @Value("${alocacao.csv}")
    private String caminhoArquivo;

    @PostConstruct
    public void importarCsvOnStartup() throws IOException, CsvException {
        importarCsv(caminhoArquivo);
    }

    @Transactional
    public void importarCsv(String caminhoArquivo) throws IOException, CsvException {
        StringReader reader = new StringReader(caminhoArquivo);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<String[]> alocacoes = csvReader.readAll();
        List<Alocacao> objAlocacoes = new ArrayList<>();
        Integer sizeAlocaoes = 20;
        
        for (String[] alocacao: alocacoes) {
            
            while (sizeAlocaoes <= 20) {
                var newAlocacao = new Alocacao();
                newAlocacao.setId(Long.parseLong(alocacao[0]));
                newAlocacao.setNumArea(Long.parseLong(alocacao[1]));
                newAlocacao.setAutomovel(Long.parseLong(alocacao[2]));
                newAlocacao.setConcessionaria(Long.parseLong(alocacao[3]));
                newAlocacao.setQtdAutomovel(Long.parseLong(alocacao[4]));
                
                objAlocacoes.add(newAlocacao);
            }
            
        }
    }
}
