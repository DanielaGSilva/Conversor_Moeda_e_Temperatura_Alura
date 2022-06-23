import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Moeda {

	public static double ObterValorDeMoeda(String nomeDaMoedaBase) {
		
		String inputValue;
		
		
		String testeDouble = "(\\d*\\,?\\d+)|(\\d*\\.?\\d+)";
		
		do { 
			inputValue = JOptionPane.showInputDialog("Por favor insira o valor em " + nomeDaMoedaBase);
			if (!inputValue.matches(testeDouble)) {
				JOptionPane.showMessageDialog(null, "Precisa fornecer um número para ser computado", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while (!inputValue.matches(testeDouble));
		
		if (!(inputValue.indexOf(',') == -1)) {
			inputValue = inputValue.replace(',', '.');
		}
		
		return Double.parseDouble(inputValue);
		
	}
	
	
	public static String[] codigoDasMoedas() {
		
		String[] nomesDasMoedas = Moeda.nomeDasMoedas();
				
				
		Object moedaOrigem = JOptionPane.showInputDialog(null, "Converter de", "Opção", JOptionPane.INFORMATION_MESSAGE, null, nomesDasMoedas, nomesDasMoedas[0]);
						
		Object moedaDestino;
				
		do {
			moedaDestino = JOptionPane.showInputDialog(null, "Converter para", "Opção", JOptionPane.INFORMATION_MESSAGE, null, nomesDasMoedas, nomesDasMoedas[0]);
			if (moedaDestino == moedaOrigem) {
				JOptionPane.showMessageDialog(null, "Precisa escolher moedas diferentes", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} while (moedaOrigem == moedaDestino);
				
			
		Map<String, String> moedasSimbolos = Moeda.ForneceOpcoesMoeda();
				
		String simboloOrigem = moedasSimbolos.get(moedaOrigem);
		String simboloDestino = moedasSimbolos.get(moedaDestino);
					
				
		String codigoParaConversao = simboloOrigem + "_" + simboloDestino;
				
		return new String[] {codigoParaConversao, String.valueOf(moedaOrigem)};
	}
	
	
	
	public static String[] nomeDasMoedas() {
		return new String[]{"Real Brasileiro", "Dólar Americano", "Dólar Canadense", "Euro", "Libra Esterlina", "Ienes Japonês", "Rand da Africa do Sul", "Peso Méxicano", "Peso Argentino", "Peso Chileno", "Renminbi Chinês"};
	}
	
	public static String[] codigoDeTodasAsMoedas() {
		return new String[]{"BRL", "USD", "CAD", "EUR", "GBP", "JPY", "ZAR", "MXN", "ARS", "CLP", "CNY"};
	}
	
	
	public static Map<String, String> ForneceOpcoesMoeda() {
		
		
		String[] nomeDasMoedas = nomeDasMoedas();
		String[] codigoDeTodasAsMoedas = codigoDeTodasAsMoedas();
		
		
		Map<String, String> paisesMoedas = new HashMap<String, String>();
		
		for (int i = 0; i < nomeDasMoedas.length; i++) {			
			paisesMoedas.put(nomeDasMoedas[i], new String(codigoDeTodasAsMoedas[i]));
		}
		
		return paisesMoedas;
		
	}
	
	public static String ValorDeConversao(String moedas) {
		
		String APIKEY = "5a7206d06ab76ecdd3a5";	
		String urlCountries = "https://free.currconv.com/api/v7/convert?q=" + moedas + "&compact=ultra&apiKey=" + APIKEY;

		
		String output = getUrlContents(urlCountries);
		return output;
		
	}
	
	private static String getUrlContents(String theURL) {
		
		StringBuilder content = new StringBuilder();
		
		try {
			URL url = new URL(theURL);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
		
	}
	
}
