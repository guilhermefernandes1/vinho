package com.algaworks.wine.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.wine.model.Vinho;
import com.algaworks.wine.repository.Vinhos;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class CadastraVinhoService {

	@Autowired
	private Vinhos vinhoRepo;
	
	@Autowired
	private AmazonS3 s3Client;
	
	public void save(Vinho vinho){
		this.vinhoRepo.save(vinho);
	}
	
	public String salvarFoto(Long codigo, MultipartFile foto) {
		String nomeFoto = foto.getOriginalFilename();
		Vinho vinho = this.vinhoRepo.findOne(codigo);
		vinho.setFoto(nomeFoto);
		this.vinhoRepo.save(vinho);
		
		try{
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(foto.getContentType());
		metadata.setContentLength(foto.getSize());
		this.s3Client.putObject("wine", nomeFoto, foto.getInputStream(), metadata);
		}catch (AmazonClientException | IOException e) {
			throw new RuntimeException("Erro salvando arquivo no s3.", e);
		}
		
		return nomeFoto;
	}
}
