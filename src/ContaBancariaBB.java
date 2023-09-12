public class ContaBancariaBB {
    String agencia, conta;
    int digitoInformado, digitoVerificador;
    boolean valido;

    public ContaBancariaBB() {}
    public ContaBancariaBB(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }

    void calcularDigito(String valor) {
        int caractere[] = new int[valor.length()];
        int multiplicador = valor.length();
        int soma = 0;
        int produtoDigito[] = new int[valor.length()];

        for (int i = 0; i < valor.length(); i++) {
            if (i != valor.length() - 1) {
                caractere[i] = Integer.parseInt(valor.substring(i, i+1));
//                System.out.println("Caractere: " + caractere[i]);
                produtoDigito[i] = caractere[i] * multiplicador;
//                System.out.println("C: " + caractere[i] + "\tM: " + multiplicador + "\tP: " + produtoDigito[i]);
                multiplicador--;
                soma += produtoDigito[i];
            } else {
                if (valor.substring(i, i+1).equalsIgnoreCase("X")) {
                    digitoInformado = 10;
                } else {
                    digitoInformado = Integer.parseInt(valor.substring(i, i+1));
                }
//                System.out.println("DV: " + digitoInformado);
            }
        }

        digitoVerificador = 11 - (soma % 11);
        if (digitoVerificador == 11) {
            digitoVerificador = 0;
        }
        // Se o resultado da conta dá 10, o dígito verificador é X. Para efeitos de cálculo aqui mantivemos como 10.
//        System.out.println("DV Encontr: " + digitoVerificador);
    }

    boolean verificarDigito(String valor) {
        calcularDigito(valor);
        if (digitoVerificador == digitoInformado) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    String removerCaracteresEspeciais (String texto) {
        texto = texto
                .replace(".","")
                .replace("-","")
                .replace(" ","")
                .replace("/","");
        return texto;
    }

    String validarConta(String agencia, String conta) {
        System.out.println("Agencia: " + agencia + "\tConta: " + conta);
        String mensagem = "";
        agencia = removerCaracteresEspeciais(agencia);
        valido = verificarDigito(agencia);

        if (valido) {
            mensagem = "Número da agência válido!\t";
        } else {
            mensagem = "Número da agência inválido!\t";
        }

        conta = removerCaracteresEspeciais(conta);
        valido = verificarDigito(conta);

        if (valido) {
            mensagem = mensagem + "Numero da conta válido!";
        } else {
            mensagem = mensagem + "Numero da conta inválido!";
        }

        return mensagem;
    }

    public static void main(String[] args) {
        ContaBancariaBB cb = new ContaBancariaBB();
        System.out.println(cb.validarConta("1584-9","16.437-2"));
    }
}
