import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoExchangeRate {
    private String base, target;

    public ConexaoExchangeRate(int opcao) {
        switch (opcao) {
            case 1:
                base = "USD";
                target = "ARS";
                break;
            case 2:
                base = "ARS";
                target = "USD";
                break;
            case 3:
                base = "USD";
                target = "BRL";
                break;
            case 4:
                base = "BRL";
                target = "USD";
                break;
            case 5:
                base = "USD";
                target = "COP";
                break;
            case 6:
                base = "COP";
                target = "USD";
        }
    }

    public double conectarConverter(double valor) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/688158672e8f7bcf105c62da/pair/%s/%s/%f".formatted(base, target, valor).replace(",", ".")))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        MoedaConvertida moedaConvertida = gson.fromJson(response.body(), MoedaConvertida.class);

        return moedaConvertida.conversion_result();
    }

    public String getBase() {
        return base;
    }
    public String getTarget() {
        return target;
    }
}