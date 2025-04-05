package com.webreach.mirth.plugins.xdsquerywizard;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPage;

import com.webreach.mirth.client.ui.PlatformUI;
import com.webreach.mirth.model.Channel;
import com.webreach.mirth.model.converters.ObjectXMLSerializer;

public class WizardChannelProducer implements WizardPage.WizardResultProducer {

	private static final String IHE_XDS_QUERY_CHANNEL_TEMPLATE_XML = "IHE XDS Query Channel Template.xml";
	private ADTToIHEXDSWizardPlugin wizard;
	
	public WizardChannelProducer(ADTToIHEXDSWizardPlugin wizard) {
		this.wizard = wizard;
	}
	
	public boolean cancel(Map data) {
		return wizard.alertOption("Are you should you would like to cancel the wizard?");
	}

	public Object finish(Map data) throws WizardException {
		String channelXML = loadChannelTemplate();
		channelXML = performTemplateReplacements(data, channelXML);
		Channel channel = (Channel)new ObjectXMLSerializer().fromXML(channelXML);
		channel.setName("ADT over port " + (String)data.get("listenerPort") + " to IHE XDS Query");
		channel.setLastModified(Calendar.getInstance());
		channel.setVersion(PlatformUI.SERVER_VERSION);
		channel.setId(UUID.randomUUID().toString());
		
		return channel;
	}

	private String performTemplateReplacements(Map data, String channelXML) {
		// run replacements
		channelXML = channelXML.replaceAll("\\@port", (String)data.get("listenerPort"));
		channelXML = channelXML.replaceAll("\\@mapping", (String)data.get("mapping"));

		String[] startDateComp = ((String)data.get("startDate")).substring(4).split("-"); 
		channelXML = channelXML.replaceAll("\\@start_year", startDateComp[2]);
		channelXML = channelXML.replaceAll("\\@start_month", startDateComp[1]);
		channelXML = channelXML.replaceAll("\\@start_day", startDateComp[0]);
		
		String[] endDateComp = ((String)data.get("endDate")).substring(4).split("-");
		channelXML = channelXML.replaceAll("\\@end_year", endDateComp[2]);
		channelXML = channelXML.replaceAll("\\@end_month", endDateComp[1]);
		channelXML = channelXML.replaceAll("\\@end_day", endDateComp[0]);
		
		channelXML = channelXML.replaceAll("\\@actor", (String)data.get("IHEActor"));
		
		return channelXML;
	}

	private String loadChannelTemplate() throws WizardException {
		BufferedInputStream in = new BufferedInputStream(ADTToIHEXDSWizardPlugin.class.getResourceAsStream(IHE_XDS_QUERY_CHANNEL_TEMPLATE_XML));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		 
		String line;
		StringBuilder channelXMLBuilder = new StringBuilder();
		try {
			while ((line = br.readLine()) != null)
			{
				channelXMLBuilder.append(line + "\n");
			}
			br.close();
			in.close();
		} catch (IOException e) {
			throw new WizardException(e.getMessage());
		}
		
		String channelXML = channelXMLBuilder.toString();
		return channelXML;
	}
}