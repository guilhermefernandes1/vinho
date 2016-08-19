package com.algaworks.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.wine.model.Vinho;
import com.algaworks.wine.repository.Vinhos;
import com.algaworks.wine.storage.FotoStorageS3;

@Service
public class CadastraVinhoService {

	@Autowired
	private Vinhos vinhoRepo;
	
	@Autowired
	private FotoStorageS3 fotoSorageS3;
	
	public void save(Vinho vinho){
		this.vinhoRepo.save(vinho);
	}
	
	public String salvarFoto(Long codigo, MultipartFile foto) {
		Vinho vinho = this.vinhoRepo.findOne(codigo);
		String nomeFoto = this.fotoSorageS3.salvar(foto);
		vinho.setFoto(nomeFoto);
		this.vinhoRepo.save(vinho);	
		
		return this.fotoSorageS3.getUrl(nomeFoto);
	}
}
