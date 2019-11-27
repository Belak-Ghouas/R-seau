import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurator {
	
	
	private final String MSG_BIENVENUE="\t **** BIENVENUE sur le GestionCoin **** ,"	+ " Type :   1  to connect   ****   2  to sign ,  ";
	private final String TABLEAU_DE_BORD="\t *** Type *** ,"+" 1 >> Post" +"  2 >>Annonces "+" 3 >>Delete" +" 4 >>Quit "+"5 >>Conect "+"6 >>Accept"+", " ;
	private final String properties="ressources/config.properties";
	private final String ERROR_CONNECT="\t   CE COMPTE N'EXISTE PAS !!!!! ,";
	private final String ERROR_SIGN="\t  CE PSEUDO EXISTE DEJA!!!!" ;
	private final String ERROR_COMMAND="\t LE NUMERO ENTREZ NE CORRESPAND PAS !!! ,";
	private final String PSEUDO=">> Entrez pseudo : ";
	
	

	private Properties prop = new Properties();
	private InputStream input ;
	private OutputStream output;
	
public  Configurator() throws IOException {
		
		input = new FileInputStream(properties) ;
		output = new FileOutputStream(properties) ;
		
		
				
		 prop.setProperty("MSG_BIENVENUE",MSG_BIENVENUE);
         prop.setProperty("TABLEAU_DE_BORD",TABLEAU_DE_BORD);
         prop.setProperty("ERROR_CONNECT",ERROR_CONNECT);
         prop.setProperty("ERROR_SIGN",ERROR_SIGN);
         prop.setProperty("ERROR_COMMAND",ERROR_COMMAND);
         prop.setProperty("PSEUDO",PSEUDO);
         // save properties to project root folder
         prop.store(output, null);
        
	}

public String getMSG_BIENVENUE() throws IOException {
	prop.load(input);
	
	return prop.getProperty("MSG_BIENVENUE");
}


public String getTABLEAU_DE_BORD() throws IOException {
	 prop.load(input);
	return prop.getProperty("TABLEAU_DE_BORD");	
}

public String getERROR_CONNECT() throws IOException {
	prop.load(input);
	return prop.getProperty("ERROR_CONNECT");	
}

public String getERROR_SIGN() throws IOException {
	prop.load(input);
	return prop.getProperty("ERROR_SIGN");	
}

public String getERROR_COMMAND() throws IOException {
	prop.load(input);
	return prop.getProperty("ERROR_COMMAND");	
}

public String getPSEUDO() throws IOException {
	prop.load(input);
	return prop.getProperty("PSEUDO");	
}
public void setPSEUDO(String PSEUDO) throws IOException {
	
	
	 prop.setProperty("PSEUDO",PSEUDO);
	 prop.store(output, null);
}
public void setMSG_BIENVENUE(String MSG_BIENVENUE) throws IOException {
	
	
	 prop.setProperty("MSG_BIENVENUE",MSG_BIENVENUE);
	 prop.store(output, null);
}


public void setTABLEAU_DE_BORD(String TABLEAU_DE_BORD) throws IOException {

	prop.setProperty("TABLEAU_DE_BORD",TABLEAU_DE_BORD  );
	prop.store(output, null);
}

public void setERROR_SIGN(String ERROR_SIGN) throws IOException {
	
	prop.setProperty("ERROR_SIGN",ERROR_SIGN  );
	prop.store(output, null);
}


public void setERROR_CONNECT(String ERROR_CONNECT) throws IOException {
	
	prop.setProperty("ERROR_CONNECT",ERROR_CONNECT );
	prop.store(output, null);
}








}
