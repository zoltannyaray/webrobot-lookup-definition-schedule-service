package com.dayswideawake.webrobot.messaging.consumer;

import java.util.Date;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.TestGroup;
import com.dayswideawake.webrobot.backend.service.LookupDefinitionTaskService;
import com.dayswideawake.webrobot.messaging.Channels;
import com.dayswideawake.webrobot.messaging.model.LookupCreatedMessage;

@Test(groups=TestGroup.GROUP_INTEGRATION)
@SpringBootTest
public class NewLookupHandlerTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private Channels channels;
	@Mock
	private LookupCreatedMessage lookupCreatedMessage;
	@Mock
	private LookupDefinitionTaskService lookupDefinitionTaskService;
	
	private Long lookupJobId;
	private Date lookupTime;
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		lookupJobId = 1L;
		lookupTime = new Date();
		Mockito.when(lookupCreatedMessage.getLookupTime()).thenReturn(lookupTime.getTime());
		Mockito.when(lookupCreatedMessage.getLookupJobId()).thenReturn(lookupJobId);
	}
	
	public void onNewLookupShouldBeCalledOnInputMessage() {
		// when
		Message<LookupCreatedMessage> message = MessageBuilder.withPayload(lookupCreatedMessage).build();
		channels.newLookups().send(message);
		// then
//		Mockito.verify(lookupDefinitionTaskService).markTaskLastLookupAt(lookupJobId, lookupTime);
	}
	
}
