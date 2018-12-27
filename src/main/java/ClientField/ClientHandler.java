package ClientField;


import SafeContext.RSA;
import Wrapper.Wrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handler implementation for the object echo client.  It initiates the
 * ping-pong traffic between the object echo client and server by sending the
 * first message to the server.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * Creates a client-side handler.
     */

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws RuntimeException {
        RSA.init();
    }


    @Override
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


    @Override
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

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}