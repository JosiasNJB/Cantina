import java.util.ArrayList;

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
        return pedidos_filtrados;
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
