package br.senai.sc.edu.aula_spring_data;

import br.senai.sc.edu.aula_spring_data.dominio.Evento;
import br.senai.sc.edu.aula_spring_data.dto.ClienteEventoDTO;
import br.senai.sc.edu.aula_spring_data.repository.EventoRepository;
import br.senai.sc.edu.aula_spring_data.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@SpringBootApplication
public class AulaSpringDataApplication implements CommandLineRunner {

	@Autowired
	EventoRepository eventoRepository;

	@Autowired
	InscricaoRepository inscricaoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AulaSpringDataApplication.class, args);
	}

	@Override
	public void run(String...args){
		System.out.println("Hello");

		//List<Evento> list = eventoRepository.findAll();

		//List<Evento> list = eventoRepository.nomeContainingOrderByNomeDesc("in");

		//Evento evento = new Evento("DEVinHouse");

//		Evento evento = eventoRepository.codigo(10L);
//
//		evento.setNome("DEVinHouse Test");
//
//		eventoRepository.save(evento);

//		Evento e1 = new Evento("Evento 1");
//		Evento e2 = new Evento("Evento 2");
//		Evento e3 = new Evento("Evento 3");
//		Evento e4 = new Evento("Evento 4");
//		Evento e5 = new Evento("Evento 5");
//
//		eventoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));

		//Page<Evento> list = eventoRepository.findAll(PageRequest.of(0, 3));

		//List<Evento> list = eventoRepository.buscarPorNome("%in%");

		//	int count = eventoRepository.countEvento();

		//	System.out.println("********************************************");
//
//		System.out.println(count);
//
//
//		System.out.println("****************** Página 1 **************************");
//		list.forEach(System.out::println);
//
//		System.out.println("****************** Página 2 **************************");
//		list = eventoRepository.findAll(PageRequest.of(1, 3));
//		list.forEach(System.out::println);
//
//		System.out.println("****************** Página 3 **************************");
//		list = eventoRepository.findAll(PageRequest.of(2, 3));
//		list.forEach(System.out::println);
//
//		System.out.println("****************** Página 4 **************************");
//		list = eventoRepository.findAll(PageRequest.of(3, 3));
//		list.forEach(System.out::println);


		List<ClienteEventoDTO> list = inscricaoRepository.buscarClienteEventoNativo();

		list.forEach(System.out::println);
	}
}
