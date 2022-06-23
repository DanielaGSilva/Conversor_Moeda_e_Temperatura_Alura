
import javax.swing.JOptionPane;

public class Converter {

	public static void main(String[] args) {
		
		Object[] itens = {"Conversor de Moeda", "Conversor de Temperatura"};
		Object selectedValue = JOptionPane.showInputDialog(null, "Escolha um item", "Opção", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
		String opcao = String.valueOf(selectedValue);
		
		switch (opcao) {
				case "Conversor de Moeda":
				
				String[] codigoParaConversao = Moeda.codigoDasMoedas();
				
				
				String codigoMoedaBase = codigoParaConversao[0].substring(4, 7);
				
				
				Double valorMoedaAtual = Moeda.ObterValorDeMoeda(codigoParaConversao[1]);				
				 
				String output = Moeda.ValorDeConversao(codigoParaConversao[0]);
				int positionOfSemiColon = output.indexOf(":")+1;
				int positionOfClosingBracket = output.indexOf("}");
				String rawValueString = output.substring(positionOfSemiColon, positionOfClosingBracket);
				Double convertedValue = Double.parseDouble(rawValueString);
				
				
				Double valorConvertido = convertedValue * valorMoedaAtual;
				String valorConvertidoFormatdo = String.format("%.2f", valorConvertido);
				String mensagem = "Valor convertido: " + codigoMoedaBase + " " + valorConvertidoFormatdo;
				JOptionPane.showMessageDialog(null, mensagem);
				break;
				
				case "Conversor de Temperatura":
			
				String[] baseDestino = Temperatura.temperaturaBaseEDestino();
				String dePara = baseDestino[0] + "_" + baseDestino[1];
				
				double temperaturabase = Temperatura.ObterValorDaTemperatura(baseDestino[0]);
				
				double resultado = Temperatura.conversor(dePara, temperaturabase);
				String resposta = "Valor convertido: " + String.format("%.2f", resultado);
				JOptionPane.showMessageDialog(null, resposta);
				break;
		}
		
		int continuar = JOptionPane.showConfirmDialog(null,"Deseja continuar?","Escolha um",JOptionPane.YES_NO_CANCEL_OPTION);

		if (continuar == 0) {
			main(args);
		} else if (continuar == 1) {
			JOptionPane.showMessageDialog(null, "Programa finalizado");
		} else {
			JOptionPane.showMessageDialog(null, "Programa concluído");
		}
	}

}
