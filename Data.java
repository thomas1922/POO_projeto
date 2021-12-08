package supermercado;

import java.io.Serializable;


public class Data implements Serializable{

    private int dia;
    private int mes;
    private int ano;
    
    public Data(){
        
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

    public int comparaDatas(Data dataInicio, Data dataFim, Data dataHoje) {
        if (dataHoje.ano >= dataInicio.ano && dataHoje.ano <= dataFim.ano) {
            if (dataHoje.mes >= dataInicio.mes && dataHoje.mes <= dataFim.mes) {
                    if (dataHoje.dia >= dataInicio.dia&&dataHoje.dia <= dataFim.dia) {
                        return 1;}
            }else{
                return 0;
            }
        }
        return 0;
    }
    public int verificaData(Data dataHoje,Data dataInicio){
         if (dataHoje.ano <= dataInicio.ano ){
             if (dataHoje.mes <= dataInicio.mes){
                 if (dataHoje.dia < dataInicio.dia){
                     return 1;
                 }
             }
         }
         return 0;
    }
    
    @Override
    public String toString(){
        return getDia()+"/"+getMes()+"/"+getAno();
    }
}
