package com.fyx.springboot.aop;

import com.fyx.springboot.annotation.ServiceLog;
import com.fyx.springboot.entity.Action;
import com.fyx.springboot.entity.Manager;
import com.fyx.springboot.util.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private ActionService actionService;

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    //Service层切点
    @Pointcut("@annotation(com.fyx.springboot.annotation.ServiceLog)")
    public void serviceAspect(){
    }

    @Around("serviceAspect()")
    public Object aroundExec(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        //读取session中的用户
        Manager manager = (Manager) session.getAttribute("manager");

        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += joinPoint.getArgs()[i];
            }
        }
        String mgrName = "";
        int mgrId = 0;
        if(!StringUtils.isEmpty(manager)){
            mgrName = manager.getMgrname();
            mgrId = manager.getMgrid();
        }

        Action action = new Action();
        Object proceed = null;
        try {
            /*========控制台输出=========*/
            System.out.println("==============开始==============");
            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            System.out.println("方法描述：" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人："+ mgrName);

            /*========数据库日志=========*/
            action.setActionDesc(getServiceMethodDescription(joinPoint));
            action.setActionMethod(request.getMethod());
            action.setActionUrl(request.getRequestURI());
            action.setParams(params);
            action.setContentType(request.getContentType());
            action.setActionType("0");
            action.setManagerId(mgrId);
            action.setIp(request.getRemoteAddr());
            action.setActionTime(DateUtil.dateToString(new Date()));
            //向下执行
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            action.setActionType("1");
            action.setMessage(e.getMessage());
            logger.error("异常信息：{}",e.getMessage());
        } catch (Throwable throwable) {
            action.setActionType("1");
            action.setMessage(throwable.getMessage());
            logger.error("异常信息：{}",throwable.getMessage());
        }finally {
            //保存数据库
            actionService.add(action);
        }
        return proceed;

    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(ServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


}
