package br.com.altran.desafio.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.altran.desafio.entity.Pessoa;
import br.com.altran.desafio.exception.BusinessException;
import br.com.altran.desafio.repository.PessoaRepository;

/**
 * The Class PessoaService.
 */
@Service
public class PessoaService {
	
	/** The pessoa repository. */
	@Autowired
	private PessoaRepository pessoaRepository;
		
	/**
	 * Find all.
	 *
	 * @param specification the specification
	 * @param pageable the pageable
	 * @return the list
	 */
	public List<Pessoa> findAll(Specification<Pessoa> specification, Pageable pageable) {
		return pessoaRepository.findAll(specification, pageable).getContent();
	}

	/**
	 * Save.
	 *
	 * @param pessoa the pessoa
	 * @return the pessoa
	 */
	public Pessoa save(@Valid Pessoa pessoa) {
		if (pessoa.getId() != null) {
			throw new BusinessException("A pessoa cadastrada possuí identificador preenchido.");
		}
		Pessoa pessoaComCpf = pessoaRepository.findByCpf(pessoa.getCpf());
		if (pessoaComCpf != null) {
			throw new BusinessException("O CPF da pessoa cadastrada já existe na base de dados.");
		}
		return pessoaRepository.save(pessoa);
	}

	/**
	 * Edits the.
	 *
	 * @param id the id
	 * @param pessoa the pessoa
	 * @return the pessoa
	 */
	public Pessoa edit(Long id, @Valid Pessoa pessoa) {
		Optional<Pessoa> pessoaBase = pessoaRepository.findById(id);
		if (pessoaBase.isPresent()) {
			pessoa.setId(id);
			return pessoaRepository.save(pessoa);
		} else {
			throw new BusinessException(MessageFormat.format("Não existe pessoa na base de dados com o id informado: {0}.", id));
		}
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Long id) {
		Optional<Pessoa> pessoaBase = pessoaRepository.findById(id);
		if (pessoaBase.isPresent()) {
			pessoaRepository.deleteById(id);
		} else {
			throw new BusinessException(MessageFormat.format("Não existe pessoa na base de dados com o id informado: {0}.", id));
		}
		
	}
	
}
