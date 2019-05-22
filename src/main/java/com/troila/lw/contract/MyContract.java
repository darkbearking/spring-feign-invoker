package com.troila.lw.contract;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;

import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

import feign.Contract.BaseContract;
import feign.MethodMetadata;

/**
 * 當前例子的目的，是讓MyContract識別兩種註解
 * 分別是我們自定義的和Spring的註解
 * 因此先要讓當前類繼承spring默認的翻譯器SpringMvcContract
 * @author liwei
 *
 */
public class MyContract extends SpringMvcContract {

	/**
	 * 處理類級別註解
	 * 
	 * 需要注意的是，如果這個方法保留，那麼會影響到其他方法沒有使用MyUrl註解的方法請求的
	 * 或者在當前方法中使用super()，繼承父類同名方法的功能也不會報錯
	 */
	@Override
	protected void processAnnotationOnClass(MethodMetadata data, Class<?> clz) {
		// TODO Auto-generated method stub
		super.processAnnotationOnClass(data, clz);
	}

	//處理方法級別註解
	//同時因為我們定義的MyUrl註解僅僅是一個方法級的註解（@Target(ElementType.METHOD)），故只對當前方法進行完善即可
	@Override
	protected void processAnnotationOnMethod(MethodMetadata data, Annotation annotation, Method method) {
		super.processAnnotationOnMethod(data, annotation, method);
		
		//如果註解是MyUrl類型的，我們才做處理，否則忽略
		if(MyUrl.class.isInstance(annotation)) {
			System.out.println("##############  這是自定義編譯器");
			
			MyUrl myurl = method.getAnnotation(MyUrl.class);
			String url = myurl.url();
			String httpMethod = myurl.method();
			//data.template()保存http請求的信息，包括請求頭、url等
			data.template().method(httpMethod);
			//下面的寫法是取代原有的請求路徑。相當於url的重寫了。
			//data.template().url(url);
			//下面的寫法是追加到原有的請求路徑下。因為我們在feign客戶端一般寫的是服務的通用地址，然後追加具體功能的地址
			data.template().append(url);
		}
	}

	/**
	 * 處理參數級別註解
	 * 
	 * 需要注意的是，如果這個方法保留，那麼會影響到其他方法沒有使用MyUrl註解的方法請求的
	 * 或者在當前方法中使用super()，繼承父類同名方法的功能也不會報錯
	 */
	@Override
	protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
		// TODO Auto-generated method stub
		return super.processAnnotationsOnParameter(data, annotations, paramIndex);
	}

}
