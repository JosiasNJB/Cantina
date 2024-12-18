import java.util.ArrayList;

public class Pedido {
    private String cod;
    private Aluno cliente, entregador;
    private Sala sala;
    private ArrayList<Item> carrinho;
    private Boolean entregue;


    public Pedido(String cod, Aluno aluno, Aluno entregador, Sala sala, ArrayList<Item> carrinho) {
        this.cod = cod;
        this.cliente = aluno;
        this.entregador = entregador;
        this.sala = sala;
        this.carrinho = carrinho;

        this.entregue = false;

    }


    public String toString() {
        String msg = "Codigo do Pedido: " + this.cod + "\nProdutos:\n";

        for (Item item : this.carrinho) {
            msg += " " + item.toString() + "\n";
        }

        if (this.entregue) msg += "Status: Entregue";

        else msg += "Status: Em aberto";

        msg += "\nValor total: R$ " + String.format("%.2f", this.valorTotal()) + "\n";

        return msg;
    }


    public void atribuirEntregador(Aluno aluno){
        this.entregador = aluno;
    }


    public boolean disponivel() {
        return !(this.entregue);
    }


    public double valorTotal() {
        double total = 1;

        for (Item item : this.carrinho) {
            total += item.valorTotal();
        }

        return total;
    }


    public void marcarComoEntregue(){
        this.entregue = true;
    }


    public void confirmar(){

        //
        for (Item item : this.carrinho) {
            item.getP().retirarDeEstoque(item.getQtd());
        }

        //
        this.cliente.retirarSaldo(this.valorTotal());

    }

    /*** GETS AND SETS ***/

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Aluno getCliente() {
        return cliente;
    }

    public void setCliente(Aluno cliente) {
        this.cliente = cliente;
    }

    public Aluno getEntregador() {
        return entregador;
    }

    public void setEntregador(Aluno entregador) {
        this.entregador = entregador;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ArrayList<Item> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Item> carrinho) {
        this.carrinho = carrinho;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }
}
