package br.com.wbotelhos.spring.batch.mapper;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import br.com.wbotelhos.spring.batch.model.Pagamento;

@Component("pagamentoMapper")
public class PagamentoMapper implements FieldSetMapper<Pagamento> {
	private final static String DATE_PATTERN = "dd/MM/yy";
	private final static String MONEY_PATTERN = "R$###,###.###";

	public Pagamento mapFieldSet(FieldSet fieldSet) {
		Pagamento pagamento = new Pagamento();

		int cursor = 0;

		pagamento.setDepositante(fieldSet.readString(cursor++));
		pagamento.setCodigo(fieldSet.readInt(cursor++));
		pagamento.setTipo(fieldSet.readString(cursor++));
		pagamento.setData(fieldSet.readDate(cursor++, DATE_PATTERN));
		
		try {
			DecimalFormat format = new DecimalFormat(MONEY_PATTERN);
			Number valor = format.parse(fieldSet.readString(cursor++));
			
			pagamento.setValor(valor.doubleValue());
		} catch (ParseException e) {
			pagamento.setValor(0.0);
		}

		pagamento.setComentario(fieldSet.readString(cursor++));

		return pagamento;
	}

}