import java.io.IOException;
import java.util.Scanner;

public class ConversorDeMoedas {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 7) {
            Mensagem.mostrarMensagem();
            opcao = input.nextInt();
            if (opcao >= 1 && opcao < 7) {
                ConexaoExchangeRate conversor = new ConexaoExchangeRate(opcao);
                System.out.println("Digite o valor que deseja converter: ");
                double valor = input.nextDouble();
                double valorConvertido = conversor.conectarConverter(valor);
                System.out.printf("Valor %f [%s] corresponde ao valor final de =>>>> %f [%s]%n", valor, conversor.getBase(), valorConvertido, conversor.getTarget());
            } else if (opcao == 7) {
                System.out.println("Programa finalizado.");
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}