public class Item {
    private Produto p;
    private int qtd;

    public Item(Produto p, int qtd){
        this.p = p;
        this.qtd = qtd;
    }

    // UNFINISHED
    public String toString() {
        return "" + qtd;
    }

    // UNFINISHED
    public double valorTotal(){
        return qtd * p.getValor();
    }

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
