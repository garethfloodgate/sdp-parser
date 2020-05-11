package org.vidtech.sdp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.io.StringWriter;

import org.testng.annotations.Test;
import org.vidtech.sdp.descriptor.Originator;
import org.vidtech.sdp.descriptor.Originator.AddressType;
import org.vidtech.sdp.descriptor.SDPDescriptor;

@Test
public class SDPUtilsWriteTest
{
	
	public void testCanWriteSDPWithMinimalData()
	{
		SDPDescriptor sdp = SDPDescriptor.builder()
							    .withOriginator(
						    		Originator.builder()
										.withId("dave")
										.withVersion("bob")
										.withAddrType(AddressType.IP4)
										.withUnicastAddress("streaming.awesome.com")
										.build())
					            .withSessionName("A session")
					            .build();
		
		String expected = "v=0\r\n" 
		                + "o=- dave bob IN IP4 streaming.awesome.com\r\n"
				        + "s=A session\r\n";

		try
		{
			StringWriter w = new StringWriter();
			SDPUtils.write(sdp, w);
			
			assertEquals(w.toString(), expected, "Unexpected string write output");
		}
		catch (IOException e)
		{
			fail("Unexpected IO error");
		}
		
	}
	

	
	
}