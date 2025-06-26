package groffe.testes.consumerapis;

import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.opentelemetry.OpenTelemetryTracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.trace.Span;

@Component
public class TimerRoute extends RouteBuilder {

	@Value("${wait.time}")
	private int waitTime;
	
	@Value("${url.apicontagem}")
	private String urlApiContagem;
	
	@Value("${url.apisaudacoes}")
	private String urlApiSaudacoes;
	
	@Value("${url.apiconsumocontagem}")
	private String urlApiConsumoContagem;
	
    @Override
    public void configure() throws IOException {
    	OpenTelemetryTracer ott = new OpenTelemetryTracer();
    	ott.init(this.getContext());
    	
    	from("timer:print?period=" + waitTime)
	        .routeId("print-message-route")
	        .process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	            	
                	System.out.println();
                	System.out.println();
                	RestTemplate restTemplate = new RestTemplate();
                	String responseApiContagem = null;
                    try {
                        responseApiContagem = restTemplate.getForObject(urlApiContagem, String.class);
                        System.out.println("Resposta do servidor - APIContagem: " + responseApiContagem);
                    } catch (Exception e) {
                        System.err.println("Erro ao enviar requisição - APIContagem: " + e.getMessage());
                    }

                	System.out.println();
                	System.out.println();
                	RestTemplate restTemplate2 = new RestTemplate();
                	String responseApiSaudacoes = null;
                    try {
                        responseApiSaudacoes = restTemplate2.getForObject(urlApiSaudacoes, String.class);
                        System.out.println("Resposta do servidor - APISaudacoes: " + responseApiSaudacoes);
                    } catch (Exception e) {
                        System.err.println("Erro ao enviar requisição - APISaudacoes: " + e.getMessage());
                    }

                	System.out.println();
                	System.out.println();
                	RestTemplate restTemplate3 = new RestTemplate();
                	String responseApiConsumoContagem = null;
                    try {
                        responseApiConsumoContagem = restTemplate3.getForObject(urlApiConsumoContagem, String.class);
                        System.out.println("Resposta do servidor - ApiConsumoContagem: " + responseApiConsumoContagem);
                    } catch (Exception e) {
                        System.err.println("Erro ao enviar requisição - ApiConsumoContagem: " + e.getMessage());
                    }

                	System.out.println();
                	System.out.println();
                    System.out.println("Aguardando 50 milissegundos...");
	            	Span spanTempoEspera = ott.getTracer().spanBuilder("TempoEspera").startSpan();                	
	            	spanTempoEspera.setAttribute("tempo_inicio", System.currentTimeMillis());
	            	spanTempoEspera.setAttribute("urlApiContagem", urlApiContagem);
	            	spanTempoEspera.setAttribute("responseApiContagem", responseApiContagem);
	            	spanTempoEspera.setAttribute("urlApiSaudacoes", urlApiSaudacoes);
	            	spanTempoEspera.setAttribute("responseApiSaudacoes", responseApiSaudacoes);
	            	spanTempoEspera.setAttribute("urlApiConsumoContagem", urlApiConsumoContagem);
					spanTempoEspera.setAttribute("responseApiConsumoContagem", responseApiConsumoContagem);
	            	Thread.sleep(50);
	            	spanTempoEspera.setAttribute("tempo_termino", System.currentTimeMillis());
	            	spanTempoEspera.end();
                    System.out.println("Encerrada a espera de 50 milissegundos!");

                	System.out.println();
                	System.out.println();
	            }
	         })
	        .log("Envio de requisicoes para APIs concluido!");

    	ott.close();
    }
}