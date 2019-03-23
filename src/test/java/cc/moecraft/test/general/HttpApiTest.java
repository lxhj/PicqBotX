package cc.moecraft.test.general;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.sender.IcqHttpApi;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.test.icq.listeners.ExceptionListener;
import cc.moecraft.test.icq.listeners.TestListener;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * The class {@code HttpApiTest} is the unit tester for the http api.
 * <p>
 * Class created by the HyDEV Team on 2019-03-21!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-03-21 21:05
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpApiTest
{
    private static final long TEST_RECEIVE_QQ = 233186409; // 用来测试的QQ, 必须不是机器人QQ, 而且要在群里x
    private static final long TEST_RECEIVE_GR = 491046707; // 用来测试的群

    private static PicqBotX bot;
    private static IcqHttpApi api;
    private static HyLogger logger;

    @BeforeClass
    public static void init()
    {
        // 创建机器人对象 ( 接收端口 )
        PicqBotX bot = new PicqBotX(30192);

        // 添加一个机器人账户 ( 名字, 发送URL, 发送端口 )
        bot.addAccount("Bot01", "127.0.0.1", 31091);

        // 注册事件
        bot.getEventManager().registerListeners(new TestListener(), new ExceptionListener());

        // 启用指令管理器, Unit test
        bot.enableCommandManager(false, "ut -");

        api = bot.getAccountManager().getNonAccountSpecifiedApi();
        logger = bot.getLogger();

        // 启动机器人
        new Thread(bot::startBot).start();
        ThreadUtil.safeSleep(2000);
    }
}