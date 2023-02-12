package client;


import com.crazymakercircle.im.common.bean.User;
import com.crazymakercircle.imClient.session.ClientSession;
import com.crazymakercircle.util.JsonUtil;
import com.crazymakercircle.util.Logger;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestClientSession {


    //测试用例： 会话的双向绑定

    @Test
    public void testSessionBind() throws Exception {
        // EmbeddedChannel是专门用于测试使用的
        ClientSession session=new ClientSession(new EmbeddedChannel());
        session.setUser(new User());

        // 把POJO对象以 json的形式，打印出来
        Logger.cfo(JsonUtil.pojoToJson(session.getUser()));

        Logger.cfo("eg:正向导航");
        //  通过session获得channel
        Channel channel=session.getChannel();

        Logger.cfo("eg:反向导航");
        // 通过channel获得session
        Attribute<ClientSession> r_session = channel.attr(ClientSession.SESSION_KEY);

        Logger.cfo(JsonUtil.pojoToJson(r_session.get().getUser()));

    }


}
