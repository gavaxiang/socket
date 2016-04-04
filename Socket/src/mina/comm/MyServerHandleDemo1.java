package mina.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


public class MyServerHandleDemo1 implements IoHandler{

    private Logger logger = Logger.getLogger(this.getClass().getName());
     
    @Override
    public void exceptionCaught(IoSession session, Throwable arg1)
            throws Exception {
        // TODO Auto-generated method stub
        logger.warning("服务器启动发生异常，have a exception : " + arg1.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        String messageStr = message.toString();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        String dateStr = format.format(new Date());
        
        logger.info(messageStr + "\t" + dateStr);
        
        Collection<IoSession> sessions = session.getService().getManagedSessions().values();
        for(IoSession tempSession : sessions){
            tempSession.write(messageStr + "\t" + dateStr);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        logger.info("服务器成功发送信息: " + message.toString());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        logger.info("there is a session closed");
        CloseFuture future = session.close(true);
        future.addListener(new IoFutureListener(){
            public void operationComplete(IoFuture future){
                if(future instanceof CloseFuture){
                    ((CloseFuture)future).setClosed();
                    logger.info("have do the future set to closed");
                }
            }
        });
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        logger.info("there is a session created");
        session.write("welcome to the chat room");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
        // TODO Auto-generated method stub
        logger.info(session.getId() + "(SesssionID) is idle in the satate-->" + arg1);
    }

    @Override
    public void sessionOpened(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

    
}
