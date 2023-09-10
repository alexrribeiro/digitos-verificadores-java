public class ContaBancariaBradesco {
    String agencia, conta;
    int digitoInformado, digitoVerificador;
    boolean valido;

    public ContaBancariaBradesco() {}
    public ContaBancariaBradesco(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }

    void calcularDigito(String valor, String tipo) {
        int caractere[] = new int[valor.length()];
        int multiplicador = 2;
        int soma = 0;
        int produtoDigito[] = new int[valor.length()];

        for (int i = valor.length(); i > 0; i--) {
//            System.out.println(i);
            if (i != valor.length()) {
                caractere[i-1] = Integer.parseInt(valor.substring(i-1, i));
//                System.out.println("Caractere: " + caractere[i]);
                produtoDigito[i-1] = caractere[i-1] * multiplicador;
//                System.out.println("C: " + caractere[i-1] + "\tM: " + multiplicador + "\tP: " + produtoDigito[i-1]);
                multiplicador++;
                if (multiplicador > 7) multiplicador = 2;
                soma += produtoDigito[i-1];
            } else {
                if (valor.substring(i-1, i).equalsIgnoreCase("P") && tipo.equals("c")) {
                    digitoInformado = 10;
                } else if (valor.substring(i-1, i).equalsIgnoreCase("0") && tipo.equals("a")) {
                    digitoInformado = 10;
                } else if (valor.substring(i-1, i).equalsIgnoreCase("0")) {
                    digitoInformado = 0;
                }
                else {
                    digitoInformado = Integer.parseInt(valor.substring(i-1, i));
                }
//                System.out.println("DV Inform: " + digitoInformado);
            }
        }

        digitoVerificador = 11 - (soma % 11);
        if (digitoVerificador == 11) {
            digitoVerificador = 0;
        }
        // Se o resultado da conta dá 10, o dígito verificador é P. Para efeitos de cálculo aqui mantivemos como 10.
//        System.out.println("DV Encontr: " + digitoVerificador);
    }

    boolean verificarDigito(String valor, String tipo) {
        calcularDigito(valor, tipo);
        if (digitoVerificador == digitoInformado) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    String validarConta(String agencia, String conta) {
        System.out.println("Agencia: " + agencia);
        System.out.println("Conta: " + conta);
        String mensagem = "";
        agencia = agencia
                .replace(".","")
                .replace("-","");
        valido = verificarDigito(agencia, "a");

        if (valido) {
            mensagem = "Número da agência válido!\t";
        } else {
            mensagem = "Número da agência inválido!\t";
        }

        conta = conta
                .replace(".","")
                .replace("-","");
        valido = verificarDigito(conta, "c");

        if (valido) {
            mensagem = mensagem + "Numero da conta válido!";
        } else {
            mensagem = mensagem + "Numero da conta inválido!";
        }

        return mensagem;
    }

    public static void main(String[] args) {
        ContaBancariaBradesco cb = new ContaBancariaBradesco();
        System.out.println(cb.validarConta("0314-0", "1759866-P"));
    }
}
