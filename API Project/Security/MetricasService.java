import java.util.Date;

public class MetricasService {

    public MetricasUsuario obterMetricasUsuario(String idUsuario) {
       
        return new MetricasUsuario();
    }

    public MetricasAplicacao obterMetricasAplicacao() {

        return new MetricasAplicacao();
    }

    public void atualizarMetricasUsuario(String idUsuario, MetricasUsuario metricas) {
        
    }

    public Relatorio gerarRelatorio(Date dataInicio, Date dataFim) {
      
        return new Relatorio();
    }
}