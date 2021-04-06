package cn.coding;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testServer() throws Exception {
        new Server().start();
    }

    @Test
    public void testClient() throws Exception {
        final Client client = new Client();
        Thread sendMsg = new Thread(client::sendInputMsg);
        sendMsg.start();
        client.start();
    }
}
