package com.algaworks.wine.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.algaworks.wine.WineApplication;
import com.algaworks.wine.model.TipoVinho;
import com.algaworks.wine.model.Vinho;
//import com.algaworks.wine.repository.Vinhos;

//import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WineApplication.class)
@WebAppConfiguration
public class VinhosControllerTest {

	private static final Long TEST_VINHO_ID = (long) 1;
	
	@Autowired
	private VinhosController vinhosController;
	
//	@Autowired
//	private Vinhos vinhoRep;
	
	private MockMvc mockMvc;
	private Vinho vinho;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.vinhosController).build();
		
		this.vinho = new Vinho();
		this.vinho.setCodigo(TEST_VINHO_ID);
		this.vinho.setNome("Vinho1");
		this.vinho.setSafra(2016);
		this.vinho.setTipo(TipoVinho.BRANCO);
		this.vinho.setValor(new BigDecimal(20.5));
		this.vinho.setVolume(750);		
		
	}
	
	@Test
	public void pesquisaTodosVinhos() throws Exception{
		this.mockMvc.perform(get("/vinhos"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vinhos"))
		.andExpect(view().name("/vinho/ListagemVinhos"));
	}
	
}
