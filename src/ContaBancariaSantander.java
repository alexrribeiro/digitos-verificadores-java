public class ContaBancariaSantander {
    String agencia, conta;
    int digitoVerificador, digitoVerificadorInformado;

    public ContaBancariaSantander() {}

    public ContaBancariaSantander(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }

    void calcularDigito(String valor) {
        int caractere[] = new int[valor.length()];
        int multiplicador;
        int total = 0;
        int produtoDigito[] = new int[valor.length()];

        for (int i = 0; i < valor.length(); i++) {
            if (i != valor.length()-1) {
                caractere[i] = Integer.parseInt(valor.substring(i, i+1));
                multiplicador = verificarMultiplicador(i);
                produtoDigito[i] = caractere[i] * multiplicador;
                produtoDigito[i] = descartarDezena(produtoDigito[i]);

                total += produtoDigito[i];
            }
            else {
                digitoVerificadorInformado = Integer.parseInt(valor.substring(i, i + 1));
                if (digitoVerificadorInformado == 0) {
                    digitoVerificadorInformado = 10;
                }
            }
        }

        total = descartarDezena(total);
        digitoVerificador = 10 - total;
//        System.out.println("DV encontrado: " + digitoVerificador);
    }

    int descartarDezena(int valor) {
        if (valor >= 10) {
            valor = valor % 10;
        }
        return valor;
    }

    int verificarMultiplicador(int posicao) {
        int multiplicador = 0;
        if (posicao == 3 || posicao == 6 || posicao == 8) {
            multiplicador = 1;
        } else if (posicao == 2 || posicao == 7 || posicao == 11) {
            multiplicador = 3;
        } else if (posicao == 1 || posicao == 5 || posicao == 10) {
            multiplicador = 7;
        } else if (posicao == 0 || posicao == 4 || posicao == 9) {
            multiplicador = 9;
        }
        return multiplicador;
    }

    boolean verificarDigito(String agenciaEConta) {
        boolean contaValida;
        calcularDigito(agenciaEConta);
        if (digitoVerificador == digitoVerificadorInformado) {
            contaValida = true;
        }
        else {
            contaValida = false;
        }
        return contaValida;
    }

    String concatenarAgenciaEConta(String agencia, String conta) {
        String agenciaEConta = agencia + conta;
        return agenciaEConta;
    }

    String validarConta(String agencia, String conta) {
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
        ContaBancariaSantander cb = new ContaBancariaSantander();
//        System.out.println(cb.validarConta("4739","01722064-1"));
        System.out.println(cb.validarConta("0662", "03208805-0"));
    }
}
