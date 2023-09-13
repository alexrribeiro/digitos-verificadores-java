import java.util.Scanner;

public class RegistroGeral {
    String numeroRg;
    int digitoVerificador;
    int digitoVerificadorInformado;
    boolean rgValido;

    public RegistroGeral() {}
    public RegistroGeral(String numeroRg) {
        this.numeroRg = numeroRg;
    }

    private void calcularDigito(String numeroRg) {
        int[] caractere = new int[numeroRg.length()];
        int mult = numeroRg.length();
        int soma = 0;
        int[] produtoDigito = new int[numeroRg.length()];

        // Extraindo os dígitos
        for (int i = 0; i < numeroRg.length(); i++) {
            if (i != numeroRg.length()-1) {
                caractere[i] = Integer.parseInt(numeroRg.substring(i, i + 1));
                if (mult != 1) {
                    produtoDigito[i] = mult * caractere[i];
                    mult--;
                }
            } else {
                if (numeroRg.substring(i, i + 1).equalsIgnoreCase("X")) {
                    digitoVerificadorInformado = 10;
                } else {
                    digitoVerificadorInformado = Integer.parseInt(numeroRg.substring(i, i + 1));
                }
            }
        }

        // Somar cada produto, multiplicar por 100 e dividir por 11. O resto da divisão é o DV.
        for (int i = 0; i < numeroRg.length(); i++) {
            soma += produtoDigito[i];
        }
        soma *= 100;

        digitoVerificador = soma % 11;
    }

    private boolean verificarDigito(String numeroRg) {
        calcularDigito(numeroRg);
        rgValido = digitoVerificador == digitoVerificadorInformado;
        return rgValido;
    }

    public String validarRegistroGeral(String numeroRg) {
        numeroRg = numeroRg.replace(".","").replace("-","");
        boolean valido = verificarDigito(numeroRg);
        String mensagem;

        if (valido) {
            mensagem = "Número de RG válido!";
        } else {
            mensagem = "Número de RG inválido!";
        }

        return mensagem;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número do RG a verificar com o dígito: ");
        RegistroGeral rg = new RegistroGeral(sc.next());
        System.out.println(rg.validarRegistroGeral(rg.numeroRg));

//        RegistroGeral rg2 = new RegistroGeral();
//        System.out.println(rg2.validarRegistroGeral("41.982.199-5"));
    }
}
