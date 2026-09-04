import java.util.Scanner;

public class Principal {

    static Scanner entrada = new Scanner(System.in);

    // Declaração das variáveis
    static String[] produtos = {"X-Burguer","X-Salada","Batata Frita","Refrigerante","Suco"};
    static double[] precos = {15.00,18.00,12.00,6.00,8.00};
    static int[] quantidades = new int[5];


// codigo principal do professor
    public static void main(String[] args) {

        int opcao;

        do {
            exibirCabecalho();   // <== METODO COMPARTILHADO (ponto de conflito)
            exibirMenuPrincipal();

            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:ExibirCardapio();break;
                case 2:ExibirPagamento();break;
                case 0:System.out.println("Encerrando...");break;
                default:System.out.println("Opcao invalida!");}

        } while (opcao != 0);
        entrada.close();
    }

    // CABEÇALHO
    public static void exibirCabecalho() {
        System.out.println("=====================");
        System.out.println("Caixa de Lanchonete");
        System.out.println("=====================");
    }

    // MENU PRINCIPAL
    public static void exibirMenuPrincipal() {
        System.out.println("1 - Exibir cardapio");
        System.out.println("2 - Efetuar pagamento");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // =========================
    // PARTE B - PAGAMENTO
    // =========================

    public static void ExibirPagamento() {

        double calTotal = 0;
        double calTroco = 0;

        // Calcula o total usando os produtos adicionados
        for (int i = 0; i < produtos.length; i++) {

            if (quantidades[i] > 0) {
                double subtotal = precos[i] * quantidades[i];
                calTotal += subtotal;
            }
        }

        if (calTotal == 0) {System.out.println("Nenhum produto foi adicionado ao pedido."); return;}

        // Desconto de 10% para compras acima de R$ 50
        if (calTotal > 50.00) {System.out.println("Desconto de 10% aplicado!");calTotal *= 0.90;}

        System.out.printf("Valor total da compra: R$ %.2f%n", calTotal);

        // Cupom com sistema de deixar as letras maisculas
        System.out.print("Digite o cupom: ");
        String cupom = entrada.next().toUpperCase();

        if (cupom.equals("ALEGIT")) {
            System.out.println("Cupom válido! Desconto aplicado.");
            calTotal *= 0.90;
        } else {
            System.out.println("Cupom inválido.");
        }

        // Pagamento
        System.out.printf("Valor final da compra: R$ %.2f%n", calTotal);
        System.out.print("Por favor, efetue o pagamento: R$ ");

        double pagamento = entrada.nextDouble();

        if (pagamento < calTotal) {

            System.out.println("Valor insuficiente.");

        } else if (pagamento == calTotal) {

            System.out.println("Pagamento feito com sucesso!");

        } else {

            calTroco = pagamento - calTotal;
//%.2f mostra o valor com 2 casas decimais %n pule para a proxima linha
            System.out.printf("Pagamento feito com sucesso! Troco: R$ %.2f%n", calTroco);
        }
    }

    // =========================
    // PARTE A - CARDÁPIO
    // =========================

    public static void ExibirCardapio() {

        int opcao;

        do {

            exibirMenuLanchonete();
// tem que deixar o codigo dessa maneira para n da erro de sistaxe
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:
                    exibirCardapio();
                    break;

                case 2:
                    adicionarProduto();
                    break;

                case 3:
                    exibirPedido();
                    break;
// DENTRO DO SWITCH O COMANDO CHAMA A PARTE "B"
                case 4:
                    ExibirPagamento();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // MENU DA LANCHONETE
    public static void exibirMenuLanchonete() {

        System.out.println();
        System.out.println("===============================");
        System.out.println("      LANCHONETE DO BAIRRO");
        System.out.println("===============================");
        System.out.println("1 - Ver cardápio");
        System.out.println("2 - Adicionar produto ao pedido");
        System.out.println("3 - Ver pedido");
        System.out.println("4 - Efetuar pagamento");
        System.out.println("0 - Sair");
        System.out.println("===============================");
    }

    // EXIBIR CARDÁPIO
    public static void exibirCardapio() {

        System.out.println();
        System.out.println("---------- CARDÁPIO ----------");

        for (int i = 0; i < produtos.length; i++) {

            System.out.printf("%d - %s R$ %.2f%n",i + 1,produtos[i],precos[i]);}
//d int s string %.2f duas casas decimais %n proxima linha
        System.out.println("------------------------------");
    }

    // ADICIONAR PRODUTO
    public static void adicionarProduto() {

        exibirCardapio();

        System.out.print("Digite o código do produto: ");
        int codigo = entrada.nextInt();
// vai para o outro e retorna se true
        if (!codigoValido(codigo)) {
            System.out.println("Código de produto inválido!");
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = entrada.nextInt();
// vai para o outro e retorna se true
        if (!quantidadeValida(quantidade)) {
            System.out.println("A quantidade deve ser maior que zero!");
            return;
        }

        int posicao = codigo - 1;

        quantidades[posicao] =
            quantidades[posicao] + quantidade;

        System.out.println();
        System.out.println("Produto adicionado com sucesso!");
        System.out.println(
            produtos[posicao] +
            " - Quantidade: " +
            quantidade
        );
    }

    // VALIDAR CÓDIGO
    public static boolean codigoValido(int codigo) {

        if (codigo >= 1 && codigo <= produtos.length) {
            return true;
        }

        return false;
    }

    // VALIDAR QUANTIDADE
    public static boolean quantidadeValida(int quantidade) {

        if (quantidade > 0) {
            return true;
        }

        return false;
    }

    // EXIBIR PEDIDO
    public static void exibirPedido() {

        System.out.println();
        System.out.println("---------- PEDIDO ----------");

        boolean temProduto = false;

        for (int i = 0; i < produtos.length; i++) {

            if (quantidades[i] > 0) {

                double subtotal =
                    precos[i] * quantidades[i];
// s string d o valor inteiro 2f duas casas decimais n proxima linha
                System.out.printf("%s | Qtd: %d | R$ %.2f%n",produtos[i],quantidades[i],subtotal); temProduto = true;
            }
        }

        if (!temProduto) {System.out.println("Nenhum produto foi adicionado.");
        }

        System.out.println("----------------------------");
    }
}
