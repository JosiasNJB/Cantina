import java.io.FileWriter;
import java.io.IOException;

public class Aluno extends Usuario implements Salvavel{
    private double saldo;

    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
    }
    // Implementar a lógica para adicionar saldo ao aluno.
    public void inserirSaldo(Double valor){

        this.saldo += valor;

    }
    // Implementar a lógica para remover saldo do aluno.
    public boolean retirarSaldo(Double valor){

        // Validar se o saldo é suficiente para retirar o valor.
        if (this.saldo >= valor){
            this.saldo -= valor;
            return true;
        }

        return false;

    }
    public void saveArq(FileWriter fw) throws IOException {
        fw.write("ALU\n");
        fw.write(this.cpf + "\n");
        fw.write(this.nome + "\n");
        fw.write(this.getSenha() + "\n");
    }
    /*** GETS AND SETS ***/

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
