/**
 * Created by joee on 2017/9/27.
 */

package Mina;

public class MinaClientEntry {
    public static void main(String[] args) {
        MinaClient client = new MinaClient();
        client.connect();
        client.sendMsg2Server("i am cilent");
    }
}
