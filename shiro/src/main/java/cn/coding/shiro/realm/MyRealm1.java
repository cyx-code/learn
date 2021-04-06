package cn.coding.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行MyRealm1...");
        // 得到用户名
        String username = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"zhang".equals(username)) {
            // 如果用户名错误
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            // 如果密码错误
            throw new IncorrectCredentialsException();
        }
        // 如果身份验证成功，返回一个Authentication实现
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
