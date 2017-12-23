package com.x3.core.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.x3.core.ResponseState;
import com.x3.core.annotation.BodyFormat;
import com.x3.core.annotation.NotNull;
import com.x3.core.dto.ParamsBody;
import com.x3.core.dto.ReturnBody;

/**
 * 切面，对RequestMapping所有请求切入 判断是否有效用户、参数值是否为空
 *
 */
@Component("requestAspect")
@Aspect
@Configuration
@ComponentScan("com.x3.core.aspect")
@EnableAspectJAutoProxy
public class RequestAspect {

	// 定义切入点
	@SuppressWarnings("unused")
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) ")
	private void pointCut() {
	}

	@Around("pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args = pjp.getArgs();
		ParamsBody paramsBody = null;
		HttpServletRequest request = null;
		Map<String, Object> params = null;
		for (int j = 0; j < args.length; j++) {
			if (args[j] instanceof ParamsBody) {
				paramsBody = (ParamsBody) args[j];
			} else if (args[j] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[j];
			}
		}

		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		Annotation[] annotations = method.getAnnotations();

		for (Annotation annotation : annotations) {
			Class<? extends Annotation> type = annotation.annotationType();
			if (paramsBody != null)
				params = paramsBody.getBody();
			if (BodyFormat.class.equals(type) && params != null) {
				BodyFormat ann = (BodyFormat) annotation;
				String value = ann.value();
				String[] fields = value.split(",");
				Map<String, Object> formatParams = new HashMap<String, Object>();
				for (String filed : fields) {
					if (params.get(filed) != null)
						formatParams.put(filed, params.get(filed));
				}
				paramsBody.setBody(formatParams);
			} else if (NotNull.class.equals(type)) {
				NotNull ann = (NotNull) annotation;
				String value = ann.value();
				boolean user = ann.user();
				ReturnBody returnbody = new ReturnBody();

				if (user) {
					String token = request.getHeader("Token");
					if (StringUtils.isEmpty(token)) {
						returnbody.setStatus(ResponseState.INVALID_TOKEN);
						returnbody.setMsg("token 为空");
						return returnbody;
					}
					if (StringUtils.isEmpty(token)) {
						returnbody.setStatus(ResponseState.INVALID_TOKEN);
						returnbody.setMsg("token为空");
						return returnbody;
					}
				}

				String[] fields = value.split(",");
				Object keyValue = null;
				if (params != null) {
					for (String filed : fields) {
						keyValue = params.get(filed);
						if (StringUtils.isEmpty(keyValue)) {
							returnbody.setStatus(ResponseState.FAILED);
							returnbody.setMsg("入参" + filed + "值为空");
							return returnbody;
						}
					}
				}

			}
		}
		return pjp.proceed();
	}
}
