public class Admin extends Usuario {
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

}
