package com.king.app.webapp.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.model.UserInfo;
import com.king.framework.utils.AuthUtils;
import com.king.framework.utils.RedisUtil;
import com.king.system.cache.DictCache;
import com.king.system.conts.DictConts;
import com.king.system.entity.SysResources;
import com.king.system.entity.SysRole;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysResourcesService;
import com.king.system.service.ISysRoleService;
import com.king.system.service.ISysUserService;
import com.king.system.shiro.CustomerShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/14
 * @描述
 */
@Controller
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    Random ran = new Random();

    public static final String RedisVerCodeKey = "King-Redis-VerCode_";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysResourcesService sysResourcesService;

    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView  mv = new ModelAndView("login");
        JSONArray i18nKeys = super.loadI18nData();
        mv.addObject("i18nKeys",i18nKeys);
        return mv;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String username, String password, String verCode) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        String servVerCode = redisUtil.get(RedisVerCodeKey + SecurityUtils.getSubject().getSession().getId()).toString();
        if(!verCode.equalsIgnoreCase(servVerCode)){
            return new ResultResp<>("验证码错误");
        }

        UsernamePasswordToken uToken = new UsernamePasswordToken(username, password);
        //实现记住我
        uToken.setRememberMe(true);
        try {
            //进行验证，报错返回首页，不报错到达成功页面。
            subject.login(uToken);

        } catch (UnknownAccountException e) {
            throw e;//交给统一异常进行处理
        } catch (IncorrectCredentialsException e) {
            throw e;//交给统一异常进行处理
        }
        //成功则跳转页面
        return ResultResp.ok();
    }

    private void clearSession(String username){
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        RedisSessionDAO sessionDAO = (RedisSessionDAO) sessionManager.getSessionDAO();
        Collection<Session> sessions = sessionDAO.getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions) {
            //清除该用户以前登录时保存的session
            if (session != null) {
                SimplePrincipalCollection simplePrincipal = (SimplePrincipalCollection) session.getAttribute("shiro_redis_session");
                if (simplePrincipal != null) {
                    Object obj = simplePrincipal.getPrimaryPrincipal();
                    UserInfo userInfo = AuthUtils.obj2Bean(obj);
                    String account = userInfo.getUsername();
                    if (account != null && username.equals(account)) {
                        sessionManager.getSessionDAO().delete(session);
                    }
                }
            }
        }
    }

    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request){
        logger.info("********************登录成功，进入首页！********************");

        UserInfo userInfo = AuthUtils.getUserInfo();

        ModelAndView mv = new ModelAndView("index");
        JSONArray i18nKeys = super.loadI18nData();
        mv.addObject("i18nKeys",i18nKeys);
        mv.addObject("userInfo",userInfo);

        DictCache.getDict(DictConts.SYS_RES_TYPE);

        //获取用户角色和权限
        boolean flag = bindPermissionResources(request);
        if(!flag){
            mv = new ModelAndView("redirect:/login");
        }
        return mv;
    }

    /**
     * 绑定资源权限到session
     * @param request
     */
    private boolean bindPermissionResources(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            subject.logout();
            return false;
        }
        String account = AuthUtils.getUserInfo().getUsername();
        StringBuffer buffer = new StringBuffer();
        SysUser user = sysUserService.findByUserName(account);
        if(user != null){
            List<SysRole> roles = sysRoleService.getRolesByUserId(user.getId());
            List<SysResources> resources = new ArrayList<>();
            for(SysRole role : roles){
                resources.addAll(sysResourcesService.getResourceByRoleId(role.getId()));
                Set<String> permissions = CustomerShiroRealm.getPermissions(resources);
                int i = 0;
                int size = permissions.size();
                for (String permission : permissions) {
                    buffer.append(permission);
                    if (i < size - 1) {
                        buffer.append(",");
                    }
                    i++;
                }
            }
        }
        request.getSession().setAttribute("permissions", buffer.toString());
        return true;
    }

    @ResponseBody
    @RequestMapping("loadMenus")
    public Object loadMenus(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            UserInfo userInfo = AuthUtils.getUserInfo();
            Criteria criteria = new Criteria();
            criteria.put("type",0);//菜单
            criteria.put("userId",userInfo.getId());
            List<SysResources> resources = sysResourcesService.findResources(criteria);

            //循环顶级节点，将其子节点拼装成树形结构
            JSONArray roots = new JSONArray();
            for(SysResources res : resources){
                if(res.getPid() == -1L){//顶级节点
                    JSONObject node = new JSONObject();
                    node.put("id",res.getId());
                    node.put("text",res.getName());
                    node.put("pid",res.getPid());
                    node.put("url",res.getUrl());
                    node.put("type",res.getType());
                    node.put("icon",res.getIcon() != null ? res.getIcon() : "");//这里可以扩展，自定义图标样式
                    JSONArray childs = hasChildrenNodes(res,resources);
                    node.put("children",childs);
                    roots.add(node);
                }
            }
            return roots;
        }
        return new JSONArray();
    }

    /**
     * 递归成树
     * @param parent
     * @param children
     * @return
     */
    private JSONArray hasChildrenNodes(SysResources parent,List<SysResources> children){
        JSONArray datas = new JSONArray();
        for(SysResources res : children){
            if(res.getPid() == parent.getId()){
                JSONObject node = new JSONObject();
                node.put("id",res.getId());
                node.put("text",res.getName());
                node.put("pid",res.getPid());
                node.put("url",res.getUrl());
                node.put("type",res.getType());
                node.put("icon",res.getIcon() != null ? res.getIcon() : "");//这里可以扩展，自定义图标样式
                JSONArray childs = hasChildrenNodes(res,children);
                node.put("children",childs);
                datas.add(node);
            }
        }
        return datas;
    }

    /**
     * 退出登录
     * @return
     */
    @ResponseBody
    @RequestMapping("logout")
    public ResultResp logout(){
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = AuthUtils.getUserInfo();

        subject.logout();//退出

        clearSession(userInfo.getUsername());//清空session
        return ResultResp.ok();
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("welcome1")
    public String welcome1(){
        return "welcome1";
    }

    @RequestMapping("createVerCodeImage")
    public void createVerCodeImage(HttpServletResponse resp){
        int width = 100;
        int height = 50;

        //1.创建一对象，在内存中图片(验证码图片对象)
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.PINK);//设置画笔颜色
        g.fillRect(0,0,width,height);

        //2.2画边框
        g.setColor(Color.gray);
        g.drawRect(0,0,width - 1,height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        StringBuffer verCode = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);//随机字符
            g.setColor(randomColor());//随机颜色
            g.setFont(randomFont());//随机字体
            //2.3写验证码
            g.drawString(ch+"",width/5*i,height/2);
            verCode.append(ch);
        }

        redisUtil.set(RedisVerCodeKey + SecurityUtils.getSubject().getSession().getId(),verCode.toString().toUpperCase(),1800);

        //2.4画干扰线
        g.setColor(Color.CYAN);
        //随机生成坐标点
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);//画线
        }

        //画干扰点
        for (int i = 0; i < 10; i++) {
            g.setColor(randomColor());//随机颜色
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            g.drawOval(x1,y1,2,2);//画点
        }

        //3.将图片输出到页面展示
        try {
            ImageIO.write(image,"jpg",resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //生成随机颜色
    private Color randomColor(){
        int red = ran.nextInt(255);
        int green = ran.nextInt(255);
        int blue = ran.nextInt(255);
        return new Color(red,green,blue);
    }

    //生辰随机字体
    private Font randomFont(){
        String[] fontNames= {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
        int index = ran.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = ran.nextInt(4);
        int size=ran.nextInt(5)+ 24;
        return new Font(fontName,style,size);
    }

}
