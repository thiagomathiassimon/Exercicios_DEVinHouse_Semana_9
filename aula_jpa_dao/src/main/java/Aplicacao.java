import br.com.devinhose.dao.ClienteDAO;
import br.com.devinhose.dao.EventoDAO;
import br.com.devinhose.dao.InscricaoDAO;
import br.com.devinhose.dominio.Cliente;
import br.com.devinhose.dominio.Evento;
import br.com.devinhose.dominio.Inscricao;
import br.com.devinhose.dto.ClienteDTO;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.concurrent.Callable;

import static br.com.devinhose.dominio.JPAUtil.entityManagerFactory;

public class Aplicacao {

    public static void main(String[] args) {

        EventoDAO eventoDAO = new EventoDAO();
//
//        Evento evento = new Evento("DEVinHouse");
//
//        //eventoDAO.inserir(evento);
//
////        evento.setCodigo(13L);
////        evento.setNome("DevInHouse");
////
////        //eventoDAO.alterar(evento);
////
////        evento.setCodigo(16L);
////        eventoDAO.remover(evento);
//
//        evento = eventoDAO.find(11L);

        ClienteDAO clienteDAO = new ClienteDAO();
//
//        Cliente cliente = new Cliente();
//        cliente.setNome("Maria");
//        cliente.setCpf("456");
//        cliente.setEmail("m@gmail.com");
//        cliente.setRg("003");
//        cliente.setSenha("password");
//        cliente.setTelefone("002");
//
//        clienteDAO.inserir(cliente);


        InscricaoDAO inscricaoDAO = new InscricaoDAO();

//        Evento evento;
//        Cliente cliente;
//
//        evento = eventoDAO.find(13L);
//        cliente = clienteDAO.find(3L);
//
//        Inscricao inscricao = new Inscricao();
//        inscricao.setCliente(cliente);
//        inscricao.setEvento(evento);
//        inscricao.setValor(1000.00);
//        inscricao.setParcela(10);
//        inscricao.setObservacao("Nenhuma");
//
//        inscricaoDAO.inserir(inscricao);


        List<ClienteDTO> list = inscricaoDAO.findAll();

        list.forEach(System.out::println);

   //     System.out.println(cliente);

    }

}
