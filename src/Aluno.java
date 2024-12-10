public class Aluno extends Usuario{
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
}
