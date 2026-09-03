import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            exibirCabecalho();   // <== METODO COMPARTILHADO (ponto de conflito)
            exibirMenu();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1: /* funcionalidade do Desenvolvedor A */ break;
                case 2: ExibirPagamento(); break;
                case 0: System.out.println("Encerrando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        entrada.close();
    }

    public static void exibirCabecalho() {
        System.out.println("=====================");
        System.out.println("Caixa de Lanchonete");
        System.out.println("=====================");
    }

    public static void exibirMenu() {
        System.out.println("1 - ...");
        System.out.println("2 - Efetuar pagamento");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

	public static void ExibirPagamento() {

        // declaração das variáveis
        Scanner scan = new Scanner(System.in);
        double Caltotal = 0, Caltroco = 0;
        int Pagamento;
        String cupom;

        // sistema de rotação para ver o que foi pedido
        for (int nota = 0; nota <7; nota++) { // SUBSTITUIR O 7 PELO NOME DO VETOR DO CARDAPIO

            int pedido = scan.nextInt();
            scan.nextLine();

            Caltotal += pedido;} 
            // aplicação de desconto
            if (Caltotal > 50.00) {
                System.out.println("Desconto de 10%");
                Caltotal *= 0.90;
            }

        System.out.println("Valor total da compra de: " + Caltotal);


        //parte do cupom

         System.out.println("Digite o cupom:");
          cupom = scan.nextLine();

        if (cupom.equals("ALEGIT")) {
         System.out.println("Cupom válido! Desconto aplicado.");
        Caltotal *= 0.90;
        } 
        else {System.out.println("Cupom inválido."); }


        //pagamento final

        System.out.println("Valor total da compra de: " + Caltotal);
        System.out.println("por favor efetuar pagamento");


        Pagamento = scan.nextInt();
        scan.nextLine();

        if (Pagamento < Caltotal) {
            System.out.println("Valor insuficiente");
        }
        else if (Pagamento == Caltotal) {
            System.out.println("Pagamento feito com sucesso");
        }
        else {
            Caltroco = Pagamento - Caltotal;
            System.out.println("Valor a mais, receber " + Caltroco + " de troco");
        }

      


    }
}
