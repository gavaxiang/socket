package mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import mina.comm.MyServerHandleDemo1;
import mina.comm.MyTextLineCodecFactory;

public class SimpleMinaServer {

	SocketAcceptor acceptor = null;

	SimpleMinaServer() {
		acceptor = new NioSocketAcceptor();
	}

	public boolean bind() {
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineCodecFactory())); // 配置CodecFactory
		LoggingFilter log = new LoggingFilter();
		log.setMessageReceivedLogLevel(LogLevel.INFO);
		acceptor.getFilterChain().addLast("logger", log);
		acceptor.setHandler(new MyServerHandleDemo1());// 配置handler
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);

		try {
			acceptor.bind(new InetSocketAddress(8888));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String args[]) {
		SimpleMinaServer server = new SimpleMinaServer();
		if (!server.bind()) {
			System.out.println("服务器启动失败");
		} else {
			System.out.println("服务器启动成功");
		}
	}

}
