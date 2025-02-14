import java.io.FileWriter;
import java.io.IOException;

public class Admin extends Usuario implements Salvavel{
    private String email;

    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }
    // Valida o acesso verificando a validação da superclasse
    public boolean validarAcesso(String pwd) {
        return super.validarAcesso(pwd);
    }

    public String toString() {
        return super.toString() + " (ADMIN)";
    }

    public void saveArq(FileWriter fw) throws IOException {
        fw.write("ADM\n");
        fw.write(this.cpf + "\n");
        fw.write(this.nome + "\n");
        fw.write(this.email + "\n");
        fw.write(this.getSenha() + "\n");
    }

}
