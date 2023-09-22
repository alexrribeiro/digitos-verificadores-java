public class CadastroPessoaJuridica {
    String numeroCnpj;
    int digitoVerificador;
    int digitoVerificadorInformado1, digitoVerificadorInformado2;
    boolean cnpjValido;

    public CadastroPessoaJuridica() {}
    public CadastroPessoaJuridica(String numeroCnpj) {
        this.numeroCnpj = numeroCnpj;
    }

    private int[] multiplicarValores(String numeroCnpj, int ordem) {
        int[] caractere = new int[numeroCnpj.length()];
        int multiplicador = 2;
        int[] produto = new int[numeroCnpj.length()];
        int inicio = numeroCnpj.length();

        if (ordem == 1) {
            inicio = numeroCnpj.length() - 2;
        } else if (ordem == 2) {
            inicio = numeroCnpj.length() - 1;
        }

        for (int i = inicio; i > 0; i--) {
            caractere[i-1] = Integer.parseInt(numeroCnpj.substring(i-1, i));
            produto[i-1] = multiplicador * caractere[i-1];
//            System.out.println("I: " + i +"\tC: " + caractere[i-1] + "\tM: " + multiplicador + "\tP: " + produto[i-1]);
            if (multiplicador == 9) {
                multiplicador = 2;
            } else {
                multiplicador++;
            }
        }
        return produto;
    }

    private void informarDigitosVerificadores(String numeroCnpj) {
        digitoVerificadorInformado1 = Integer.parseInt(numeroCnpj.substring(12, 13));
        digitoVerificadorInformado2 = Integer.parseInt(numeroCnpj.substring(13, 14));
        System.out.println("DVs informados: " + digitoVerificadorInformado1 + digitoVerificadorInformado2);
    }

    private int calcularDigitoVerificador(int[] grupo, int tamanho) {
        int soma = 0;

        for (int i = 0; i < tamanho; i++) {
            soma += grupo[i];
        }
        soma *= 10;
        digitoVerificador = soma % 11;
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
        System.out.println("DV encontrado: " + digitoVerificador);
        return digitoVerificador;
    }

    private boolean verificarDigito(String numero) {
        informarDigitosVerificadores(numero);
        int[] primeiroGrupo = multiplicarValores(numero, 1);
        int digitoVerificador1 = calcularDigitoVerificador(primeiroGrupo, primeiroGrupo.length - 2);
        int[] segundoGrupo = multiplicarValores(numero, 2);
        int digitoVerificador2 = calcularDigitoVerificador(segundoGrupo, segundoGrupo.length - 1);
        cnpjValido = digitoVerificador1 == digitoVerificadorInformado1 && digitoVerificador2 == digitoVerificadorInformado2;

        return cnpjValido;
    }

    public String validarCadastroPessoaJuridica(String numero) {
        numero = numero
                .replace(".","")
                .replace("-","")
                .replace("/", "");
        boolean valido = verificarDigito(numero);
        String mensagem;

        if (valido) {
            mensagem = numero + ": Número de CNPJ válido!";
        } else {
            mensagem = numero + ": Número de CNPJ inválido!";
        }

        return mensagem;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Digite o número do CNPJ a verificar com os dígitos: ");
//        CadastroPessoaJuridica cnpj = new CadastroPessoaFisica(sc.next());
//        System.out.println(cnpj.validarCadastroPessoaFisica(cnpj.numeroCpf));

        CadastroPessoaJuridica cnpj = new CadastroPessoaJuridica();
        System.out.println(cnpj.validarCadastroPessoaJuridica("00.000.000/0001-91")); // Banco do Brasil
        System.out.println(cnpj.validarCadastroPessoaJuridica("15.398.577/0001-70"));
        System.out.println(cnpj.validarCadastroPessoaJuridica("42.149.415/0001-71"));
        System.out.println(cnpj.validarCadastroPessoaJuridica("59.460.156/0001-06"));
    }
}
