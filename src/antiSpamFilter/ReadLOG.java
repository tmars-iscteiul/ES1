package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadLOG {
	
	public static boolean readFile(File f) {
		boolean validated = false;
		boolean linhaComParametrosAMais=false;
		int contadorHam =0;
		int contadorSpam =0;
		int contadorLinhas=0;
		
		String []hamFileFields = null;
		String []firstFieldSplit = null;
		ArrayList<String> nameList = new ArrayList<String>();
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				contadorLinhas++;
				String nextLine = s.nextLine();
				
				//Divisão das diversas colunas do ficheiro
				hamFileFields= nextLine.split("\t");
				
				//Divisão da primeira coluna do ficheiro em /
				firstFieldSplit= hamFileFields[0].split("/");
				
				//Adicionar o nome de todas as linhas do ficheiro "ham.log"ou"spam.log à lista "nameList"
				nameList.add(firstFieldSplit[2]);
				
				//Verificar número de parametros da primeira coluna (firstFieldSplit[])
				if (firstFieldSplit.length>4){
					linhaComParametrosAMais=true;
					System.out.println("Linha: '"+ contadorLinhas+ "' do ficheiro: "+f.getName()+" ,com parametros a mais");
				}
			}
			
			System.out.println("Ficheiro nome: "+ f.getName());
			
			if(f.getName().equals("ham.log")){
				//Verificar se o nome de cada email do ficheiro ham.log está correto
				for(int i=0; i<nameList.size();i++){
					if(nameList.get(i).equals("_ham_")){
						contadorHam++;
					}
					else{
						System.out.println("Linha: '"+ (i+1)+"' com nome incorreto: "+nameList.get(i)); 
					}
				}
				//Validação do ficheiro ham.log
				if(contadorHam==contadorLinhas && linhaComParametrosAMais==false){
					validated=true;
				}
				System.out.println("Validação do ficheiro ham.log: "+ validated);
				
			}
			if(f.getName().equals("spam.log")){
				//Verificar se o nome de cada email do ficheiro spam.log está correto
				for(int j=0; j<nameList.size();j++){
					if(nameList.get(j).equals("_spam_")){
						contadorSpam++;
					}
					else{
						System.out.println("Linha: '"+ (j+1)+"' com nome incorreto: "+nameList.get(j)); 
					}
				}
				//Validação do ficheiro spam.log
				if(contadorSpam==contadorLinhas && linhaComParametrosAMais==false){
					validated=true;
				}
				System.out.println("Validação do ficheiro spam.log: " + validated);
			}
			else{
				//System.out.println("Ficheiro não interessante para o software AntiSpam");
				//nao sei porque aparece este print quando eu não estou a inserir nenhum ficheiro sem ser o ham ou o spam
			}
			
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		return validated;
		
	}

	/*
	public static void main(String[] args) {
		readFile(new File("Ficheiros/ham.log"));
		readFile(new File("Ficheiros/spam.log"));
	}*/

}
