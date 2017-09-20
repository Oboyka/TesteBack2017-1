/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeback;

import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author cil.6000001126
 */
// classe que serve como armazenador dos elementos da tabela tb_customer_account
public class tbCustomerAccountWrapper {

    int idCustomer;
    String cpf_cnpj;
    String nome;
    boolean isActive;
    double value;
    
    public tbCustomerAccountWrapper(int id, String cpf_cnpj, String nome, boolean isActive, double val){
        this.idCustomer = id;
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
        this.isActive = isActive;
        this.value = val;
    }
    
    public int getIdCustomer() {
        return idCustomer;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean IsActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    protected int compareTo(double compared){
        if (this.value > compared)
            return 1;
        else if (this.value == compared)
            return 0;
        else
            return -1;
    }
    
}
