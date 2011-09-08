package br.com.wbotelhos.spring.batch.repository;

import br.com.wbotelhos.spring.batch.model.Pagamento;

public interface PagamentoRepository {

	void save(final Pagamento pagamento);

}