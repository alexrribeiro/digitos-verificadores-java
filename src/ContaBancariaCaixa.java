public class ContaBancariaCaixa {
    String agencia, operacao, conta;
    int digitoInformado, digitoVerificador;
    boolean valido;

    public ContaBancariaCaixa() {}
    public ContaBancariaCaixa(String agencia, String operacao, String conta) {
        this.agencia = agencia;
        this.operacao = operacao;
        this.conta = conta;
    }

    private void calcularDigito(String valor) {
        int[] caractere = new int[valor.length()];
        int multiplicador = 2;
        int soma = 0;
        int[] produtoDigito = new int[valor.length()];

        for (int i = valor.length(); i > 0; i--) {
//            System.out.println(i);
            if (i != valor.length()) {
                caractere[i-1] = Integer.parseInt(valor.substring(i-1, i));
//                System.out.println("Caractere: " + caractere[i]);
                produtoDigito[i-1] = caractere[i-1] * multiplicador;
//                System.out.println("C: " + caractere[i-1] + "\tM: " + multiplicador + "\tP: " + produtoDigito[i-1]);
                multiplicador++;
                if (multiplicador > 9) multiplicador = 2;
                soma += produtoDigito[i-1];
            } else {
                digitoInformado = Integer.parseInt(valor.substring(i-1, i));
//                if (valor.substring(i-1, i).equalsIgnoreCase("0")) {
//                    digitoInformado = 10;
//                }
//                else {
//                    digitoInformado = Integer.parseInt(valor.substring(i-1, i));
//                }
//                System.out.println("DV Inform: " + digitoInformado);
            }
        }

        digitoVerificador = (soma * 10) % 11;
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
        // Se o resultado da conta dá 10, o dígito verificador é X. Para efeitos de cálculo aqui mantivemos como 10.
//        System.out.println("DV Encontr: " + digitoVerificador);
    }

    private boolean verificarDigito(String valor) {
        calcularDigito(valor);
        valido = digitoVerificador == digitoInformado;
        return valido;
    }

    public String validarConta(String agencia, String operacao, String conta) {
        System.out.println("Agencia: " + agencia);
        System.out.println("Operação: " + operacao);
        System.out.println("Conta: " + conta);
        String agenciaEConta = agencia + operacao + conta;
        String mensagem;
        agenciaEConta = agenciaEConta
                .replace(".","")
                .replace("-","");
        valido = verificarDigito(agenciaEConta);

        if (valido) {
            mensagem = "Agência e Conta válidos!\t";
        } else {
            mensagem = "Agência e/ou conta inválidos!\t";
        }

        return mensagem;
    }

    public static void main(String[] args) {
        ContaBancariaCaixa cb = new ContaBancariaCaixa();
        System.out.println(cb.validarConta("4895","013","25893222-8"));
//        System.out.println(cb.validarConta("0981", "023", "91596423-0"));
//        System.out.println(cb.validarConta("2910", "003", "45465987-0"));
    }
}
