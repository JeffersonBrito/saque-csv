package br.com.jeff.avancados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Saque {
    public static void main(String[] args) throws IOException {

        buscarID();
    }




    public static void buscarID() throws IOException {
        List<String> contas = Files.readAllLines(Paths.get("src/br/com/jeff/avancados/contas.csv"));
        System.out.println("Digite seu ID");
        StringBuilder pessoas = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String verifyID = scanner.nextLine();
        boolean idCorreto = false;
        for (String conta : contas){
            String[] partes = conta.split(",");
            if (verifyID.equals(partes[0])) {
                if (!partes[0].equals("id")) {
                    if (!partes[4].equals("funds")) {
                        int saldo = Integer.parseInt(partes[4]);
                        idCorreto = true;
                        System.out.println("Seu ID " + partes[0] + ", seu Saldo: " + partes[4]);
                        System.out.println("Digite quanto deseja sacar: ");
                        Integer saqueID = scanner.nextInt();
                        if (saqueID < saldo){
                            int saldoSaque = saldo;
                            int saque = saqueID;
                            int result = (saldoSaque - saque);
                            Integer finalSaldo = (result);
                            String oloco = finalSaldo.toString();
                            partes[4] = oloco;
                            System.out.println("Seu saque foi de: " + saque);
                            System.out.println("Sua seu saldo atual é: " + result);
                            String pessoa = String.format("%s, %s %s, %s, %s, ", partes[0], partes[1], partes[2], partes[3], partes[4]);
                            pessoas.append(pessoa + "\n");
                        }
                        else {
                            System.out.println("Saque invalido");
                        }
                    }
                }
            }else{
                pessoas.append(conta + "\n");
            }
            gravarArquivo(pessoas);
        }
        if (idCorreto == false){
            System.out.println("ID Invalido");
        }
    }


    public static void gravarArquivo(StringBuilder pessoas){
        Path outputPath = Paths.get("src/br/com/jeff/avancados/contas.txt");

        try {
            Files.write(outputPath, pessoas.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo");
        }

    }
}
