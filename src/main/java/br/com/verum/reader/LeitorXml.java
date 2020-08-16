package br.com.verum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.verum.modelo.Negociacao;
import br.com.verum.xstream.LocalDateTimeConverter;

public class LeitorXml {

	public List<Negociacao> carrega(InputStream inputStream){
		
		XStream stream = new XStream(new DomDriver());
		stream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
		stream.alias("negociacao", Negociacao.class);
				
		return (List<Negociacao>) stream.fromXML(inputStream);
	}

}
