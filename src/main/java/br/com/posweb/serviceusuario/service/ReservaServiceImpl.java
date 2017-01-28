package br.com.posweb.serviceusuario.service;

import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.posweb.serviceusuario.entity.Reserva;
import br.com.posweb.serviceusuario.entity.Usuario;
import br.com.posweb.serviceusuario.repository.ReservaRepository;
import br.com.posweb.serviceusuario.repository.UsuarioRepository;
import br.com.posweb.serviceusuario.util.FuncoesArquivo;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void reservar(Integer codigoReserva, Integer codigoUsuario) throws Exception {
		
		String IP_MQ = FuncoesArquivo.getInstance().getValorProperties("ip_activemq");
		String PORT_MQ = FuncoesArquivo.getInstance().getValorProperties("port_activemq");
		
		Usuario usuario = usuarioRepository.findOne(codigoUsuario);
		
		if(usuario != null){
			Reserva reserva = new Reserva(codigoReserva, usuario);
					
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://"+IP_MQ+":"+PORT_MQ);
			
			Connection connection = null;
			Session session = null;
			try {
				
				connection = factory.createConnection();
				connection.start();
				
				session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		
				Destination queue = session.createQueue("Queue");
				
				MessageProducer producer = session.createProducer(queue);
				
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				ObjectMessage message = session.createObjectMessage(reserva);
				message.setStringProperty("codigoReserva", codigoReserva.toString());
				
				producer.send(message);
				
			} catch (JMSException e) {
				e.printStackTrace();
				throw new Exception("Problemas ao iniciar JMS");
			}finally{
				try {
					if(session != null){
							session.close();
					}
					if(connection != null){
						connection.close();
					}
				} catch (JMSException e) {
					e.printStackTrace();
					throw new Exception("Problemas ao fechar as conexoes com o JMS");
				}
			}
		}else{
			throw new Exception("Usuario nao existe");
		}
	}

	public List<Reserva> listAllReservas() {
		return reservaRepository.listAllReservas();
	}

}
