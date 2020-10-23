package br.com.loterias.infraestrutura.loteria.timemania;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.loterias.aplicacao.loteria.EnumTipoDeLoteria;
import br.com.loterias.aplicacao.loteria.MontaResultado;
import br.com.loterias.aplicacao.loteria.PreenchedorDeCampo;
import br.com.loterias.dominio.loteria.Loteria;

@Component("timemania")
@RequestScope
public class MontaResultadoTimemania implements MontaResultado<Loteria> {
	
	private List<Integer> numeros = new ArrayList<Integer>();
	private String numeroDeGanhadores;
	private String numeroDoConcurso;
	private String dataDoUltimoSorteio;
	
	private int contadorParaPegarNumeroDoConcurso = 0;
	
	@Autowired
	@Qualifier("preencheNumeros")
	PreenchedorDeCampo preenchedorDeCampo;
	
	@Override
	public boolean executa(String linhaParaSerAnalisada) {
		return preenchedorDeCampo.continuaPreenchendo(linhaParaSerAnalisada, EnumTipoDeLoteria.TIMEMANIA, this, contadorParaPegarNumeroDoConcurso);
	}
	
	@Override
	public Loteria objetoMontado() {
		return new Loteria(EnumTipoDeLoteria.TIMEMANIA, this.numeros, this.numeroDeGanhadores, this.numeroDoConcurso, this.dataDoUltimoSorteio);
	}

}
