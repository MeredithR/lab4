
package ServerField;

import Wrapper.Wrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


public class ServerHandler extends ChannelInboundHandlerAdapter {

    
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        BigInteger n = BigInteger.valueOf(0);
        BigInteger d = BigInteger.valueOf(0);
        if(msg instanceof Wrapper){
            Wrapper wrapper = (Wrapper) msg;
            ArrayList<Object> msg1 = wrapper.getData();
            n = (BigInteger) msg1.get(0);
            d = (BigInteger) msg1.get(1);
            if(wrapper.getMessage()!=null){
                System.out.println("Received message: ");
                System.out.println(ServerField.decryption(n,d,wrapper.getMessage()));
            }
        }
        Scanner scanner = new Scanner(System.in);
        String string = "";
        System.out.println("Enter the message: ");
        if(scanner.hasNextLine()){
            string = scanner.nextLine();
        }
        BigInteger msg2 = ServerField.encryption(n,d,string);
        ctx.write(msg2);
        ctx.flush();
    }

    
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
