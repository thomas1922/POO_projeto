package supermercado;

import java.io.Serializable;
import java.util.Scanner;

public class Data implements Serializable {

    private int dia;
    private int mes;
    private int ano;

    /**
     * Empty constructor
     */
    public Data() {

    }

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    /**
     * method that checks if the current date is between the initial date and
     * final date. If true returns 1, if false returns 0
     *
     * @param dataInicio initial date
     * @param dataFim final date
     * @param dataHoje current date
     * @return 0 or 1
     */
    public int comparaDatas(Data dataInicio, Data dataFim, Data dataHoje) {
        if (dataHoje.ano >= dataInicio.ano && dataHoje.ano <= dataFim.ano) {
            if (dataHoje.mes >= dataInicio.mes && dataHoje.mes <= dataFim.mes) {
                if (dataHoje.dia >= dataInicio.dia && dataHoje.dia <= dataFim.dia) {
                    return 1;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * method that checks if the new date is earlier than the current date. If
     * true returns 1, if false returns 0.
     *
     * @param nova new date
     * @param atual current date
     * @return 0 or 1
     */
    public int verificaData(Data nova, Data atual) {
        if (nova.ano == atual.ano) {
            if (nova.mes < atual.mes) {
                return 1;
            } else {
                if (nova.mes == atual.mes) {
                    if (nova.dia < atual.dia) {
                        return 1;
                    }
                }
            }
        }
        if (nova.ano < atual.ano) {
            return 1;
        }
        return 0;
    }

    /**
     * Method that asks the user to insert a day, a month and a year using the
     * method scannerDatas and returns a date
     *
     * @return date
     */
    public Data novaData() {
        int dia, mes, ano;
        Data nova;
        System.out.println("Insira o dia: ");
        dia = scannerDatas();
        System.out.println("Insira o mes ");
        mes = scannerDatas();
        System.out.println("Insira o ano ");
        ano = scannerDatas();
        nova = new Data(dia, mes, ano);
        return nova;
    }

    /**
     * Method that asks the user to enter a value, if it is integer it returns
     * that value.
     *
     * @return value
     */
    public int scannerDatas() {
        int valor = 1;
        int valor2 = 1;
        Scanner scDados = new Scanner(System.in);
        if (scDados.hasNextInt()) {
            valor = scDados.nextInt();
        } else {
            valor2 = scannerDatas();
            return valor2;
        }
        return valor;
    }

    /**
     * Method that takes as parameter the current date, checks using the method
     * verificaData if it is less than the current date. If it's lower, it asks
     * for a new date, otherwise returns the new date
     *
     * @param dataHoje current date
     * @return new date
     */
    public Data mudaData(Data dataHoje) {
        Data nova = novaData();
        if (nova.verificaData(nova, dataHoje) == 1) {
            System.out.println("Não pode voltar atrás no tempo");
            Data nova2 = mudaData(dataHoje);
            return nova2;
        }

        return nova;
    }

    @Override
    public String toString() {
        return getDia() + "/" + getMes() + "/" + getAno();
    }
}
