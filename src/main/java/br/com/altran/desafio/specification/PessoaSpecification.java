package br.com.altran.desafio.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.altran.desafio.entity.Pessoa;

public class PessoaSpecification implements Specification<Pessoa> {

	private static final long serialVersionUID = -938014258312880579L;
	
	private Pessoa filter;

	public PessoaSpecification(Pessoa filter) {
		super();
		this.filter = filter;
	}

	public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicateList = new ArrayList<>();
		Predicate predicate = criteriaBuilder.conjunction();

		if (filter.getId() != null) {
			predicateList.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
		}
		
		if (filter.getNome() != null) {
			predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + filter.getNome().toUpperCase() + "%"));
		}
		
		if (filter.getCpf() != null) {
			predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("cpf")), "%" + filter.getCpf().toUpperCase() + "%"));
		}
		
		if (filter.getDtNascimento() != null) {
			predicateList.add(criteriaBuilder.equal(root.get("dtNascimento"), filter.getDtNascimento()));
		}
		
		if (!predicateList.isEmpty()) {
			predicate.getExpressions().add(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
		}

		return predicate;
	}
}