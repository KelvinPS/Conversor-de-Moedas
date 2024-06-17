import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Conexão e rebimento de cotações==========
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/b26ee7ad5ff366252f7d71f7/latest/USD"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        //=========================================
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        String json = response.body();
        //menu--------------------------------------
        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal;

        String menu = """
                ___________________Menu___________________
                1) Dólar (USD) para Real Br (BRL)
                2) Euro (EUR) para Real Br (BRL)
                3) Peso Argentino (ARS) para Real Br (BRL)
                4) Real Br (BRL) para Dólar (USD)
                5) Real Br (BRL) para Euro (EUR)
                6) Real Br (BRL) para Peso Argentino (ARS)
                
                7) Sair
                __________________________________________
                Escolha uma opção:
                """;
        System.out.println(menu);
        opcaoPrincipal = scanner.nextInt();
        //------------------------------------------
        //switch de escolhas========================
        double valorConversao;

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        double brlValue = jsonObject.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();

        switch (opcaoPrincipal) {
            case 1:
                System.out.println("Quantos Dolares você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject1 = JsonParser.parseString(json).getAsJsonObject();
                double brlValue2 = jsonObject1.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();

                double conversao = valorConversao * brlValue2;
                System.out.println("A conversão resultou em: R$" + new DecimalFormat("#,##0.00").format(conversao) );

                break;
            case 2:
                System.out.println("Quantos Euros você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject2 = JsonParser.parseString(json).getAsJsonObject();
                double eurValue = jsonObject2.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();

                double intermediario1 = valorConversao / eurValue;
                double conversao3 = intermediario1 * brlValue;

                System.out.println("A conversão resultou em: R$" + new DecimalFormat("#,##0.00").format(conversao3) );

                break;

            case 3:
                System.out.println("Quantos Pesos Argentinos você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject3 = JsonParser.parseString(json).getAsJsonObject();
                double arsValue = jsonObject3.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();

                double intermediario2 = valorConversao / arsValue;
                double conversao2 = intermediario2 * brlValue;

                System.out.println("A conversão resultou em: R$" + new DecimalFormat("#,##0.00").format(conversao2) );

                break;
            case 4:
                System.out.println("Quantos Reais você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject4 = JsonParser.parseString(json).getAsJsonObject();
                double brlValue3 = jsonObject4.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();

                double conversao4 = valorConversao / brlValue3;

                System.out.println("A conversão resultou em: " + new DecimalFormat("#,##0.00").format(conversao4) + " Dolares" );

                break;
            case 5:
                System.out.println("Quantos Reais você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject5 = JsonParser.parseString(json).getAsJsonObject();
                double eurValue2 = jsonObject5.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();

                double intermediario3 = valorConversao / brlValue;
                double conversao5 = intermediario3 / eurValue2;

                System.out.println("A conversão resultou em: " + new DecimalFormat("#,##0.00").format(conversao5) + " Euros" );

                break;
            case 6:
                System.out.println("Quantos Reais você deseja converter?");
                valorConversao = scanner.nextDouble();
                JsonObject jsonObject6 = JsonParser.parseString(json).getAsJsonObject();
                double arsValue2 = jsonObject6.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();

                double intermediario4 = valorConversao / brlValue;
                double conversao6 = intermediario4 * arsValue2;

                System.out.println("A conversão resultou em: " + new DecimalFormat("#,##0.00").format(conversao6) + " Pesos Argentinos" );

                break;

            case 7:
                System.out.println("Encerrando...");
                break;
        }
        //==========================================
        System.out.println("O Programa encerrou corretamente!");
    }
}