package com.sztech.cleangovern.service;

import com.sztech.cleangovern.service.service.provider.IFeebackProblemInfoProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = DubboConsumerApplication.class)
public class DubboConsumerApplicationTests {

	@Autowired
	IFeebackProblemInfoProvider feebackProblemInfoProvider;

	@Test
	public void contextLoads() {
		//System.out.println(feebackProblemInfoProvider.test());
	}

}
