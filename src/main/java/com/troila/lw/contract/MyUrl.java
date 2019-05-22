package com.troila.lw.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定義注釋
 * @author liwei
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {

	//這裡定義了兩個方法或者叫屬性，那麼在註解的使用時就不能出現這兩個屬性以外的其他屬性
	String url();
	String method();
}
