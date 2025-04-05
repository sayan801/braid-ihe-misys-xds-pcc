/*
 * IheConfigurationClient.java
 *
 * Created on June 22, 2007, 5:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.webreach.mirth.plugins.ihe;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.jdesktop.swingworker.SwingWorker;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.client.ui.panels.reference.ReferenceListFactory.ContextType;
import com.webreach.mirth.model.CodeTemplate;
import com.webreach.mirth.model.CodeTemplate.CodeSnippetType;
import com.webreach.mirth.plugins.ClientPanelPlugin;

/**
 * 
 * @author brendanh
 */
public class IheConfigurationClient extends ClientPanelPlugin
{
    public IheConfigurationClient(String name)
    {
        super(name);

        getTaskPane().setTitle("IHE Tasks");
        setComponent(new IheConfigurationPanel());
        addTask("doRefresh", "Refresh", "Refresh configuration settings", "", new ImageIcon(com.webreach.mirth.client.ui.Frame.class.getResource("images/refresh.png")));
        addTask("doReloadConfiguration", "Reload Configuration", "Reload the configuration file", "", new ImageIcon(com.webreach.mirth.client.ui.Frame.class.getResource("images/deploy.png")));
        addTask("doSave", "Save", "Save configuration settings", "", new ImageIcon(com.webreach.mirth.client.ui.Frame.class.getResource("images/save.png")));
        getComponent().addMouseListener(getPopupMenuMouseAdapter());
    }

    public ArrayList<CodeTemplate> getReferenceItems()
    {
        ArrayList<CodeTemplate> variablelistItems = new ArrayList<CodeTemplate>();

        variablelistItems.add(new CodeTemplate("IHE Configuration Loader", "Get the IHE configuration loader variable", "var config = ConfigurationLoader.getInstance();", CodeSnippetType.VARIABLE, ContextType.GLOBAL_CONTEXT.getContext()));
        variablelistItems.add(new CodeTemplate("Reset IHE Configuration", "Reset the IHE configuration with a list of actors to use", "ConfigurationLoader.getInstance().resetConfiguration(\"actors\")", CodeSnippetType.FUNCTION, ContextType.GLOBAL_CONTEXT.getContext()));

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
        buildPatientDescriptor.append("var pid = new PatientID('pid');\n");
        buildPatientDescriptor.append("pid.addId(\"id_type\", \"id\");\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create a new PatientDescriptor\n");
        buildPatientDescriptor.append("var pd = new PatientDescriptor(pid);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Set the Name information\n");
        buildPatientDescriptor.append("pd.setNameLast('lastName');\n");
        buildPatientDescriptor.append("pd.setNameFirst('firstName');\n");
        buildPatientDescriptor.append("pd.setMotherMaidenName('motherMaiden');\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Setup the DOB\n");
        buildPatientDescriptor.append(" var dateFormatter = new SimpleDateFormat(\"yyyy-MM-dd\");\n");
        buildPatientDescriptor.append("pd.setBirthDateTime(dateFormatter.parse('rawDOB'));\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Set the gender info\n");
        buildPatientDescriptor.append("pd.setAdministrativeSex('administrativeSex');\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their primary address\n");
        buildPatientDescriptor.append("var address = new Address();\n");
        buildPatientDescriptor.append("address.setAddType(Packages.com.misyshealthcare.connect.base.SharedEnums.AddressType.OTHER);\n");
        buildPatientDescriptor.append("address.setAddLine1('addLine1');\n");
        buildPatientDescriptor.append("address.setAddCity('addCity');\n");
        buildPatientDescriptor.append("address.setAddState('addState');\n");
        buildPatientDescriptor.append("address.setAddZip('addZip');\n");
        buildPatientDescriptor.append("pd.addAddress(address);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their Home Phone\n");
        buildPatientDescriptor.append("var phone = new PhoneNumber();\n");
        buildPatientDescriptor.append("phone.setType(Packages.com.misyshealthcare.connect.base.SharedEnums.PhoneType.HOME);\n");
        buildPatientDescriptor.append("phone.setNumber('homePhoneNumber');\n");
        buildPatientDescriptor.append("phone.setAreaCode('homePhoneArea');\n");
        buildPatientDescriptor.append("pd.addPhoneNumber(phone);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create their Work Phone\n");
        buildPatientDescriptor.append("phone = new PhoneNumber();\n");
        buildPatientDescriptor.append("phone.setType(Packages.com.misyshealthcare.connect.base.SharedEnums.PhoneType.WORK);\n");
        buildPatientDescriptor.append("phone.setNumber('workPhoneNumber');\n");
        buildPatientDescriptor.append("phone.setAreaCode('workPhoneArea');\n");
        buildPatientDescriptor.append("pd.addPhoneNumber(phone);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Setup some alternate ID information\n");
        buildPatientDescriptor.append("pd.setSsn('ssn');\n");
        buildPatientDescriptor.append("pd.setDriverLicense('driverLicense');\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Setup the Visit(Encounter) info, establishing the Provider info for\n");
        buildPatientDescriptor.append("// the Patient\n");
        buildPatientDescriptor.append("var visit = new Visit(\"location\", \"id\");\n");
        buildPatientDescriptor.append("// Setup the basics here\n");
        buildPatientDescriptor.append("visit.setSystemPatientId('pid'));\n");
        buildPatientDescriptor.append("var dateFormatter = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.SSS Z\");\n");
        buildPatientDescriptor.append("visit.setVisitStartTimestamp(dateFormatter.parse('datePatientCreated'));\n");
        buildPatientDescriptor.append("visit.setReason(\"reason\");\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("// Create the Provider\n");
        buildPatientDescriptor.append("var provider = new Provider();\n");
        buildPatientDescriptor.append("provider.setProviderId(\"id\");\n");
        buildPatientDescriptor.append("provider.setProvNameTitle(\"title\");\n");
        buildPatientDescriptor.append("provider.setProvNameFirst(\"first\");\n");
        buildPatientDescriptor.append("provider.setProvNameMiddle(\"middle\");\n");
        buildPatientDescriptor.append("provider.setProvNameLast(\"last\");\n");
        buildPatientDescriptor.append("visit.addProvider(provider);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("pd.addVisit(visit);\n");
        buildPatientDescriptor.append("\n");
        buildPatientDescriptor.append("channelMap.put(\"pd\", pd);\n");
        variablelistItems.add(new CodeTemplate("Build Patient Descriptor", "Build patient descriptor object for IHE transactions", buildPatientDescriptor.toString(), CodeSnippetType.FUNCTION, ContextType.GLOBAL_CONTEXT.getContext()));
        
        StringBuilder buildReferralSummary = new StringBuilder();
        buildReferralSummary.append("importPackage(Packages.com.misyshealthcare.connect.doc.ccd);\n");
        buildReferralSummary.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
        buildReferralSummary.append("importPackage(Packages.java.util);\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("var rightNow = GregorianCalendar.getInstance();\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function findMostRecentReferralEncounter(patientXML) {\n");
        buildReferralSummary.append("\tfor each(encounter in patientXML['patient']['encounterList'].children()) {\n");
        buildReferralSummary.append("\t\tif (encounter['@typeName'].equals(\"SPECIALIST_REFERRAL\")) {\n");
        buildReferralSummary.append("\t\t\treturn encounter;\n");
        buildReferralSummary.append("\t\t}\n");
        buildReferralSummary.append("\t}\n");
        buildReferralSummary.append("\treturn null;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function findReferralReason(referralEncounter) {\n");
        buildReferralSummary.append("\tfor each(observation in referralEncounter['observationList'].children()) {\n");
        buildReferralSummary.append("\t\tif (observation['concept']['name']['shortName'].equals(\"REFREASON\")) {\n");
        buildReferralSummary.append("\t\t\treturn observation['value']['localizedValueAsString'].toString();\n");
        buildReferralSummary.append("\t\t}\n");
        buildReferralSummary.append("\t}\n");
        buildReferralSummary.append("\treturn null;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getAllergies() {\n");
        buildReferralSummary.append("\tvar allergies = [];\n");
        buildReferralSummary.append("\tallergies[0] = new Allergy(\"Iodinated contrast\", \"laryngeal oedema\", null, null, rightNow, null);\n");
        buildReferralSummary.append("\treturn allergies;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getReviewOfSystem() {\n");
        buildReferralSummary.append("\tvar reviews = [];\n");
        buildReferralSummary.append("\treviews[0] = \"System Review 1\";\n");
        buildReferralSummary.append("\treviews[1] = \"System Review 2\";\n");
        buildReferralSummary.append("\treturn reviews;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");  
        buildReferralSummary.append("function getVitalSigns() {\n");
        buildReferralSummary.append("\tvar measurements = [];\n");
        buildReferralSummary.append("\tmeasurements[0] = new Measurements(rightNow, \"Temperature\", \"Head\", \"Home\",\"97\", \"F\", null, \"fever\", null);\n");
        buildReferralSummary.append("\treturn measurements;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getProcedures() {\n");
        buildReferralSummary.append("\tvar procedures = [];\n");
        buildReferralSummary.append("\tprocedures[0] = new Procedure(rightNow, \"Total hip replacement, left\", new Code(\"52734007\", null, \"LOINC\", null), SharedEnums.StatusCode.COMPLETED, null, null);\n");
        buildReferralSummary.append("\treturn procedures;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getPhysicalExams() {\n");
        buildReferralSummary.append("\tvar exams = [];\n");
        buildReferralSummary.append("\texams[0] = \"PhysicalExam 1\";\n");
        buildReferralSummary.append("\texams[1] = \"PhysicalExam 2\";\n");
        buildReferralSummary.append("\treturn exams;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getHistoryOfPresentIllness() {\n");
        buildReferralSummary.append("\tvar histories = [];\n");
        buildReferralSummary.append("\thistories[0] = \"Present Illness 1\";\n");
        buildReferralSummary.append("\thistories[1] = \"Present Illness 2\";\n");
        buildReferralSummary.append("\treturn histories;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getSocialHistory() {\n");
        buildReferralSummary.append("\tvar histories = [];\n");
        buildReferralSummary.append("\thistories[0] = \"Social history 1\";\n");
        buildReferralSummary.append("\thistories[1] = \"Social history 2\";\n");
        buildReferralSummary.append("\treturn histories;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getFamilyHistory() {\n");
        buildReferralSummary.append("\tvar histories = [];\n");
        buildReferralSummary.append("\thistories[0] = \"Family history 1\";\n");
        buildReferralSummary.append("\thistories[1] = \"Family history 2\";\n");
        buildReferralSummary.append("\treturn histories;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getAdvanceDirectives() {\n");
        buildReferralSummary.append("\tvar ads = [];\n");
        buildReferralSummary.append("\tads[0] = new AdvanceDirective(\"Do not resuscitate\", new Code(\"304251008\", null, \"2.16.840.1.113883.6.96\", null), rightNow, \"completed\", \"No comment\", null);\n");
        buildReferralSummary.append("\treturn ads;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getPlanOfCareItems() {\n");
        buildReferralSummary.append("\tcares = [];\n");
        buildReferralSummary.append("\tcares[0] = \"PlanOfCare 1\";\n");
        buildReferralSummary.append("\tcares[1] = \"PlanOfCare 2\";\n");
        buildReferralSummary.append("\treturn cares;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getImmunizations() {\n");
        buildReferralSummary.append("\tvar immunizations = [];\n");
        buildReferralSummary.append("\timmunizations[0] = new Immunization(rightNow, \"Influenza virus vaccine, IM\", new Code(\"88\", null, \"LOINC\", null),\n");
        buildReferralSummary.append("\tnew Code(\"14628\", \"Oral\", \"RouteOfAdministration\", null), new DoseQuantity(new Quantity(\"1\", null), null), null, \"comment\", \"reference\", null);\n");
        buildReferralSummary.append("\treturn immunizations;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");  
        buildReferralSummary.append("function getSurgicalHistory() {\n");
        buildReferralSummary.append("\tvar surgeries = [];\n");
        buildReferralSummary.append("\tsurgeries[0] = \"Surgery history 1\";\n");
        buildReferralSummary.append("\tsurgeries[1] = \"Surgery history 2\";\n");
        buildReferralSummary.append("\treturn surgeries;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getMedications() {\n");
        buildReferralSummary.append("\tvar medications = [];\n");
        buildReferralSummary.append("\tmedications[0] = new Medication(\"Amoxicillin (250 mg tabs)\", Calendar.getInstance(), null, new Code(\"472.0\", null, \"ICD9\", null), \"3 times per day  ora\", new Code(\"14735\", \"Oral\", \"RouteOfAdministration\", null), new DoseQuantity(new Quantity(\"1\", \"tablet\"), null), \"t.i.d\", null, \"\", null, \"active\", null);\n");
        buildReferralSummary.append("\treturn medications;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getProblems() {\n");
        buildReferralSummary.append("\tvar problems = [];\n");
        buildReferralSummary.append("\tproblems[0] = new SimpleProblem(\"CHRONIC PHARYNGITIS AND NASOPHARYNGITIS\", new Code(\"472.0\", null, \"ICD9\", null), rightNow, null, SharedEnums.ClinicalStatusCode.RESOLVED, \"Misys CPR\", \"This is comment1\n");
        buildReferralSummary.append("\tproblems[1] = new SimpleProblem(\"Problem2\", new Code(\"222.2\", null, \"ICD9\", null), rightNow, null, SharedEnums.ClinicalStatusCode.ACTIVE, \"Misys CPR\", \"This is comment2\n");
        buildReferralSummary.append("\treturn problems;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function createMetaData() {\n");
        buildReferralSummary.append("\tvar md = new MetaData();\n");
        buildReferralSummary.append("\tvar author = new Author();\n");
        buildReferralSummary.append("\tvar authorOrg = new Organization();\n");
        buildReferralSummary.append("\tauthorOrg.setOrganizationName(\"Source Organization");
        buildReferralSummary.append("\tvar orgAddr = buildAddress(\"123 Main\",null,\"Aliso Viejo\",\"CA\",\"92656\",\"US\n");
        buildReferralSummary.append("\tauthorOrg.setAddress([orgAddr]);\n");
        buildReferralSummary.append("\tauthorOrg.setId(new Id(\"2.8.233.3\", \"12345\"));\n");
        buildReferralSummary.append("\tauthor.setOrganization(authorOrg);\n");
        buildReferralSummary.append("\tvar authorPerson = new Participant();\n");
        buildReferralSummary.append("\tauthorPerson.setId(new Id(\"2.3.515.3\", \"12345\"));\n");
        buildReferralSummary.append("\tauthorPerson.setAddress([orgAddr]);\n");
        buildReferralSummary.append("\tauthorPerson.setPersonName(buildPersonaName(\"John\",\"Jones\",null,\"Dr.\",null));\n");
        buildReferralSummary.append("\tauthorPerson.setPhoneNumbers([buildPhoneNumber(\"949\",\"2555053\",SharedEnums.PhoneType.WORK)]);\n");
        buildReferralSummary.append("\tauthor.setAuthorPerson(authorPerson);\n");
        buildReferralSummary.append("\tmd.setAuthors([author]);\n");
        buildReferralSummary.append("\tmd.setClassCode(SharedEnums.XdsClassCode.Transfer_Summarization);\n");
        buildReferralSummary.append("\tmd.setComment(\"Patient has severe Chest pain\n");
        buildReferralSummary.append("\tmd.setConfidentialityCodes([SharedEnums.ConfidentialityCode.Restricted]);\n");
        buildReferralSummary.append("\tmd.setCustodian(getCustodian());\n");
        buildReferralSummary.append("\tmd.setFacilityTypeCode(\"Outpatient\n");
        buildReferralSummary.append("\tmd.setFormatCode(SharedEnums.XdsFormatCode.XDS_MS);\n");
        buildReferralSummary.append("\tmd.setPerformers([getPerformer()]);\n");
        buildReferralSummary.append("\tmd.setHomeSystemId(\"EMR-MISYSCONNECT\n");
        buildReferralSummary.append("\tmd.setServiceStartTime(rightNow );\n");
        buildReferralSummary.append("\tmd.setSourcePatientId(new Id(\"254.345.22.15.3\", $('patientId')));\n");
        buildReferralSummary.append("\tmd.setSourcePatientInfo(getPatientInfo());\n");
        buildReferralSummary.append("\tmd.setTitle(\"Sample Referral Summary Document\n");
        buildReferralSummary.append("\treturn md;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function buildAddress(line1,line2,city,state,zip,country) {\n");
        buildReferralSummary.append("\tvar addr = new Address();\n");
        buildReferralSummary.append("\taddr.setAddLine1(line1);\n");
        buildReferralSummary.append("\taddr.setAddLine2(line2);\n");
        buildReferralSummary.append("\taddr.setAddCity(city);\n");
        buildReferralSummary.append("\taddr.setAddState(state);\n");
        buildReferralSummary.append("\taddr.setAddZip(zip);\n");
        buildReferralSummary.append("\taddr.setAddCountry(country);\n");
        buildReferralSummary.append("\treturn addr;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function buildPersonaName(first,last,middle,prefix,suffix) {\n");
        buildReferralSummary.append("\tvar name = new PersonName();\n");
        buildReferralSummary.append("\tname.setFirstName(first);\n");
        buildReferralSummary.append("\tname.setLastName(last);\n");
        buildReferralSummary.append("\tname.setMiddleName(middle);\n");
        buildReferralSummary.append("\tname.setPrefix(prefix);\n");
        buildReferralSummary.append("\tname.setSuffix(suffix);\n");
        buildReferralSummary.append("\treturn name;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function buildPhoneNumber(areacode,number,type) {\n");
        buildReferralSummary.append("\tvar phone = new PhoneNumber();\n");
        buildReferralSummary.append("\tphone.setCountryCode(\"1\n");
        buildReferralSummary.append("\tphone.setAreaCode(areacode);\n");
        buildReferralSummary.append("\tphone.setNumber(number);\n");
        buildReferralSummary.append("\tphone.setType(type);\n");
        buildReferralSummary.append("\treturn phone;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function dateToCalendar(date) {\n");
        buildReferralSummary.append("\tvar cal = new GregorianCalendar();\n");
        buildReferralSummary.append("\tcal.setTime(date);\n");
        buildReferralSummary.append("\treturn cal;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getPatientInfo() {\n");
        buildReferralSummary.append("\tvar pi = new SourcePatientInfo();\n");
        buildReferralSummary.append("\tvar incomingPat = $('pd');\n");
        buildReferralSummary.append("\tpi.setAddress([incomingPat.getAddressList().get(0)]);\n");
        buildReferralSummary.append("\tpi.setBirthdate(dateToCalendar(incomingPat.getBirthDateTime()));\n");
        buildReferralSummary.append("\tpi.setGender(incomingPat.getAdministrativeSex());\n");
        buildReferralSummary.append("\tpi.setPersonName(buildPersonaName(incomingPat.getNameFirst(),incomingPat.getNameLast(),incomingPat.getNameMiddle(),incomingPat.getNameTitle(),incomingPat.getNameSuffix()));\n");
        buildReferralSummary.append("\tpi.setPhoneNumber([incomingPat.getPhoneList().get(0)]);\n");
        buildReferralSummary.append("\treturn pi;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");  
        buildReferralSummary.append("function getPerformer() {\n");
        buildReferralSummary.append("\tvar performer = new Performer();\n");
        buildReferralSummary.append("\tperformer.setAddress([getAddress()]);\n");
        buildReferralSummary.append("\tperformer.setPhoneNumbers([getPhoneNumber()]);\n");
        buildReferralSummary.append("\tperformer.setStartTime(rightNow);\n");
        buildReferralSummary.append("\tperformer.setPersonName(new PersonName(\"Joe\", \"Atkinson\", null, null, null));\n");
        buildReferralSummary.append("\treturn performer;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");    
        buildReferralSummary.append("function getPatientContacts() {\n");
        buildReferralSummary.append("\tvar pc = new PatientContact();\n");
        buildReferralSummary.append("\tpc.setEffectiveTime(new EffectiveTime(rightNow, null));\n");
        buildReferralSummary.append("\tpc.setParticipant(getParticipant());\n");
        buildReferralSummary.append("\tpc.setPatientContactType(SharedEnums.PatientContactType.GUARDIAN);\n");
        buildReferralSummary.append("\tvar pc2 = new PatientContact();\n");
        buildReferralSummary.append("\tpc2.setEffectiveTime(new EffectiveTime(rightNow, null));\n");
        buildReferralSummary.append("\tpc2.setParticipant(getParticipant());\n");
        buildReferralSummary.append("\tpc2.setPatientContactType(SharedEnums.PatientContactType.CARE_GIVERS);\n");
        buildReferralSummary.append("\treturn [pc,pc2];\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");    
        buildReferralSummary.append("function getCustodian() {\n");
        buildReferralSummary.append("\tvar org = new Organization();\n");
        buildReferralSummary.append("\torg.setOrganizationName(\"Great NC RHIO\n");
        buildReferralSummary.append("\t//org.setOrganization(getParticipant());\n");
        buildReferralSummary.append("\treturn org;\n");
        buildReferralSummary.append("}");
        buildReferralSummary.append("\n");  
        buildReferralSummary.append("function getParticipant() {\n");
        buildReferralSummary.append("\tvar ap = new Participant();\n");
        buildReferralSummary.append("\tap.setAddress([getAddress()]);\n");
        buildReferralSummary.append("\tap.setPhoneNumbers([getPhoneNumber()]);\n");
        buildReferralSummary.append("\tap.setPersonName(getPersonaName(\"John\"));\n");
        buildReferralSummary.append("\tap.setId(new Id(\"2.3.515.3\", \"12345\"));\n");
        buildReferralSummary.append("\treturn ap;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");    
        buildReferralSummary.append("function getPersonaName(firstName) {\n");
        buildReferralSummary.append("\tvar name = new PersonName();\n");
        buildReferralSummary.append("\tname.setFirstName(firstName);\n");
        buildReferralSummary.append("\tname.setLastName(\"DePinto\n");
        buildReferralSummary.append("\tname.setMiddleName(\"F\n");
        buildReferralSummary.append("\tname.setPrefix(\"Mr.\n");
        buildReferralSummary.append("\tname.setSuffix(\"II\n");
        buildReferralSummary.append("\treturn name;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getAddress() {\n");
        buildReferralSummary.append("\tvar addr = new Address();\n");
        buildReferralSummary.append("\taddr.setAddLine1(\"700 Euclid Ave\n");
        buildReferralSummary.append("\taddr.setAddCity(\"St. Louis\n");
        buildReferralSummary.append("\taddr.setAddState(\"MO\n");
        buildReferralSummary.append("\taddr.setAddZip(\"63110\n");
        buildReferralSummary.append("\taddr.setAddCountry(\"USA\n");
        buildReferralSummary.append("\treturn addr;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("function getPhoneNumber() {\n");
        buildReferralSummary.append("\tvar phone = new PhoneNumber();\n");
        buildReferralSummary.append("\tphone.setCountryCode(\"1\n");
        buildReferralSummary.append("\tphone.setAreaCode(\"716\n");
        buildReferralSummary.append("\tphone.setNumber(\"358-7421\n");
        buildReferralSummary.append("\tphone.setType(SharedEnums.PhoneType.WORK);\n");
        buildReferralSummary.append("\treturn phone;\n");
        buildReferralSummary.append("}\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("var refSummary = new ReferralSummaryData();\n");
        buildReferralSummary.append("var referralEncounter = findMostRecentReferralEncounter($('patientInfo'));\n");
        buildReferralSummary.append("refSummary.setReferralReason(findReferralReason(referralEncounter));\n");
        buildReferralSummary.append("refSummary.setAllergies(getAllergies(referralEncounter));\n");
        buildReferralSummary.append("refSummary.setMetadata(createMetaData());\n");
        buildReferralSummary.append("refSummary.setActiveProblems(getProblems());\n");
        buildReferralSummary.append("refSummary.setMedications(getMedications());\n");
        buildReferralSummary.append("refSummary.setSurgicalHistories(getSurgicalHistory());\n");
        buildReferralSummary.append("refSummary.setHistoryOfPresentIllness(getHistoryOfPresentIllness());\n");
        buildReferralSummary.append("refSummary.setImmunizations(getImmunizations());\n");
        buildReferralSummary.append("refSummary.setFamilyHistories(getFamilyHistory());\n");
        buildReferralSummary.append("refSummary.setSocialHistories(getSocialHistory());\n");
        buildReferralSummary.append("refSummary.setReviewOfSystems(getReviewOfSystem());\n");
        buildReferralSummary.append("refSummary.setMeasurements(getVitalSigns());\n");
        buildReferralSummary.append("refSummary.setPhysicalExams(getPhysicalExams());\n");
        buildReferralSummary.append("refSummary.setProcedures(getProcedures());\n");
        buildReferralSummary.append("refSummary.setPlanOfCares(getPlanOfCareItems());\n");
        buildReferralSummary.append("refSummary.setAdvanceDirectives(getAdvanceDirectives());\n");
        buildReferralSummary.append("\n");
        buildReferralSummary.append("channelMap.put('referralSummaryCCD', refSummary);");
        variablelistItems.add(new CodeTemplate("Build Referral Summary", "Build a petient referral summary", buildReferralSummary.toString(), CodeSnippetType.FUNCTION, ContextType.GLOBAL_CONTEXT.getContext()));

        return variablelistItems;
    }

    public void doRefresh()
    {
        setWorking("Loading configuration properties...", true);

        SwingWorker worker = new SwingWorker<Void, Void>()
        {
            public Void doInBackground()
            {
                try
                {
                    refresh();
                }
                catch (ClientException e)
                {
                    alertException(e.getStackTrace(), e.getMessage());
                }
                return null;
            }

            public void done()
            {
                setWorking("", false);
            }
        };

        worker.execute();
    }

    public void doReloadConfiguration()
    {
        setWorking("Reloading configuration...", true);

        SwingWorker worker = new SwingWorker<Void, Void>()
        {
            public Void doInBackground()
            {
                try
                {
                    save();
                    boolean returnValue = ((Boolean)invoke("reloadConfiguration", null)).booleanValue();
                    if(returnValue)
                        refresh();
                    else
                        alertError("Could not load configuration file.");
                }
                catch (ClientException e)
                {
                    alertException(e.getStackTrace(), e.getMessage());
                }
                return null;
            }

            public void done()
            {
                setWorking("", false);
            }
        };

        worker.execute();
    }

    public void doSave()
    {
        setWorking("Saving configuration properties...", true);

        SwingWorker worker = new SwingWorker<Void, Void>()
        {
            public Void doInBackground()
            {
                try
                {
                    save();
                }
                catch (ClientException e)
                {
                    alertException(e.getStackTrace(), e.getMessage());
                }
                return null;
            }

            public void done()
            {
                setWorking("", false);
            }
        };

        worker.execute();
    }

    public void refresh() throws ClientException
    {
        ((IheConfigurationPanel) getComponent()).setProperties(getPropertiesFromServer(), (ArrayList<String[]>) invoke("getActors", null));
    }

    public void save() throws ClientException
    {
        setPropertiesToServer(((IheConfigurationPanel) getComponent()).getProperties());
    }

    public void start()
    {

    }

    public void stop()
    {

    }

    public void display()
    {
        try
        {
            refresh();
        }
        catch (ClientException e)
        {

        }
    }
}
