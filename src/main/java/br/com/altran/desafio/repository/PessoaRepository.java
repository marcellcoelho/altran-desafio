package br.com.altran.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.altran.desafio.entity.Pessoa;

/**
 * The Interface PessoaRepository.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	/**
	 * Find by cpf.
	 *
	 * @param cpf the cpf
	 * @return the pessoa
	 */
	Pessoa findByCpf(String cpf);

}
