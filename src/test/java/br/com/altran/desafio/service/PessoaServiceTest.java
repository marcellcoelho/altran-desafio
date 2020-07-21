package br.com.altran.desafio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.altran.desafio.entity.Pessoa;
import br.com.altran.desafio.exception.BusinessException;
import br.com.altran.desafio.repository.PessoaRepository;
import br.com.altran.desafio.specification.PessoaSpecification;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	@Test
	public void findAllSucesso() {
		List<Pessoa> content = new ArrayList<>();
		content.add(new Pessoa(1L, "Marcell", "07911761426"));
		Page<Pessoa> pagePessoa = new PageImpl<Pessoa>(content);
		
		Mockito.when(pessoaRepository.findAll(Mockito.any(PessoaSpecification.class), Mockito.any(PageRequest.class))).thenReturn(pagePessoa);
		
		assertEquals(pagePessoa.getContent(), pessoaService.findAll(new PessoaSpecification(new Pessoa(1L, "Marcell", "07911761426")), PageRequest.of(0, 10, Sort.Direction.ASC, "nomeFantasia")));
	}
	
	@Test
	public void saveSucesso() {
		Pessoa pessoa = new Pessoa("Marcell", "07911761426", LocalDate.of(1990, Month.AUGUST, 7));
		Mockito.when(pessoaRepository.findByCpf(Mockito.any(String.class))).thenReturn(null);
		Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);
		assertEquals(pessoa, pessoaService.save(pessoa));
	}
	
	@Test
	public void savePessoaComIdentificadorPreenchido() {
		Pessoa pessoa = new Pessoa("Marcell", "07911761426", LocalDate.of(1990, Month.AUGUST, 7));
		pessoa.setId(1L);
		Exception exception = assertThrows(BusinessException.class, () -> {
			pessoaService.save(pessoa);
	    });
		assertTrue(exception.getMessage().contains("A pessoa cadastrada possuí identificador preenchido."));
	}
	
	@Test
	public void savePessoaComCpfJaCadastrado() {
		Pessoa pessoa = new Pessoa("Marcell", "07911761426", LocalDate.of(1990, Month.AUGUST, 7));
		Mockito.when(pessoaRepository.findByCpf(Mockito.any(String.class))).thenReturn(pessoa);
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			pessoaService.save(pessoa);
	    });
		assertTrue(exception.getMessage().contains("O CPF da pessoa cadastrada já existe na base de dados."));
	}
	
	@Test
	public void editSucesso() {
		Optional<Pessoa> pessoa = Optional.of(new Pessoa("Marcell", "07911761426", LocalDate.of(1990, Month.AUGUST, 7)));
		Mockito.when(pessoaRepository.findById(Mockito.any(Long.class))).thenReturn(pessoa);
		
		Pessoa pessoaEditada = new Pessoa("Marcell Araújo", "07911761426", LocalDate.of(1990, Month.AUGUST, 7));
		Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoaEditada);
		assertEquals(pessoaEditada, pessoaService.edit(1L, pessoaEditada));
	}
	
	@Test
	public void editNaoExistePessoaNaBaseDeDados() {
		Long id = 1L;
		Mockito.when(pessoaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
		Pessoa pessoaEditada = new Pessoa("Marcell Araújo", "07911761426", LocalDate.of(1990, Month.AUGUST, 7));
		Exception exception = assertThrows(BusinessException.class, () -> {
			pessoaService.edit(id, pessoaEditada);
	    });
		assertTrue(exception.getMessage().contains(MessageFormat.format("Não existe pessoa na base de dados com o id informado: {0}.", id)));
	}
	
	@Test
	public void deleteByIdSucesso() {
		Optional<Pessoa> pessoa = Optional.of(new Pessoa("Marcell", "07911761426", LocalDate.of(1990, Month.AUGUST, 7)));
		Mockito.when(pessoaRepository.findById(Mockito.any(Long.class))).thenReturn(pessoa);
		pessoaService.deleteById(1L);
	}
	
	@Test
	public void deleteByIdSemDadosNaBaseDeDados() {
		Long id = 1L;
		Mockito.when(pessoaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
		Exception exception = assertThrows(BusinessException.class, () -> {
			pessoaService.deleteById(id);
	    });
		assertTrue(exception.getMessage().contains(MessageFormat.format("Não existe pessoa na base de dados com o id informado: {0}.", id)));

	}

}
