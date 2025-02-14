import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Sistema {

    // Listas para armazenar diferentes entidades do sistema
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> prods;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();

        lerDados();
    }
    /********************************/
    /** LEITURA E ESCRITA DE DADOS **/
    /********************************/

    public void lerDados(){
        try{
            FileReader fr = new FileReader("dados.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true){
                String line = br.readLine();

                if (line.equals("ADM")){
                    String cpf = br.readLine();
                    String nome = br.readLine();
                    String senha = br.readLine();
                    String email = br.readLine();

                    Admin adm = new Admin(cpf, nome, senha, email);
                    addAdmin(adm);

                } else if (line.equals("ALU")){
                    String cpf = br.readLine();
                    String nome = br.readLine();
                    String senha = br.readLine();

                    Aluno aluno = new Aluno(cpf, nome, senha);
                    addAluno(aluno);

                } else{break;}

            }
            fr.close();

        } catch(FileNotFoundException e){
            System.out.println("File not found");

        } catch(Exception e){
            System.out.println("Erro ao ler dados: " + e.getMessage());

        }
    }

    public void escreverDados(){
        try{
            System.out.println("** Salvando os dados do sistema **");
            FileWriter fw =new FileWriter("dados.txt");

            for (Admin adm : this.adms){
                adm.saveArq(fw);
            }
            for (Aluno aluno : this.alunos){
                aluno.saveArq(fw);
            }

            fw.close();

        } catch(FileNotFoundException e){
            System.out.println("File not found");

        } catch(Exception e){
            System.out.println("Erro ao ler dados: " + e.getMessage());
        }
    }

    // Verifica se o sistema não possui administradores
    public boolean sistemaVazio() {
        return this.adms.isEmpty();
    }

    // Gera um código único para um produto
    public String gerarCodigoProduto(){
        return "PROD-" + (this.prods.size() + 1);
    }

    // Gera um código único para um pedido
    public String gerarCodigoPedido(){
        return "PEDIDO-" + (this.pedidos.size() + 1);
    }

    // Lista todos os produtos do sistema
    public void listarProdutos(){
        for (Produto prod : this.prods) {
            System.out.println(prod.toString() + "\n");
        }
    }

    // Lista todas as salas do sistema
    public void listarSalas(){
        for (Sala sala : this.salas) {
            System.out.println(sala.toString() + "\n");
        }
    }

    // Filtra os pedidos com base na disponibilidade
    public ArrayList<Pedido> filtrarPedidos(Boolean disponivel){
        ArrayList<Pedido> pedidos_filtrados = new ArrayList<>();
        for(Pedido p : this.pedidos){
            if(p.disponivel() == disponivel) pedidos_filtrados.add(p);
        }
        return pedidos_filtrados;
    }

    // Filtra os pedidos associados a um aluno especifico

    public ArrayList<Pedido> filtrarPedidos(Aluno a){
        ArrayList<Pedido> pedidos_filtrados = new ArrayList<>();
        for(Pedido p : this.pedidos){
            if(p.getCliente().equals(a)) pedidos_filtrados.add(p);

        }

        pedidos_filtrados.sort(this::compararPedidos);

        return pedidos_filtrados;
    }

    private int compararPedidos(Pedido p1, Pedido p2) {
        // Primeira comparacao - quantidade de produtos diferentes (maior para menor)
        int cmp = Integer.compare(p2.getCarrinho().size(), p1.getCarrinho().size());

        // Se a quantidade de produtos for igual, compara pelo valor total (maior para menor)
        if (cmp == 0) {
            // Segunda comparacao - valor total dos pedidos (maior para menor)
            cmp = Double.compare(p2.valorTotal(), p1.valorTotal());
        }

        return cmp;
    }


    // Adiciona um novo administrador
    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    // Adiciona um novo aluno
    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    // Adiciona um novo produto
    public void addProduto(Produto p) {
        this.prods.add(p);
    }

    // Adiciona um novo pedido
    public void addPedido(Pedido p) {
        this.pedidos.add(p);
    }

    // Adiciona uma nova sala
    public void addSala(Sala s) {
        this.salas.add(s);
    }

    /*** GETS ***/

    // Recupera um aluno pelo CPF
    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;
        }
        return null;
    }

    // Recupera um administrador pelo CPF
    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;
        }
        return null;
    }

    // Recupera um produto pelo código
    public Produto getProduto(String cod){
        for(Produto prod : this.prods) {
            if(cod.equals(prod.getCodigo())) return prod;
        }
        return null;
    }

    // Recupera um pedido pelo código
    public Pedido getPedido(String cod){
        for(Pedido p : this.pedidos) {
            if(cod.equals(p.getCod())) return p;
        }
        return null;
    }

    // Recupera uma sala pelo nome
    public Sala getSala(String nome){
        for (Sala s : this.salas) {
            if(nome.equals(s.toString())) return s;
        }
        return null;
    }
}
