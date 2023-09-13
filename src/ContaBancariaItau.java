public class ContaBancariaItau {
    String agencia;
    String conta;
    int digitoVerificador;
    int digitoVerificadorInformado;

    public ContaBancariaItau() {}
    public ContaBancariaItau(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }

    private void calcularDigito(String agenciaEConta) {
        int[] caractere = new int[agenciaEConta.length()];
        int multiplicador = verificarMultiplicador(agenciaEConta);
        int soma = 0;
        int[] produtoDigito = new int[agenciaEConta.length()];

        for (int i = 0; i < agenciaEConta.length(); i++) {
            if (i != agenciaEConta.length()-1) {
                caractere[i] = Integer.parseInt(agenciaEConta.substring(i, i+1));
                produtoDigito[i] = caractere[i] * multiplicador;
                if (produtoDigito[i] >= 10) {
                    produtoDigito[i] = 1 + (produtoDigito[i] - 10);
                }
                if (multiplicador == 1) {
                    multiplicador = 2;
                } else if (multiplicador == 2) {
                    multiplicador = 1;
                }
                soma += produtoDigito[i];
            }
            else {
                digitoVerificadorInformado = Integer.parseInt(agenciaEConta.substring(i, i + 1));
            }
        }

        digitoVerificador = 10 - (soma % 10);
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
    }

    private int verificarMultiplicador(String agenciaEConta) {
//        Apesar do Itaú ter um padrão 4 dígitos para agência e 5+DV para conta,
//        vai que alguém não coloca o zero... aí como está lendo da esquerda para a direita...
        int multiplicador = 0;
//        Tamanho da string conta o dígito também, por isso parece invertido
        if (agenciaEConta.length() % 2 == 1) {
            multiplicador = 1;
        } else if (agenciaEConta.length() %2 == 0) {
            multiplicador = 2;
        }
        return multiplicador;
    }

    private boolean verificarDigito(String agenciaEConta) {
        boolean contaValida;
        calcularDigito(agenciaEConta);
        contaValida = digitoVerificador == digitoVerificadorInformado;
        return contaValida;
    }

    private String concatenarAgenciaEConta(String agencia, String conta) {
        return agencia + conta;
    }

    public String validarConta(String agencia, String conta) {
        System.out.println("Agencia: " + agencia + "\tConta: " + conta);
        String agenciaEConta = concatenarAgenciaEConta(agencia, conta)
                .replace(".","")
                .replace("-","");
        boolean valido = verificarDigito(agenciaEConta);
        String mensagem;

        if (valido) {
            mensagem = "Número de conta válida!";
        } else {
            mensagem = "Número de conta inválido!";
        }
        return mensagem;
    }

    public static void main(String[] args) {
        ContaBancariaItau cb = new ContaBancariaItau();
        System.out.println(cb.validarConta("0747","46128-3"));
    }
}
