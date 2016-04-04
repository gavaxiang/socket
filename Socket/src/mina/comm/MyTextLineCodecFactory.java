package mina.comm;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyTextLineCodecFactory implements ProtocolCodecFactory{
 
    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        return new MyTextLineDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        return new MyTextLineEncoder();
    }

}