package br.com.altran.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.altran.desafio.dto.PessoaDTO;
import br.com.altran.desafio.entity.Pessoa;
import br.com.altran.desafio.service.PessoaService;
import br.com.altran.desafio.specification.PessoaSpecification;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<PessoaDTO> findAll(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cpf", required = false) String cpf,
			@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
			@RequestParam(value = "tamanho", required = false, defaultValue = "10") Integer tamanho,
			@RequestParam(value = "propriedadeParaOrdenar", required = false, defaultValue = "nome") String propriedadeParaOrdenar,
			@RequestParam(value = "direcao", required = false, defaultValue = "ASC") Sort.Direction direcao) {
		return pessoaService.findAll(new PessoaSpecification(new Pessoa(id, nome, cpf)), PageRequest.of(pagina, tamanho, direcao, propriedadeParaOrdenar)).stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public PessoaDTO save(@RequestBody PessoaDTO pessoaDTO) {
		return convertToDto(pessoaService.save(convertToEntity(pessoaDTO)));
	}
	
	@PutMapping("/{id}")
	public PessoaDTO edit(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
		return convertToDto(pessoaService.edit(id, convertToEntity(pessoaDTO)));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		pessoaService.deleteById(id);
	}
	
	private PessoaDTO convertToDto(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaDTO.class);
	}

	private Pessoa convertToEntity(PessoaDTO pessoaDTO) {
		return modelMapper.map(pessoaDTO, Pessoa.class);
	}
	
}
