package cn.coding;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份认证测试类
 */
public class AuthenticatorTest {
    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:authenticator/shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(2, principals.asList().size());
    }
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:authenticator/shiro-authenticator-all-fail.ini");
//        Subject subject = SecurityUtils.getSubject();
//        PrincipalCollection principals = subject.getPrincipals();
//        Assert.assertEquals(2, principals.asList().size());
    }
    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
        login("classpath:authenticator/shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();
        // 得到一个身份集合，其中包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(2, principals.asList().size());
    }
    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:authenticator/shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(1, principals.asList().size());
    }
    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:authenticator/shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(1, principals.asList().size());
    }
    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:authenticator/shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(1, principals.asList().size());
    }
    private void login(String configFile) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
    }
    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();
    }
}
