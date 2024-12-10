public class Sala {
    private String bloco;
    private String sala;
    private String andar;

    // FINISHED
    public Sala(String bloco, String sala, String andar) {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    public String toString() {
        return this.bloco + this.sala + this.andar;
    }

    /*** GETS AND SETS ***/

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }
}
