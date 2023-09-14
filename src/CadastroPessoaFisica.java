import java.util.Scanner;

public class CadastroPessoaFisica {
    String numeroCpf;
    int digitoVerificador;
    int digitoVerificadorInformado1, digitoVerificadorInformado2;
    boolean cpfValido;

    public CadastroPessoaFisica() {}
    public CadastroPessoaFisica(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    private void preencherValores(String numeroCpf) {
        int[] caractere = new int[numeroCpf.length()];
        int multiplicador = numeroCpf.length();
        int soma = 0;
        int[] produtoDigito = new int[numeroCpf.length()];

        // Extraindo os dígitos
        for (int i = 0; i < numeroCpf.length(); i++) {
            if (i < numeroCpf.length()-2) {
                caractere[i] = Integer.parseInt(numeroCpf.substring(i, i + 1));
                produtoDigito[i] = multiplicador * caractere[i];
                multiplicador--;
                System.out.println("I: " + i +"\tC: " + caractere[i] + "\tM: " + multiplicador);
                soma += produtoDigito[i];
            } else {
                if (i == 9) {
                    digitoVerificadorInformado1 = Integer.parseInt(numeroCpf.substring(i, i + 1));
                } else if (i == 10) {
                    digitoVerificadorInformado2 = Integer.parseInt(numeroCpf.substring(i, i + 1));
                }
            }
        }
        System.out.println("DVs informados: " + digitoVerificadorInformado1 + digitoVerificadorInformado2);
    }

    private int calcularDigito(int soma) {
        soma *= 10;
        digitoVerificador = soma % 11;
        System.out.println("DV encontrado: " + digitoVerificador);
        return digitoVerificador;
    }

    private boolean verificarDigito(String numero) {
        preencherValores(numero);
//        cpfValido = digitoVerificador == digitoVerificadorInformado;
        return cpfValido;
    }

    public String validarRegistroGeral(String numero) {
        numero = numero.replace(".","").replace("-","");
        boolean valido = verificarDigito(numero);
        String mensagem;

        if (valido) {
            mensagem = "Número de CPF válido!";
        } else {
            mensagem = "Número de CPF inválido!";
        }

        return mensagem;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número do CPF a verificar com os dígitos: ");
        CadastroPessoaFisica cpf = new CadastroPessoaFisica(sc.next());
        System.out.println(cpf.validarRegistroGeral(cpf.numeroCpf));

//        RegistroGeral rg2 = new RegistroGeral();
//        System.out.println(rg2.validarRegistroGeral("41.982.199-5"));
    }
}
