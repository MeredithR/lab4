package ClientField;


import SafeContext.RSA;
import Wrapper.Wrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;



public class ClientHandler extends ChannelInboundHandlerAdapter {
    

   
    public void channelRegistered(ChannelHandlerContext ctx) throws RuntimeException {
        RSA.init();
    }


   
    public void channelActive(ChannelHandlerContext ctx) {
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(RSA.getN());
        data.add(RSA.getD());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String msg1 = scanner.nextLine();
        BigInteger msg = ClientField.encryption(RSA.getN(),RSA.getE(),msg1);
        Wrapper wrapper = new Wrapper(data,msg);
        ctx.write(wrapper);
        ctx.flush();
    }


    
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof BigInteger){
            BigInteger msg1 = (BigInteger) msg;
            System.out.println("Received message: ");
            System.out.println(ClientField.decryption(RSA.getN(),RSA.getE(),msg1));
        }
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(RSA.getN());
        data.add(RSA.getD());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String msg1 = scanner.nextLine();
        BigInteger msg2 = ClientField.encryption(RSA.getN(),RSA.getE(),msg1);
        Wrapper wrapper = new Wrapper(data,msg2);
        ctx.write(wrapper);
    }

   
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

   
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
