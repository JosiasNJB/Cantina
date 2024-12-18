public class Item {
    private Produto p;
    private int qtd;

    public Item(Produto p, int qtd){
        this.p = p;
        this.qtd = qtd;
    }

    public String toString() {
        return this.p + "(QTD: " + this.qtd + ")";
    }

    public double valorTotal(){
        return this.p.getValor() * this.qtd;
    }

    /*** GETS AND SETS ***/

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
