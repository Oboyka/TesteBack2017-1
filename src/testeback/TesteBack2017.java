/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author cil.6000001126
 */
public class TesteBack2017 {

    public static void main(String[] args) {
        // a nossa lista de clientes
        ArrayList<tbCustomerAccountWrapper> clientes = new ArrayList<>();
        /* uma série de chamados para criar a nossa lista de clientes; no momento,
           elas não estão no banco de dados
        
           nota: estou inserindo nomes e números aleatórios + 1 piada
        */
        tbCustomerAccountWrapper cliente = new tbCustomerAccountWrapper(1512, "04936549811", "Jonathan Welford Hudson", true, 1500.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(1512, "04936549811", "Jonathan Welford Hudson", true, 1500.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(2647, "13546884541", "José Maria Cristo", true, 245.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(2991, "16544821354", "Marcos Caetano", true, 690.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(1641, "26978421365", "Luciana Miklos Fernandes", true, 994.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(78, "11654812421", "Marcelo Ribeiro Filho", true, 722.00);
        clientes.add(cliente);
        cliente = new tbCustomerAccountWrapper(1689, "22548215422", "Joesley Batista", true, 3223546.00);
        clientes.add(cliente);
        
        // agora inserimos os dados no BD
        inserirClientes(clientes);
        
        /* este chamado é para testar se os dados foram inseridos no BD corretamente,
           e é com eles que vamos trabalhar
        */
        clientes = getClientes();
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("Média dos saldos de clientes ente #1500 e #2700 maiores que $560.00: " + df.format(getMediaValorClientes(clientes)));
        
        Collections.sort(clientes, new CustomComparator());
        
        System.out.println("Clientes utilizados no teste: ");
        
        for (tbCustomerAccountWrapper client: clientes){
            System.out.println("Nome: " + client.getNome() + " | CPF/CNPJ: " + client.getCpf_cnpj() + " | Valor: " + client.getValue());
        }
    }
    

    /*
        inserirClientes insere uma lista de clientes para cadastro no BD de teste
    */
    public static void inserirClientes(ArrayList<tbCustomerAccountWrapper> clientes){
        
        for (tbCustomerAccountWrapper cliente: clientes){
            cadastrarCliente(cliente);
        }
    }
            
    public static ArrayList<tbCustomerAccountWrapper> getClientes(){
        ArrayList<tbCustomerAccountWrapper> clientes = new ArrayList<>();
        tbCustomerAccountWrapper cliente;
        ConexaoBD bd = new ConexaoBD();
        String query = "SELECT * FROM tb_customer_account WHERE (id_customer > 1500) AND (id_customer < 2700) AND (vl_total > 560)";

        try {
            if (bd.getConnection()){
                Statement stm;
                stm = bd.connection.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                     cliente = new tbCustomerAccountWrapper(rs.getInt("id_customer"), rs.getString("cpf_cnpj"), rs.getString("nm_customer"), rs.getBoolean("is_active"), rs.getDouble("vl_total"));
                     clientes.add(cliente);
                }
                stm.close();
                bd.connection.close();
                return clientes;
            }
        } catch (SQLException e){
                System.out.println("Erro ao ler: " + e.getMessage());
        }
                return null;
    }
    
    public static void cadastrarCliente(tbCustomerAccountWrapper cliente){
        try {
            ConexaoBD bd = new ConexaoBD();
            String query = "INSERT INTO tb_customer_account VALUES (?, ?, ?, ?, ?)";
            
            if (bd.getConnection()){
                PreparedStatement prep = bd.connection.prepareStatement(query);
                prep.setInt(1, cliente.getIdCustomer());
                prep.setString(2, cliente.getCpf_cnpj());
                prep.setString(3, cliente.getNome());
                prep.setBoolean(4, cliente.IsActive());
                prep.setDouble(5, cliente.getValue());
                prep.executeUpdate();
                System.out.println(cliente.getNome() + " cadastrado com sucesso");
                prep.close();
                bd.connection.close();
            }
        } catch (SQLException e){
            if (e.getErrorCode() == 1062){
                
            } else {
                System.out.println("Erro ao cadastrar " + cliente.getNome() + ": " + e.getMessage());
            }
        }
    }
    
    public static double getMediaValorClientes(ArrayList<tbCustomerAccountWrapper> clientes){
        double val = 0;
        int count = 0;
        
        for (tbCustomerAccountWrapper cliente: clientes){
            count++;
            val += cliente.getValue();
        }
        val = (val/count);
        return val;
    }
}
