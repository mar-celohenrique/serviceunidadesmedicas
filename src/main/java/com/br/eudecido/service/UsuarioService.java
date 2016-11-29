package com.br.eudecido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.eudecido.models.Usuario;
import com.br.eudecido.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public String salvar(Usuario usuario) {
		if (this.buscarPorCPF(usuario.getCpf()) != null)
			return "CPF já está em uso";
		if (this.buscarPorEmail(usuario.getEmail()) != null)
			return "Email já está em uso";

		repository.save(usuario);
		return "Usuario cadastrado!";
	}

	public void excluir(Integer id) {
		repository.delete(id);
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public Usuario buscarPorId(Integer id) {
		return repository.findById(id);
	}

	public Usuario buscarPorCPF(String cpf) {
		return repository.findByCpf(cpf);
	}

	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}

	public String atualizar(Usuario usuario) {
		Usuario user = this.buscarPorId(usuario.getId());

		if ((user.getCpf().equals(usuario.getCpf())) || (this.buscarPorCPF(usuario.getCpf()) != null))
			return "CPF já está em uso";
		if ((user.getEmail().equals(usuario.getEmail())) || (this.buscarPorEmail(usuario.getEmail()) != null))
			return "Email já está em uso";

		repository.save(usuario);
		return "Atualizado";
	}

	public Usuario logar(String email, String senha) {
		Usuario usuario = repository.findByEmail(email);
		return usuario;
	}

	public boolean logadoComSucesso(Usuario usuario) {
		if (logar(usuario.getEmail(), usuario.getSenha()) != null) {
			return true;
		}
		return false;
	}

}
