package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import antiSpamFilter.AntiSpamFilterStyles.AOptionPane;

public class ReadLOG {
	static boolean validated = false;
	static boolean linhaComParametrosAMais=false;
	
	static String []hamFileFields = null;
	static String []firstFieldSplit = null;
	static ArrayList<String> typeList = new ArrayList<String>();
	
	public static boolean verificarTipoEmail(File f){
		if(f.getName().equals("ham.log")){
			//Verificar se o tipo de cada email do ficheiro ham.log está correto
			for(int i=0; i<typeList.size();i++){
				if(typeList.get(i).equals("_ham_")){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(f.getName().equals("spam.log")){
			//Verificar se o tipo de cada email do ficheiro spam.log está correto
			for(int j=0; j<typeList.size();j++){
				if(typeList.get(j).equals("_spam_")){
					return true;
				}
				else{
					return false;
				}
			}
		}
		//Ficheiro não interessante para o software AntiSpam
		return false;
	}
	
	public static boolean readFile(File f) {
		
		try {
			Scanner s = new Scanner(f);
			
			while (s.hasNextLine()) {
				
				String nextLine = s.nextLine();
				
				//Divisão das diversas colunas do ficheiro
				hamFileFields= nextLine.split("\t");
				
				//Divisão da primeira coluna do ficheiro em /
				firstFieldSplit= hamFileFields[0].split("/");
				
				//Adicionar o nome de todas as linhas do ficheiro "ham.log"ou"spam.log à lista "nameList"
				typeList.add(firstFieldSplit[2]);
				
				if(!verificarTipoEmail(f)){
					s.close();
					return false;
				}
				
				//Verificar número de parametros da primeira coluna (firstFieldSplit[])
				if (firstFieldSplit.length != 4){
					s.close();
					return false;
				}				
			}
			
			s.close();
			
		} catch (FileNotFoundException e) {
			AOptionPane.showMessageDialog(
					null, "Could not read the file", "Error", AOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

}
