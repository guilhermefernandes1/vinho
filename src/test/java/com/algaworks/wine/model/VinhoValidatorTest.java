package com.algaworks.wine.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class VinhoValidatorTest {
	
	private Vinho vinho;
	private Validator validator;
	
	@Before
	public void setUp(){		
		this.vinho = new Vinho();
		vinho.setNome("Cabernet");
		vinho.setSafra(2013);
		vinho.setTipo(TipoVinho.TINTO);
		vinho.setValor(new BigDecimal(20.0));
		vinho.setVolume(700);
		this.validator = createValidator();
	}
	
	private Validator createValidator(){
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	private Set<ConstraintViolation<Vinho>> createConstraintValidator(){
		return validator.validate(this.vinho);
	}

	@Test
	public void shouldNotValidateWhenNomeIsEmpty(){
		this.vinho.setNome(null);		
		Set<ConstraintViolation<Vinho>> constraintViolations = createConstraintValidator();
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vinho> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nome");
		assertThat(violation.getMessage()).isEqualTo("O nome é obrigatório.");
		this.vinho.setNome("Cabernet");
	}
	
	@Test
	public void shouldNotValidateWhenSafraIsMinorThan(){
		this.vinho.setSafra(0);		
		Set<ConstraintViolation<Vinho>> constraintViolations = createConstraintValidator();
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vinho> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("safra");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
		this.vinho.setSafra(2013);
	}
	
	@Test
	public void shouldNotValidateWhenTipoIsNull(){
		this.vinho.setTipo(null);		
		Set<ConstraintViolation<Vinho>> constraintViolations = createConstraintValidator();
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vinho> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("tipo");
		assertThat(violation.getMessage()).isEqualTo("Tipo é obrigatório.");
		this.vinho.setTipo(TipoVinho.BRANCO);
	}
	
	@Test
	public void shouldNotValidateWhenValorIsNull(){
		this.vinho.setValor(null);		
		Set<ConstraintViolation<Vinho>> constraintViolations = createConstraintValidator();
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vinho> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("valor");
		assertThat(violation.getMessage()).isEqualTo("Valor é obrigatório.");
		this.vinho.setValor(new BigDecimal(20.0));
	}
	
	@Test
	public void shouldNotValidateWhenVolumeIsMinorThan100(){
		this.vinho.setVolume(99);		
		Set<ConstraintViolation<Vinho>> constraintViolations = createConstraintValidator();
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vinho> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("volume");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 100");
		this.vinho.setVolume(750);
	}
}
