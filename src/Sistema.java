import java.security.PublicKey;
import java.util.ArrayList;

public class Sistema {
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
        // Inserir dados de teste
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

    /*** GETS ***/

    /*

    public Produto getProduto(String cod){

    }

    public Pedido getPedido(String cod){

    }

    public Sala getSala(String nome){

    }


     */

    public boolean sistemaVazio() {
        return this.adms.size() == 0;
    }

    /*
    public String gerarCodigoProduto(){

    }

    public String gerarCodigoPedido(){

    }

    public void listarProdutos(){

    }

    public void listarSalas(){

    }

    public ArrayList<Pedidos> filtrarPedidos(Boolean disponivel){


    }

    public ArrayList<Pedidos> filtrarPedidos(Aluno a){


    }



     */
}
