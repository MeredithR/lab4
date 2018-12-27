/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package ServerField;

import Wrapper.Wrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles both client-side and server-side handler depending on which
 * constructor was called.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
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
