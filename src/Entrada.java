import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior, Josias Neves Jardim Borba e Israel Magalhães
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */

    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("entrada.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALIZAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**********************/
    /** MENUS DO SISTEMA **/
    /**********************/

    /**
     * Exibe o menu principal até que o usuário opte por sair do programa.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Sistema s) {
        if (s.sistemaVazio()) {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(s);
        }

        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Login.\n" +
                "0) Sair.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) login(s);
            else System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }

    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue.
     * @param a: Objeto a classe Admin.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Admin a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar novo administrador.\n" +
                "2) Cadastrar aluno.\n" +
                "3) Cadastrar produto.\n" +
                "4) Cadastrar sala.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) cadAdmin(s);
            if (op == 2) {cadAluno(s);}
            if (op == 3) {cadProduto(s);}
            if (op == 4) {cadSala(s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Aluno a, Sistema s) {
        String msg = "\n*********************\n" +
                "Escolha uma opção:\n" +
                "1) Fazer pedido.\n" +
                "2) Fazer entrega.\n" +
                "3) Meus pedidos.\n" +
                "4) Inserir crédito.\n" +
                "0) Logout.\n";

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) {fazerPedido(a, s);}
            if (op == 2) {entregarPedido(a, s);}
            if (op == 3) {listarPedidos(a, s);}
            if (op == 4) {inserirCredito(a, s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    public void login(Sistema s) {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF:");
        String senha = this.lerLinha("Senha:");

        Admin adm = s.getAdmin(cpf);
        if (adm != null) {
            if (adm.validarAcesso(senha)) {
                this.menu(adm, s);
            }
            else System.out.println("Senha inválida.");
        }
        else {
            Aluno a = s.getAluno(cpf);
            if (a != null) {
                if (a.validarAcesso(senha)) {
                    this.menu(a, s);
                }
                else System.out.println("Senha inválida");
            }
            else {
                System.out.println("Usuário inexistente");
            }
        }
    }

    /***************/
    /*** LEITURA ***/
    /***************/

// Metodo para ler uma Sala do sistema e retornar uma Sala
private Sala lerSala(Sistema s) {
    try {
        System.out.println("Salas disponiveis:\n");
        s.listarSalas();

        String sala = this.lerLinha("Digite a sala:\n");

        while (s.getSala(sala) == null) {
            sala = this.lerLinha("Sala não existente. Tente novamente:\n");
        }

        return s.getSala(sala);
    } catch (Exception e) {
        System.out.println("Erro ao ler sala: " + e.getMessage());
        return null;
    }
}
// Metodo para ler um Item do sistema e retornar um Item
private Item lerItem(Sistema s) {
    System.out.println("Produtos disponiveis:\n");
    s.listarProdutos();

    String prod = this.lerLinha("Digite o codigo do produto:\n");

    while (s.getProduto(prod) == null) {
        prod = this.lerLinha("Codigo de Produto nao existente. Tente novamente:\n");
    }

    Produto p = s.getProduto(prod);

    int qtd = 0;
    try {
        qtd = this.lerInteiro("Digite a quantidade de " + p + " no pedido: \n");
        while (qtd > p.getQtd()) {
            qtd = this.lerInteiro("Quantidade invalida.\nMaximo de " + p.getQtd() + " Disponiveis em estoque para " + p + " Tente novamente:\n");
        }
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
    }

    return new Item(p, qtd);
}

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */

private String lerLinha(String msg) {
    // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
    System.out.print(msg);
    String linha = this.input.nextLine();

    try {
        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
    } catch (StringIndexOutOfBoundsException e) {
        System.out.println("Linha vazia ou inválida. Tente novamente.");
        return lerLinha(msg);
    }
    return linha;
}

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
private int lerInteiro(String msg) {
    // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
    String linha = this.lerLinha(msg);
    try {
        return Integer.parseInt(linha);
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
        return lerInteiro(msg); // Tenta ler novamente
    }
}
    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
private double lerDouble(String msg) {
    // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
    String linha = this.lerLinha(msg);
    try {
        return Double.parseDouble(linha);
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, digite um número decimal.");
        return lerDouble(msg); // Tenta ler novamente
    }
}

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
public void cadAdmin(Sistema s) {
    try {
        System.out.println("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o cpf: \n");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: \n");
        }

        String nome = this.lerLinha("Digite o nome: \n");
        String senha = this.lerLinha("Digite a senha: \n");
        String email = this.lerLinha("Digite o email: \n");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        System.out.println("Usuário " + a + " criado com sucesso.");
    } catch (Exception e) {
        System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
    }
}public void cadAluno(Sistema s) {
    try {
        System.out.println("\n** Cadastrando um novo aluno **\n");
        String cpf = this.lerLinha("Digite o cpf: \n");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: \n");
        }

        String nome = this.lerLinha("Digite o nome: \n");
        String senha = this.lerLinha("Digite a senha: \n");

        Aluno a = new Aluno(cpf, nome, senha);
        s.addAluno(a);

        System.out.println("Usuário " + a + " criado com sucesso.\n");
    } catch (Exception e) {
        System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
    }
}public void cadProduto(Sistema s) {
    try {
        System.out.println("\n** Cadastrando um novo produto **\n");

        String nome = this.lerLinha("Digite o nome do produto: \n");
        int qtd = this.lerInteiro("Digite a quantidade em estoque: \n");
        double valor = this.lerDouble("Digite o valor unitario do produto: \n");

        Produto p = new Produto(s.gerarCodigoProduto(), nome, qtd, valor);
        s.addProduto(p);

        System.out.println("Produto " + p + " criado com sucesso.\n");
    } catch (Exception e) {
        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
    }
}

    // Cadastrando uma nova sala
    public void cadSala(Sistema s){
        System.out.println("\n** Cadastrando uma nova sala **\n");

        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): \n");
        String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): \n");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): \n");

        Sala sl = new Sala(bloco, sala, andar);

        s.addSala(sl);

        System.out.println("Sala " + sl + " criada com sucesso.\n");

    }

    /***************************/
    /** FUNCIONALIDADES ALUNO **/
    /***************************/

// Realizando um novo pedido
public void fazerPedido(Aluno a, Sistema s){
    try {
        System.out.println("\n** Fazendo um novo Pedido **\n");

        Sala sala = lerSala(s);
        ArrayList<Item> carrinho = new ArrayList<>();

        String msg = "\n*********************\n" +
                "Escolha uma opçao:\n" +
                "1) Adicionar item ao carrinho.\n" +
                "2) Finalizar pedido.\n";

        int op = this.lerInteiro(msg);

        while (op != 2) {
            if (op == 1) carrinho.add(this.lerItem(s));
            else System.out.println("Opção invalida. Tente novamente: \n");

            op = this.lerInteiro(msg);
        }

        if (!carrinho.isEmpty()){
            Pedido p = new Pedido(s.gerarCodigoPedido(), a, null, sala, carrinho);

            if(a.getSaldo() >= p.valorTotal()){
                p.confirmar();
                s.addPedido(p);

                System.out.println("Pedido " + p + " criado com sucesso.\n");
            } else {
                System.out.println("Saldo insuficiente para fazer este pedido.\n");
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao fazer pedido: " + e.getMessage());
    }
}

    // Atribuindo aluno entregador e realizando a entrega
    public void entregarPedido(Aluno a, Sistema s){
        System.out.print("\n** Pedidos disponiveis para entrega: **\n");

        ArrayList<Pedido> pedidos = s.filtrarPedidos(true);

        for(Pedido pedido : pedidos){
            System.out.println(pedido + "\n");

        }

        String cod = this.lerLinha("Digite o codigo do pedido: \n");

        while(s.getPedido(cod) == null){
            cod = this.lerLinha("Codigo invalido. Tente novamente: \n");

        }

        Pedido p = s.getPedido(cod);

        p.atribuirEntregador(a);
        p.marcarComoEntregue();

    }
    // Listando os pedidos de um aluno especifico
    public void listarPedidos(Aluno a, Sistema s){
        System.out.print("\nPedidos de " + a + " **\n");
        System.out.println("*");

        for (Pedido p : s.filtrarPedidos(a)) {
            System.out.print(p);

            System.out.println("*");
        }

    }
    // Inserindo credito na conta do aluno
    public void inserirCredito(Aluno a, Sistema s){
        System.out.print("\n** Inserindo saldo **\n");

        double valor = this.lerDouble("Digite o valor a ser adicionado no saldo: \n");

        a.inserirSaldo(valor);
    }


}