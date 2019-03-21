package com.atguigu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service = null;

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(
			fallbackMethod = "processHystrix_Get",
			commandProperties={
				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
			}
	)
	public Dept get(@PathVariable("id") Long id)
	{

		Dept dept = this.service.get(id);

		List<Dept> list = service.list();

		if (null == dept) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return dept;
	}


	/**
	 * 这个最好写到Service层中
	 * @return
	 */
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	@HystrixCommand(
			fallbackMethod = "processHystrix_List",
			commandProperties={
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="50000")
			},
			observableExecutionMode = ObservableExecutionMode.LAZY
	)
	public List<Dept> list() {
		List<Dept> resultList = new ArrayList<>();

		List<Dept> list11 = this.service.list();
		System.out.println(list11);

		Observable<List<Dept>> observable =  Observable.create(new Observable.OnSubscribe<List<Dept>>(){

			@Override
			public void call(Subscriber<? super List<Dept>> subscriber) {

				if (!subscriber.isUnsubscribed()) {

					System.out.println("进入消费者被观察者方法");

					List<Dept> list1 = service.list();

					subscriber.onNext(list1);

					List<Dept> list2 = service.list();
					subscriber.onNext(list2);


					subscriber.onCompleted();
				}
			}
		});

		observable.subscribe(new Observer<List<Dept>>(){

			@Override
			public void onCompleted() {
				System.out.println("会聚完了所有查询请求");
			}

			@Override
			public void onError(Throwable throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void onNext(List<Dept> list) {
				System.out.println("结果来了.....");
				resultList.addAll(list);
			}
		});


		return resultList;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id, Throwable e)
	{
		e.printStackTrace();

		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
				.setDb_source("no this database in MySQL");
	}

	public List<Dept> processHystrix_List(Throwable e)
	{
		e.printStackTrace();
		return Arrays.asList(new Dept());
	}

}