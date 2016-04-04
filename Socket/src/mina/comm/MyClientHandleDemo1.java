package mina.comm;

import java.util.logging.Logger;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import mina.ChatPanel;

public class MyClientHandleDemo1 extends IoHandlerAdapter{
    private ChatPanel messagePanel = null;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    MyClientHandleDemo1(){
        
    }
    
    public MyClientHandleDemo1(ChatPanel messagePanel){
        this.messagePanel = messagePanel;
    }
    
    
    public void messageReceived(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
        String messageStr = message.toString();
        logger.info("receive a message is : " + messageStr);
        
        if(messagePanel != null)
        messagePanel.showMsg(messageStr);
    }
    
    public void messageSent(IoSession session , Object message) throws Exception{
        logger.info("客户端发了一个信息：" + message.toString());
    }
    
}
