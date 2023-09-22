public class CadastroPessoaFisica {
    String numeroCpf;
    int digitoVerificador;
    int digitoVerificadorInformado1, digitoVerificadorInformado2;
    boolean cpfValido;

    public CadastroPessoaFisica() {}
    public CadastroPessoaFisica(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    private int[] multiplicarValores(String numeroCpf, int ordem) {
        int[] caractere = new int[numeroCpf.length()];
        int multiplicador = 0;

        if (ordem == 1) {
            multiplicador = numeroCpf.length() - 1;
        } else if (ordem == 2) {
            multiplicador = numeroCpf.length();
        }

        int[] produto = new int[numeroCpf.length()];

        for (int i = 0; i < numeroCpf.length(); i++) {
            caractere[i] = Integer.parseInt(numeroCpf.substring(i, i + 1));
            produto[i] = multiplicador * caractere[i];
//            System.out.println("I: " + i +"\tC: " + caractere[i] + "\tM: " + multiplicador + "\tP: " + produto[i]);
            multiplicador--;
        }
        return produto;
    }

    private void informarDigitosVerificadores(String numeroCpf) {
        digitoVerificadorInformado1 = Integer.parseInt(numeroCpf.substring(9, 10));
        digitoVerificadorInformado2 = Integer.parseInt(numeroCpf.substring(10, 11));
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
        cpfValido = digitoVerificador1 == digitoVerificadorInformado1 && digitoVerificador2 == digitoVerificadorInformado2;

        System.out.println(numero);
        if (numero == "00000000000" || numero == "11111111111" || numero == "22222222222" || numero == "33333333333" ||
        numero == "44444444444" || numero == "55555555555" || numero == "66666666666" || numero == "77777777777" ||
        numero == "88888888888" || numero == "99999999999") {
            cpfValido = false;
        }
        System.out.println(cpfValido);

        return cpfValido;
    }

    public String validarCadastroPessoaFisica(String numero) {
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
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Digite o número do CPF a verificar com os dígitos: ");
//        CadastroPessoaFisica cpf = new CadastroPessoaFisica(sc.next());
//        System.out.println(cpf.validarCadastroPessoaFisica(cpf.numeroCpf));

        CadastroPessoaFisica cpf = new CadastroPessoaFisica();
        System.out.println(cpf.validarCadastroPessoaFisica("529.874.908-95"));
        System.out.println(cpf.validarCadastroPessoaFisica("391.653.708-30"));
        System.out.println(cpf.validarCadastroPessoaFisica("433.858.508-00"));
        System.out.println(cpf.validarCadastroPessoaFisica("888.888.888-88"));
    }
}
