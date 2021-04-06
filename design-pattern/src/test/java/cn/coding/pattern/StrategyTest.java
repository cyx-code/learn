package cn.coding.pattern;


import cn.coding.pattern.strategy.Duck;
import cn.coding.pattern.strategy.impl.FlyNoWay;
import cn.coding.pattern.strategy.impl.MallardDuck;
import cn.coding.pattern.strategy.impl.RubberDuck;
import cn.coding.pattern.strategy.impl.Squeak;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StrategyTest {
    @Test
    public void testStrategy() {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuck();
        Duck rubberDuck = new RubberDuck();

        rubberDuck.setFlyBehavior(new FlyNoWay());
        rubberDuck.setQuackBehavior(new Squeak());
        rubberDuck.performFly();
        rubberDuck.performQuck();
    }

    @Test
    public void test2() {
        int [] array = {8, 5, 3, 2, 10};
//        System.out.println(Arrays.toString(insert(array)));
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                array[i] *=2;
            }
        }
        System.out.println(array[1]);
    }

    @Test
    public void testThread() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run");
        }).start();
        //System.out.println("qqqqq");
    }

    public int[] insert(int[] array) {
        for (int i = 1; i < 2; i++) {
            if (array[i] < array[i - 1]) {//如果第i个数大于前一个数就不用判断了,因为前面都是有序数列,大于前一个数肯定比前面所有数都大,否则的话把这个数拿出来也就是赋值给temp,然后依次与前面的数比较,如果比前一个数小就让前一个数往后挪一位直到找到比temp小的位置放进去
                int temp = array[i];
                int f = i;
                for (; f >= 1 && array[f - 1] > temp; f--) {
                    array[f] = array[f-1];

                }
                array[f] = temp;
            }
        }
        return array;
    }


    @Test
    public void checkScanLogin() {
        String url = "http://localhost:8081/uums-cas-server/login";
        Map<String, String> formParam = getLoginFormData(url);
        String wxLogin = "";
        String wxUrl = "http://localhost:8081/uums-cas-server/checkWxLogin.action";
        wxLogin += "code=check_scan_login_type&";
        wxLogin += "state=" + formParam.get("uuid") + "||UUMS|iframe";
        Map<String, String> map = postRequest(wxUrl, wxLogin);
        Map<String, Object> result = JSON.parseObject(map.get("data"), Map.class);
        if (1 == (int) result.get("result")) {
            formParam.put("logType", "W");
            formParam.put("fingerPrint", "960475511");
            formParam.put("passCodeType", "1");
            formParam.put("pcType", "1");
            formParam.put("ip", "10.224.66.67");
            formParam.put("system", "UUMS");
        }
        Set<Map.Entry<String, String>> entries = formParam.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            sb.append(key).append("=").append(value).append("&");
        }
        String param = sb.toString();
        param = param.substring(0, param.length() - 1);
        Map<String, Object> response = postRequest(url + "?" + wxLogin, param);
        try {
            if (200 == (int) response.get("state")) {
                String html = (String) response.get("data");
                Document document = Jsoup.parse(html);
                Element msg = document.getElementById("msg");
                System.out.println(msg);
            }
        } catch (Exception e) {

        }
        System.out.println(map);
    }

    private Map getLoginFormData(String url) {
        Map<String, Object> responseData;
        // 先通过get请求，模拟浏览器跳转到登录界面
        responseData = getRequest(url);
        if (responseData.get("data") == null) {
            return null;
        }
        // 解析前端表单中的数据
        String data = (String) responseData.get("data");
        Document document = Jsoup.parse(data);
        Element loginForm = document.getElementById("loginForm");
        if (loginForm == null) {
            return null;
        }
        // 拿到input标签中的各个属性值
        Elements input = loginForm.getElementsByTag("input");
        Map<String, String> param = new HashMap<>();
        for (Element i : input) {
            String key = i.attr("name");
            String value = i.val();
            if (key != null && key != "") {
                param.put(key, value);
            }
        }
        return param;
    }
    /**
     * 发送get请求
     * @param url
     * @return
     */
    private Map getRequest(String url) {
        Map<String, Object> result = new HashMap<>();
        HttpURLConnection connection = null;
        StringBuffer sb = new StringBuffer();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            bufferedReader.close();
            result.put("data", sb.toString());
            result.put("state", connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
    private Map postRequest(String url, String param) {
        HttpURLConnection connection = null;
        Map<String, Object> result = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            byte[] bytes = param.getBytes();
            connection.getOutputStream().write(bytes);
            connection.setInstanceFollowRedirects(false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s).append("\n");
            }
            reader.close();
            result.put("data", sb.toString());
            result.put("state", connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
