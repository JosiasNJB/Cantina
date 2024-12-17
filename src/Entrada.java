import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

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
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
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
            if (op == 2) {/*cadAluno(s);*/}
            if (op == 3) {/*cadProduto(s);*/}
            if (op == 4) {/*cadSala(s);*/}
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
            if (op == 1) {/*fazerPedido(a, s);*/}
            if (op == 2) {/*entregarPedido(a, s);*/}
            if (op == 3) {/*listarPedidos(a, s);*/}
            if (op == 4) {/*inserirCredito(a, s);*/}
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

    private Sala lerSala(Sistema s){
        // Implemente aqui o código para ler uma sala do sistema
        System.out.println("Salas disponiveis:\n");
        s.listarSalas();

        String sala = this.lerLinha("Digite a sala:\n");

        while (s.getSala(sala) == null) {
            sala = this.lerLinha("Sala não existente. Tente novamente:\n");
        }

        return s.getSala(sala);

    }

    private Item lerItem(Sistema s){

        // Implemente aqui o código para ler um item do sistema
        System.out.println("Produtos disponiveis:\n");
        s.listarProdutos();

        String prod = this.lerLinha("Digite o codigo do produto:\n");

        while (s.getProduto(prod) == null) {
            prod = this.lerLinha("Codigo de Produto nao existente. Tente novamente:\n");
        }

        Produto p = s.getProduto(prod);

        int qtd = this.lerInteiro("Digite a quantidade de " + p + " no pedido: ");

        while (qtd > p.getQtd()) {
            qtd = this.lerInteiro("Quantidade invalida. Maximo de " + p.getQtd() + "\nDisponiveis em estoque para " + p + " Tente novamente:\n");
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

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
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
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAdmin(Sistema s) {
        System.out.println("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        System.out.println("Usuário " + a + " criado com sucesso.");
    }

    public void cadAluno(Sistema s){
        // Implemente aqui o código para cadastrar um novo aluno
        System.out.println("\n** Cadastrando um novo aluno **\n");
        String cpf = this.lerLinha("Digite o cpf: ");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");

        Aluno a = new Aluno(cpf, nome, senha);
        s.addAluno(a);

        System.out.println("Usuário " + a + " criado com sucesso.");

    }

    public void cadProduto(Sistema s){
        // Implemente aqui o código para cadastrar um novo produto
        System.out.println("\n** Cadastrando um novo produto **\n");

        String nome = this.lerLinha("Digite o nome do produto: ");
        int qtd = this.lerInteiro("Digite a quantidade em estoque: ");
        double valor = this.lerDouble("Digite o valor unitario do produto: ");

        Produto p = new Produto(s.gerarCodigoProduto(), nome, qtd, valor);
        s.addProduto(p);

        System.out.println("Produto " + p + " criado com sucesso.");

    }

    public void cadSala(Sistema s){
        // Implemente aqui o código para cadastrar uma nova sala
        System.out.println("\n** Cadastrando uma nova sala **\n");

        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
        String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): ");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");

        Sala sl = new Sala(bloco, sala, andar);

        s.addSala(sl);

        System.out.println("Sala " + sl + " criada com sucesso.");

    }


    /***************************/
    /** FUNCIONALIDADES ALUNO **/
    /***************************/


    public void fazerPedido(Aluno a, Sistema s){
        // Implemente aqui o código para fazer um novo pedido
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

            else System.out.println("Opção invalida. Tente novamente: ");

            op = this.lerInteiro(msg);

        }
        if (!carrinho.isEmpty()){
            Pedido p = new Pedido(s.gerarCodigoPedido(), a, null, sala, carrinho);

            if(a.getSaldo() >= p.valorTotal()){
                p.confirmar();
                s.addPedido(p);

                System.out.println("Pedido " + p + " criado com sucesso.");

            } else{
                System.out.println("Saldo insuficiente para fazer este pedido.");

            }
        }
    }

    public void entregarPedido(Aluno a, Sistema s){
        // Implemente aqui o código para entregar um pedido
        System.out.print("\n** Pedidos disponiveis para entrega: **\n");

        ArrayList<Pedido> pedidos = s.filtrarPedidos(true);

        for(Pedido pedido : pedidos){
            System.out.println(pedido + "\n");

        }

        String cod = this.lerLinha("Digite o codigo do pedido: ");

        while(s.getPedido(cod) == null){
            cod = this.lerLinha("Codigo invalido. Tente novamente: \n");

        }

        Pedido p = s.getPedido(cod);

        p.atribuirEntregador(a);
        p.marcarComoEntregue();

    }

    public void listarPedidos(Aluno a, Sistema s){
        // Implemente aqui o código para listar os pedidos de um aluno
        System.out.print("\n** Pedido de " + a + " **\n");

        for (Pedido p : s.filtrarPedidos(a)) {
            System.out.print(p + "\n");

        }

    }

    public void inserirCredito(Aluno a, Sistema s){
        // Implemente aqui o código para inserir crédito em um aluno
        System.out.print("\n** Inserindo saldo **\n");

        double valor = this.lerDouble("Digite o valor a ser adicionado no saldo: ");

        a.inserirSaldo(valor);
    }


}