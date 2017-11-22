package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadLOG {
	
	public static void readFile(File f) {
		boolean hamFileValidation = false;
		boolean linhaComParametrosAMais=false;
		int contadorHam =0;
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
				
				//Adicionar o nome de todas as linhas do ficheiro "ham.log" à lista "nameList"
				nameList.add(firstFieldSplit[2]);
				
				//Verificar número de parametros da primeira coluna (firstFieldSplit[])
				if (firstFieldSplit.length>4){
					linhaComParametrosAMais=true;
					System.out.println("Linha: '"+ contadorLinhas+ "' com parametros a mais");
				}
				
			}
			
			//Verificar se o nome do email está correto (ham)
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
				hamFileValidation=true;
			}
			
			System.out.println(hamFileValidation);

		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		
	}

	public static void main(String[] args) {
		readFile(new File("Ficheiros/ham.log"));
	}

}
