package br.com.verum.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import br.com.verum.modelo.Negociacao;

public class LeitorXmlTest {

	@Test
	public void carregaXmlComApenasUmaNegociacao() {
		
		LocalDateTime data = LocalDateTime.of(2020, 8, 15, 20, 00);
		
		String xml ="<list>\n" + 
					"  <negociacao>\n" + 
					"    <preco>10.0</preco>\n" + 
					"    <quantidade>4</quantidade>\n" + 
					"    <data>\n" + 
					"      <time>1597532400000</time>\n" + 
					"      <timezone>America/Sao_Paulo</timezone>\n" + 
					"    </data>\n" + 
					"  </negociacao>" +
					"</list>";
		LeitorXml leitor = new LeitorXml();
		
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(inputStream);
		
		Negociacao negociacao = new Negociacao(10.0, 4, data);
		
		assertEquals(1, negociacoes.size());
		assertEquals(negociacao, negociacoes.get(0));
		
	}

}
