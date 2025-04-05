package com.webreach.mirth.plugins.ihepixwizard;

import java.awt.Rectangle;
import java.util.List;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.WizardPage;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.client.ui.Frame;
import com.webreach.mirth.client.ui.PlatformUI;
import com.webreach.mirth.model.Channel;
import com.webreach.mirth.plugins.ChannelWizardPlugin;

public class ADTToIHEPIXWizardPlugin extends ChannelWizardPlugin {
	public ADTToIHEPIXWizardPlugin(String name) {
		super(name);
	}

	@Override
	public Channel runWizard() {
		Frame parent = PlatformUI.MIRTH_FRAME;

		try {	
	
	        if (!parent.mirthClient.isExtensionEnabled("IHE Sender")) {
	        	this.alertError("The IHE Sender connector must be installed and enabled to use this wizard.");
	        	return null;
	        }
	        if (!parent.mirthClient.isExtensionEnabled("IHE Configuration")) {
	        	this.alertError("The IHE Configuration plugin must be installed and enabled to use this wizard.");
	        	return null;
	        }
            List<String> actorList = (List<String>) PlatformUI.MIRTH_FRAME.mirthClient.invokePluginMethod("IHE Configuration", "getActorNames", null);
            if(actorList == null || actorList.size() == 0) {
            	this.alertError("The IHE Configuration plugin must have a configuration loaded with actors to use this wizard.");
            	return null;
            }
		} catch (ClientException e) {
			this.alertException(e.getStackTrace(), e.getMessage());
		}
		
		// All we do here is assemble the list of WizardPage subclasses we
		// want to show:
		Class[] pages = new Class[] { SourceConnectorPage.class, PatientDescriptorPage.class, DestinationConnectorPage.class };

		Object object = WizardDisplayer.showWizard(WizardPage.createWizard(pages, new WizardChannelProducer(this)), new Rectangle(20, 20, 600, 400));

		return (Channel) object;
	}
}
