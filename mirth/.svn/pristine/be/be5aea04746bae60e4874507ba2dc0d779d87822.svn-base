package com.webreach.mirth.plugins.ihepixwizard;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPage;

import com.webreach.mirth.connectors.ihe.IheSenderProperties;
import com.webreach.mirth.connectors.mllp.LLPListenerProperties;
import com.webreach.mirth.model.Channel;
import com.webreach.mirth.model.Connector;
import com.webreach.mirth.model.Step;

public class WizardChannelProducer implements WizardPage.WizardResultProducer {

	private ADTToIHEPIXWizardPlugin wizard;
	
	public WizardChannelProducer(ADTToIHEPIXWizardPlugin wizard) {
		this.wizard = wizard;
	}
	
	public boolean cancel(Map data) {
		return wizard.alertOption("Are you should you would like to cancel the wizard?");
	}

	public Object finish(Map data) throws WizardException {
		Channel channel = wizard.getDefaultNewChannel();
			
		channel.setName("ADT over port " + (String)data.get("listenerPort") + " to IHE PIX");
		
		Properties sourceProperties = new LLPListenerProperties().getDefaults();
		sourceProperties.put(LLPListenerProperties.LLP_PORT, (String)data.get("listenerPort"));
		channel.getSourceConnector().setProperties(sourceProperties);
		
		Connector destinationConnector = channel.getDestinationConnectors().get(0);
		destinationConnector.setName("Send patient to PIX via IHE");
		destinationConnector.setTransportName(IheSenderProperties.name);
		
		Properties destinationProperties = new IheSenderProperties().getDefaults();
		
		destinationProperties.setProperty(IheSenderProperties.JAVASCRIPT_SCRIPT, getPIXSubmit(data));
		
		destinationConnector.setProperties(destinationProperties);
		
		List<Step> transformerSteps = destinationConnector.getTransformer().getSteps();
		
		Step javascriptStep = new Step();
		javascriptStep.setSequenceNumber(0);
		javascriptStep.setType("JavaScript");
		javascriptStep.setName("Build PatientDescriptor Object");
		
		String patientDescriptor = getPatientDescriptor(data);
		
		javascriptStep.setScript(patientDescriptor);
		
		Map<String,String> javascriptStepData = new HashMap<String,String>();
		javascriptStepData.put("Script", patientDescriptor);
		
		javascriptStep.setData(javascriptStepData);
		
		transformerSteps.add(javascriptStep);
		
		destinationConnector.getTransformer().setSteps(transformerSteps);
		
		channel.setLastModified(Calendar.getInstance());
		
		return channel;
	}
	
	private String getPatientDescriptor(Map data)  {
		StringBuilder buildPatientDescriptor = new StringBuilder();

        buildPatientDescriptor.append("// misys\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.base.clinicaldata);\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.base.demographicdata);\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.base.codemapping);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.ihe);\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.ihe.configuration);\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.ihe.unitest);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("importPackage(Packages.com.misyshealthcare.connect.net);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// micon\n");
        buildPatientDescriptor.append("importPackage(Packages.mesatests);\n");
        buildPatientDescriptor.append("importPackage(Packages.mesatests.xds);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// java\n");
        buildPatientDescriptor.append("importPackage(Packages.java.io);\n");
        buildPatientDescriptor.append("importPackage(Packages.java.net);\n");
        buildPatientDescriptor.append("importPackage(Packages.java.util);\n");
        buildPatientDescriptor.append("importPackage(Packages.java.text);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Patient Id information\n");
        buildPatientDescriptor.append("var pid = new PatientID(msg['PID']['PID.3']['PID.3.1'].toString());\n");
        buildPatientDescriptor.append("pid.addId(" + (String)data.get("assigningAuthority") + ", " + (String)data.get("assigningAuthorityId") + ");\n"); // INSERT
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create a new PatientDescriptor\n");
        buildPatientDescriptor.append("var pd = new PatientDescriptor(pid);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Set the Name information\n");
        buildPatientDescriptor.append("pd.setNameLast(msg['PID']['PID.5']['PID.5.1'].toString());\n");
        buildPatientDescriptor.append("pd.setNameFirst(msg['PID']['PID.5']['PID.5.2'].toString());\n");
        buildPatientDescriptor.append("pd.setMotherMaidenName(msg['PID']['PID.6']['PID.6.1'].toString());\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Setup the DOB\n");
        buildPatientDescriptor.append(" var dateFormatter = new SimpleDateFormat(\"yyyy-MM-dd\");\n");
        buildPatientDescriptor.append("pd.setBirthDateTime(dateFormatter.parse(msg['PID']['PID.7']['PID.7.1'].toString()));\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Set the gender info\n");
        buildPatientDescriptor.append("pd.setAdministrativeSex(msg['PID']['PID.8']['PID.8.1'].toString());\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their primary address\n");
        buildPatientDescriptor.append("var address = new Address();\n");
        buildPatientDescriptor.append("address.setAddType(Packages.com.misyshealthcare.connect.base.SharedEnums.AddressType.OTHER);\n");
        buildPatientDescriptor.append("address.setAddLine1(msg['PID']['PID.11']['PID.11.1'].toString());\n");
        buildPatientDescriptor.append("address.setAddCity(msg['PID']['PID.11']['PID.11.3'].toString());\n");
        buildPatientDescriptor.append("address.setAddState(msg['PID']['PID.11']['PID.11.4'].toString());\n");
        buildPatientDescriptor.append("address.setAddZip(msg['PID']['PID.11']['PID.11.5'].toString());\n");
        buildPatientDescriptor.append("pd.addAddress(address);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their Home Phone\n");
        buildPatientDescriptor.append("var phoneHome = msg['PID']['PID.13']['PID.13.1'].toString();\n");
        buildPatientDescriptor.append("phoneHome = phoneHome.replace(/-|\\s|\\(|\\)/g,'');\n");
        buildPatientDescriptor.append("if (phoneHome.length == 11) {\n");
        buildPatientDescriptor.append("    phoneHome = phoneHome.substring(1);\n");
        buildPatientDescriptor.append("}\n");
        buildPatientDescriptor.append("if (phoneHome.length == 9) {\n");
        buildPatientDescriptor.append("    var phone = new PhoneNumber();\n");
        buildPatientDescriptor.append("    phone.setType(Packages.com.misyshealthcare.connect.base.SharedEnums.PhoneType.HOME);\n");
        buildPatientDescriptor.append("    phone.setNumber(phoneHome.substring(3));\n");
        buildPatientDescriptor.append("    phone.setAreaCode(phoneHome.substring(0,3));\n");
        buildPatientDescriptor.append("    pd.addPhoneNumber(phone);\n");
        buildPatientDescriptor.append("}\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their Work Phone\n");
        buildPatientDescriptor.append("var phoneWork = msg['PID']['PID.13']['PID.13.1'].toString();\n");
        buildPatientDescriptor.append("phoneWork = phoneWork.replace(/-|\\s|\\(|\\)/g,'');\n");
        buildPatientDescriptor.append("if (phoneWork.length == 11) {\n");
        buildPatientDescriptor.append("    phoneWork = phoneWork.substring(1);\n");
        buildPatientDescriptor.append("}\n");
        buildPatientDescriptor.append("if (phoneWork.length == 9) {\n");
        buildPatientDescriptor.append("    var phone = new PhoneNumber();\n");
        buildPatientDescriptor.append("    phone.setType(Packages.com.misyshealthcare.connect.base.SharedEnums.PhoneType.WORK);\n");
        buildPatientDescriptor.append("    phone.setNumber(phoneWork.substring(3));\n");
        buildPatientDescriptor.append("    phone.setAreaCode(phoneWork.substring(0,3));\n");
        buildPatientDescriptor.append("    pd.addPhoneNumber(phone);\n");
        buildPatientDescriptor.append("}\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Setup some alternate ID information\n");
        buildPatientDescriptor.append("pd.setSsn(msg['PID']['PID.19']['PID.19.1'].toString());\n");
        buildPatientDescriptor.append("pd.setDriverLicense(msg['PID']['PID.20']['PID.20.1'].toString());\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("if (msg['PV1'] != undefined) {\n");
        buildPatientDescriptor.append("    // Setup the Visit(Encounter) info, establishing the Provider info for\n");
        buildPatientDescriptor.append("    // the Patient\n");
        buildPatientDescriptor.append("    var visit = new Visit(" + (String)data.get("visitSystemId") + ", " + (String)data.get("visitId") + ");\n"); // INSERT
        buildPatientDescriptor.append("    // Setup the basics here\n");
        buildPatientDescriptor.append("    visit.setSystemPatientId(msg['PID']['PID.3']['PID.3.1'].toString());\n");
        buildPatientDescriptor.append("    var dateFormatter = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.SSS Z\");\n");
        buildPatientDescriptor.append("    visit.setVisitStartTimestamp(dateFormatter.parse(msg['PV1']['PV1.44']['PV1.44.1'].toString()));\n");
        buildPatientDescriptor.append("    visit.setReason(msg['PV2']['PV2.3']['PV2.3.2'].toString());\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("    // Create the Provider\n");
        buildPatientDescriptor.append("    var provider = new Provider();\n");
        buildPatientDescriptor.append("    provider.setProviderId(msg['PV1']['PV1.7']['PV1.7.1'].toString());\n");
        buildPatientDescriptor.append("    provider.setProvNameTitle(msg['PV1']['PV1.7']['PV1.7.6'].toString());\n");
        buildPatientDescriptor.append("    provider.setProvNameFirst(msg['PV1']['PV1.7']['PV1.7.3'].toString());\n");
        buildPatientDescriptor.append("    provider.setProvNameMiddle(msg['PV1']['PV1.7']['PV1.7.4'].toString());\n");
        buildPatientDescriptor.append("    provider.setProvNameLast(msg['PV1']['PV1.7']['PV1.7.2'].toString());\n");
        buildPatientDescriptor.append("    visit.addProvider(provider);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("    pd.addVisit(visit);\n");
        buildPatientDescriptor.append("}\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("channelMap.put(\"pd\", pd);\n");
        
        return buildPatientDescriptor.toString();
	}
	
	private String getPIXSubmit(Map data) {
		StringBuilder pixSubmit = new StringBuilder();
		
		pixSubmit.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
		pixSubmit.append("\n");
		pixSubmit.append("var actor = \"" + (String)data.get("IHEActor") + "\";\n");
		pixSubmit.append("var actors = new ArrayList();\n");
        pixSubmit.append("actors.add(actor);\n");
        pixSubmit.append("configuration.resetConfiguration(actors);\n");
        pixSubmit.append("\n" );
        pixSubmit.append("var ssmd = new SubmissionSetMetaData();\n");
        pixSubmit.append("PatientBroker.getInstance().createPatient($('pd'), IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);\n");
		
		return pixSubmit.toString();
	}
}