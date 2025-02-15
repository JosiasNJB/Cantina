public class Produto implements ITwoString {
    private String codigo, nome;
    private int qtd;
    private double valor;

    public Produto(String codigo, String nome, int qtd, double valor){
        this.codigo = codigo;
        this.nome = nome;
        this.qtd = qtd;
        this.valor = valor;
    }

    public String toString(){
        return this.codigo + ": " + this.nome;
    }

    public void retirarDeEstoque(int qtd){
        this.qtd -= qtd;
    }

    /*** GETS AND SETS ***/

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
