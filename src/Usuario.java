public abstract class Usuario implements ITwoString {
    protected String cpf, nome;
    private String senha;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean validarAcesso(String s) {
        return s.equals(this.senha);
    }

    // Metodo para alterar a senha do usuário
    // Retorna true se a senha foi alterada com sucesso, false caso contrário
    public boolean alterarSenha(String senha_atual, String senha_nova) {
        if (this.validarAcesso(senha_atual)) {
            this.senha = senha_nova;
            return true;
        }
        return false;
    }


    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getSenha() {return this.senha; }
}
