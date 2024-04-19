package com.whale.cloud.router;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.whale.cloud.router.annotation.DS;
import com.whale.cloud.router.annotation.TB;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author jiazhiwei
 */
@Slf4j
@Aspect
@Component("db-router-point")
public class DynamicRouterJoinPoint extends DynamicRouterSupport {

    @Value("${whale.mini-db-router.jdbc.default:db01}")
    private String defaultDb;

    @Pointcut("@annotation(com.whale.cloud.router.annotation.DS)")
    public void dbPoint() {
    }

    @Pointcut("@annotation(com.whale.cloud.router.annotation.TB)")
    public void tbPoint() {

    }

    @Around("dbPoint() && @annotation(ds)")
    public Object doRouter(ProceedingJoinPoint jp, DS ds) throws Throwable {
        String assignKey = ds.key();
        if (StringUtils.isBlank(assignKey)) {
            assignKey = defaultDb;
        }
        String dbKey = getDbKey(assignKey);
        // 设置到 ThreadLocal
        DbContextHolder.setDBKey(dbKey);
        // 返回结果
        try {
            return jp.proceed();
        } finally {
            DbContextHolder.clearDBKey();
            DbContextHolder.clearTBKey();
        }
    }

    @Around("tbPoint() && @annotation(tb)")
    public Object doRouter(ProceedingJoinPoint jp, TB tb) throws Throwable {
        if (StringUtils.isBlank(DbContextHolder.getDBKey())) {
            DbContextHolder.setDBKey(getDbKey(defaultDb));
        }
        String tbKey = tb.key();
        Object[] params = jp.getArgs();
        if (StrUtil.isNotBlank(tbKey) && ArrayUtil.isNotEmpty(params)) {
            Method method = getMethod(jp);
            String tbKeyIdx = null;
            for (int i = 0; i < method.getParameters().length; i++) {
                tbKeyIdx = getRouterValue(tbKey, params[i], method.getParameters()[i]);
                if (StrUtil.isNotBlank(tbKeyIdx)) {
                    break;
                }
            }
            DbContextHolder.setTBKey(String.valueOf(getTbRouterAlgorithm().execute(tbKeyIdx)));
        }

        // 返回结果
        try {
            return jp.proceed();
        } finally {
            DbContextHolder.clearDBKey();
            DbContextHolder.clearTBKey();
        }
    }

    private String getRouterValue(String tbKey, Object param, Parameter parameter) {
        List<String> tbKeys = StrUtil.split(tbKey, ".");
        String key = tbKeys.get(0);
        if (!StrUtil.equals(parameter.getName(), key)) {
            return null;
        }
        if (tbKeys.size() == 1) {
            return String.valueOf(param);
        }
        tbKeys = ListUtil.sub(tbKeys, 1, tbKeys.size());
        return BeanUtil.getProperty(param, ArrayUtil.join(tbKeys.toArray(), ","));
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}
