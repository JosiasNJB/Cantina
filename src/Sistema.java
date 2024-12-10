import java.util.ArrayList;

public class Sistema {
    /*
    private ArrayList<Aluno> alunos;
    private ArrayList<Admin> adms;
    private ArrayList<Produto> prods;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Sala> salas;
    */

    ArrayList<Aluno> alunos;
    ArrayList<Admin> adms;
    ArrayList<Produto> prods;
    ArrayList<Pedido> pedidos;
    ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();

    }

    public boolean sistemaVazio() {
        return this.adms.isEmpty();

    }

    public String gerarCodigoProduto(){
        return "PROD-" + this.prods.size() + 1;

    }

    public String gerarCodigoPedido(){
        return "PEDIDO-" + this.pedidos.size() + 1;

    }

    public void listarProdutos(){
        for (Produto prod : this.prods) {
            System.out.println(prod.toString() + "\n");

        }

    }

    public void listarSalas(){
        for (Sala sala : this.salas) {
            System.out.println(sala.toString() + "\n");

        }

    }

    public ArrayList<Pedido> filtrarPedidos(Boolean disponivel){
        ArrayList<Pedido> pedidos_filtrados = new ArrayList<>();

        for(Pedido p : this.pedidos){
            if(p.disponivel() == disponivel) pedidos_filtrados.add(p);

        }

        return pedidos_filtrados;

    }

    //NEED TO CHECK IF IT WORKS
    public ArrayList<Pedido> filtrarPedidos(Aluno a){
        ArrayList<Pedido> pedidos_filtrados = new ArrayList<>();

        for(Pedido p : this.pedidos){
            if(p.getCliente().equals(a)) pedidos_filtrados.add(p);

        }

        return pedidos_filtrados;

    }

    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    public void addProduto(Produto p) {
        this.prods.add(p);
    }

    public void addPedido(Pedido p) {
        this.pedidos.add(p);
    }

    public void addSala(Sala s) {
        this.salas.add(s);
    }

    /*** GETS ***/

    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;

        }

        return null;

    }

    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;

        }

        return null;

    }

    public Produto getProduto(String cod){
        for(Produto prod : this.prods) {
            if(cod.equals(prod.getCodigo())) return prod;

        }

        return null;

    }

    public Pedido getPedido(String cod){
        for(Pedido p : this.pedidos) {
            if(cod.equals(p.getCod())) return p;

        }

        return null;

    }

    //NEED TO CHECK IF IT WORKS
    public Sala getSala(String nome){
        for (Sala s : this.salas) {
            if(nome.equals(s.toString())) return s;

        }

        return null;

    }
}
