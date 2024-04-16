import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PrevisaoTempoTest {

	private static PrevisaoTempo previsaoTempo;

	@BeforeAll
	public static void setUp() throws IOException {
		
		previsaoTempo = new PrevisaoTempo();
		
	}
	
	@Test
	public void testPrevisaoPorCidade() throws JSONException, IOException {
		
		String cidade_teste = "São Paulo";
		String pais_teste = "BR";

		Map<String, Object> response = previsaoTempo.getForecastByCityName(cidade_teste);
		JSONObject body = new JSONObject(response.get("body").toString());

		String paisAPI = body.getJSONObject("city").getString("country");
    	String cidadeAPI = body.getJSONObject("city").getString("name");
    	
    	// assertAll agrupa os asserts e verifica todos do grupo, mesmo que algum deles falhe,
    	// que é diferente de simplesmente enfileirar asserts "normais" (que faria o código parar na primeira falha)
    	Assertions.assertAll("Verifica Cidade e País retornados pela API",
    		() -> assertEquals(cidade_teste, cidadeAPI),
    		() -> assertEquals(pais_teste, paisAPI)
    		);
	}

}