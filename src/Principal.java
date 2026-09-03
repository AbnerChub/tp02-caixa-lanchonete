import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcao;

        do {
            exibirCabecalho();
            exibirMenu();

            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:
                    exibirCardapio();
                    break;

                case 2:
                    adicionarProduto(entrada);
                    break;

                case 3:
                    exibirPedido();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        entrada.close();
    }

    // ==============================
    // CABECALHO
    // ==============================

    public static void exibirCabecalho() {

        System.out.println("============================");
        System.out.println("      LANCHONETE DO BAIRRO");
        System.out.println("============================");
    }

    // ==============================
    // MENU
    // ==============================

    public static void exibirMenu() {

        System.out.println();
        System.out.println("1 - Ver cardapio");
        System.out.println("2 - Adicionar produto ao pedido");
        System.out.println("3 - Ver pedido");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // ==============================
    // CARDAPIO
    // ==============================

    static String[] produtos = {
        "X-Burguer",
        "X-Salada",
        "Batata Frita",
        "Refrigerante",
        "Suco"
    };

    static double[] precos = {
        15.00,
        18.00,
        12.00,
        6.00,
        8.00
    };

    static int[] quantidades = new int[5];

    public static void exibirCardapio() {

        System.out.println();
        System.out.println("---------- CARDAPIO ----------");

        for (int i = 0; i < produtos.length; i++) {

            System.out.printf(
                "%d - %-20s R$ %.2f%n",
                i + 1,
                produtos[i],
                precos[i]
            );
        }

        System.out.println("------------------------------");
    }

    // ==============================
    // ADICIONAR PRODUTO
    // ==============================

    public static void adicionarProduto(Scanner entrada) {

        exibirCardapio();

        System.out.print("Digite o codigo do produto: ");
        int codigo = entrada.nextInt();

        if (!codigoValido(codigo)) {
            System.out.println("Codigo de produto invalido!");
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = entrada.nextInt();

        if (!quantidadeValida(quantidade)) {
            System.out.println("A quantidade deve ser maior que zero!");
            return;
        }

        int posicao = codigo - 1;

        quantidades[posicao] = quantidades[posicao] + quantidade;

        System.out.println();
        System.out.println("Produto adicionado com sucesso!");
        System.out.println(
            produtos[posicao] + " - Quantidade: " + quantidade
        );
    }

    // ==============================
    // VALIDAR CODIGO
    // ==============================

    public static boolean codigoValido(int codigo) {

        if (codigo >= 1 && codigo <= produtos.length) {
            return true;
        }

        return false;
    }

    // ==============================
    // VALIDAR QUANTIDADE
    // ==============================

    public static boolean quantidadeValida(int quantidade) {

        if (quantidade > 0) {
            return true;
        }

        return false;
    }

    // ==============================
    // EXIBIR PEDIDO
    // ==============================

    public static void exibirPedido() {

        System.out.println();
        System.out.println("---------- PEDIDO ----------");

        boolean temProduto = false;

        for (int i = 0; i < produtos.length; i++) {

            if (quantidades[i] > 0) {

                double subtotal = precos[i] * quantidades[i];

                System.out.printf(
                    "%s | Qtd: %d | R$ %.2f%n",
                    produtos[i],
                    quantidades[i],
                    subtotal
                );

                temProduto = true;
            }
        }

        if (!temProduto) {
            System.out.println("Nenhum produto foi adicionado.");
        }

        System.out.println("----------------------------");
    }
}