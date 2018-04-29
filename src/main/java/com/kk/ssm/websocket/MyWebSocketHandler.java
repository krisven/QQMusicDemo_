package com.kk.ssm.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kk.ssm.entity.Message;
import com.kk.ssm.service.Impl.MsgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import javax.websocket.OnMessage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    @Resource
    private MsgServiceImpl msgService;

    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
    public static final Map<Integer, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
    }

    //握手实现连接后
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("握手实现连接后");
        int usernumber = (Integer) webSocketSession.getAttributes().get("usernumber");
        if(usernumber!=0){
            System.out.println("usernumber:" + usernumber);
            userSocketSessionMap.put(usernumber, webSocketSession);
            System.out.println("webSocketSession:" + userSocketSessionMap.get(usernumber));
        }
    }

    //发送信息前的处理
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println("信息处理：" + webSocketMessage.getPayloadLength());
        if(webSocketMessage.getPayloadLength()==0)
            return;

        //得到Socket通道中的数据并转化为Message对象
        final Message msg = new Gson().fromJson(webSocketMessage.getPayload().toString(), Message.class);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("当前系统时间为:" + df.format(day));
        //Timestamp now = new Timestamp(currentTimeMillis());
        msg.setMsgTime(df.format(day));
        System.out.println("插入数据为：FromId:"+ msg.getFromId() + " FromName" + msg.getFromName() + " toId:" + msg.getToId() + " msgText：" + msg.getMsgText() + "msgTime:" + msg.getMsgTime());
        //将信息保存至数据库
        try{
            String str = msgService.addMsg(msg.getFromId(), msg.getFromName(), msg.getToId(), msg.getMsgText(), msg.getMsgTime());
            System.out.println("添加成功"+str);
        }catch (Exception e){
            System.out.println(e);
        }

        Runnable runnable = new Runnable() {
            public void run() {
                while(true){
                    System.out.println("线程");
                    Date day1=new Date();
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    Message message = null;
                    if(msgService.findByUserAndTime(msg.getFromId(), df1.format(day1))!=null){
                        message=msgService.findByUserAndTime(msg.getFromId(), df1.format(day1));
                        try {
                            sendMessageToUser(message.getToId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        /*Message sendMsg = new Message();
        sendMsg.setFromId(1);
        sendMsg.setFromName("服务器");
        sendMsg.setToId(msg.getFromId());
        sendMsg.setMsgText("来自服务器");
        sendMsg.setMsgTime(df.format(day));
        //发送Socket信息
        sendMessageToUser(sendMsg.getToId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(sendMsg)));*/

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /*
     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
     */

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket:"+webSocketSession.getAttributes().get("usernumber")+"close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if(entry.getValue().getAttributes().get("usernumber")==webSocketSession.getAttributes().get("usernumber")){
                userSocketSessionMap.remove(webSocketSession.getAttributes().get("usernumber"));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("usernumber") + "removed");
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    //发送信息的实现
    public void sendMessageToUser(int usernumber, TextMessage message)
            throws IOException {
        System.out.println("发送消息:" + usernumber + "  " + message);
        WebSocketSession session = userSocketSessionMap.get(usernumber);
        System.out.println("session:" + session);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }
}
