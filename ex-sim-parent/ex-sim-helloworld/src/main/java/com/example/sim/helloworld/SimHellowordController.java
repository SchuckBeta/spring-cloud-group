package com.example.sim.helloworld;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class SimHellowordController  {

	volatile static int n=0;
	volatile static int m=0;

	Executor executor = Executors.newFixedThreadPool(10);

    @ResponseBody
    @RequestMapping(value = "/hello/{nPath}")
    String home(@PathVariable(value = "nPath") int nPath ) {

    	final int sPath=nPath;

    	Runnable task = new Runnable() {
    	    public void run() {
    	    	System.out.println("hello-Thread.currentThread().getName()-"+Thread.currentThread().getName());
    	    	System.out.println("hello-sPath-"+sPath);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	    	System.out.println("hello-sPath deal end-"+(sPath));
    	    }
    	};
    	executor.execute(task);


        return "Hello World!"+n;
    }

    @ResponseBody
    @RequestMapping(value = "/user")
    String user() {
    	System.out.println("user-Thread.currentThread().getName()-"+Thread.currentThread().getName());
    	System.out.println("user-sPath-"+(++m));

    	System.out.println("user-sPath deal end-"+(m));
        return "Hello User!"+m;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimHellowordController.class, args);
//        SpringApplication.run(HellowordController.class, "--server.port=8081");
//        java -jar cloud-simple-helloword-0.0.1.jar --server.port=8081
    }

}
