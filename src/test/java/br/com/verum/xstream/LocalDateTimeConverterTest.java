package br.com.verum.xstream;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.verum.modelo.Negociacao;

public class LocalDateTimeConverterTest {

	@Test
	public void deveRetornarUmXmlComAhDataCorreta() {
		
		LocalDateTime data = LocalDateTime.of(2020, 8, 15, 20, 00);
		
		Negociacao negociacao1 = new Negociacao(10.0, 4 , data);
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("negociacao", Negociacao.class);
		xstream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		String xmlConvertido = xstream.toXML(negociacao1);
		
		String xmlEsperado = "<negociacao>\n" + 
							 "  <preco>10.0</preco>\n" + 
							 "  <quantidade>4</quantidade>\n" + 
							 "  <data>\n" + 
							 "    <time>1597532400000</time>\n" + 
							 "    <timezone>America/Sao_Paulo</timezone>\n" + 
							 "  </data>\n" + 
							 "</negociacao>";
		
		assertEquals(xmlEsperado, xmlConvertido);
		
	}
	
	@Test
	public void deveConverterUmXmlParaUmanegociacaoCorreta() {
		
		LocalDateTime data = LocalDateTime.of(2020, 8, 15, 20, 00);
		
		String xml = "<negociacao>\n" + 
				 	 "  <preco>10.0</preco>\n" + 
				 	 "  <quantidade>4</quantidade>\n" + 
				 	 "  <data>\n" + 
				 	 "    <time>1597532400000</time>\n" + 
				 	 "    <timezone>America/Sao_Paulo</timezone>\n" + 
				 	 "  </data>\n" + 
				 	 "</negociacao>";
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("negociacao", Negociacao.class);
		xstream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		
		Negociacao negociacaoGerada = (Negociacao) xstream.fromXML(xml);
		Negociacao negociacaoEsperada = new Negociacao(10.0, 4, data);
		
		assertEquals(negociacaoEsperada, negociacaoGerada);
		
	}

}
