package model;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ChatHis {
    /**
     * uuid : 093115ba-96a9-11e6-a4fe-4ffa56bd3684
     * type : chatmessage
     * created : 1476956342923
     * modified : 1476956342923
     * timestamp : 1476956342914
     * from : wangjiaxu
     * msg_id : 255084734365829120
     * to : zhaokuibo
     * chat_type : chat
     * payload : {"ext":{"nickname":"隔壁老赵(赵奎博)"},"bodies":[{"msg":"玩玩玩","type":"txt"}]}
     */

    private long created;
    private String from;
    private String to;
    /**
     * ext : {"nickname":"隔壁老赵(赵奎博)"}
     * bodies : [{"msg":"玩玩玩","type":"txt"}]
     */

    private PayloadBean payload;


    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public static class PayloadBean {
        /**
         * msg : 玩玩玩
         * type : txt
         */

        private List<BodiesBean> bodies;

        public List<BodiesBean> getBodies() {
            return bodies;
        }

        public void setBodies(List<BodiesBean> bodies) {
            this.bodies = bodies;
        }

        public static class BodiesBean {
            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }


//    private String created;
//    private String from;
//    private String to;
//    private String payload;
//    private List<String> msgList;
//    private String msg;
//
//    public String getCreated() {
//        return created;
//    }
//
//    public void setCreated(String created) {
//        this.created = created;
//    }
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public String getTo() {
//        return to;
//    }
//
//    public void setTo(String to) {
//        this.to = to;
//    }
//
//    public List<String> getMsgList() {
//        return msgList;
//    }
//
//    public void setMsgList(List<String> msgList) {
//        this.msgList = msgList;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getPayload() {
//        return payload;
//    }
//
//    public void setPayload(String payload) {
//        String temp = jsonUtil.getValue(payload,"bodies").toString();
//        temp = temp.substring(1,temp.length()-1);
//        temp = jsonUtil.getValue(temp,"msg").toString();
//        this.payload = temp;
//    }
}
