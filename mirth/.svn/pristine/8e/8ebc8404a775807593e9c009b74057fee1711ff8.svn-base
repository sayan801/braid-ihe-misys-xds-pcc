/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Mirth.
 *
 * The Initial Developer of the Original Code is
 * WebReach, Inc.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Gerald Bortis <geraldb@webreachinc.com>
 *
 * ***** END LICENSE BLOCK ***** */

package com.webreach.mirth.connectors.ihe;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.syntax.jedit.SyntaxDocument;
import org.syntax.jedit.tokenmarker.JavaScriptTokenMarker;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.client.ui.UIConstants;
import com.webreach.mirth.connectors.ConnectorClass;
import com.webreach.mirth.model.DriverInfo;

/**
 * A form that extends from ConnectorClass. All methods implemented are
 * described in ConnectorClass.
 */
public class IheSender extends ConnectorClass
{
    /**
     * Creates new form IheSender
     */
    
    private static SyntaxDocument jsMappingDoc;
    
    private List<DriverInfo> drivers;
    private Map<String, String> transactions;
    
    private static final String PIX_SUBMIT = "Patient Identity Feed (ITI-8)";
    private static final String PDQ_QUERY = "Patient Demographic Query (ITI-21)";
    private static final String XDS_SUBMIT= "Provide and Register Document Set (ITI-15)";
    private static final String XDS_QUERY = "Query XDS Registry (ITI-16)";
    
    public IheSender()
    {
        name = IheSenderProperties.name;
        
        initComponents();
        
        jsMappingDoc = new SyntaxDocument();
        jsMappingDoc.setTokenMarker(new JavaScriptTokenMarker());
        
        javaScriptTextPane.setDocument(jsMappingDoc);
        
        LinkedList<String> transactionNameArray = new LinkedList<String>();
        transactionNameArray.add(PIX_SUBMIT);
        transactionNameArray.add(PDQ_QUERY);
        transactionNameArray.add(XDS_SUBMIT);
        transactionNameArray.add(XDS_QUERY);
        
        transaction.setModel(new javax.swing.DefaultComboBoxModel(transactionNameArray.toArray()));
    }
    
    public Properties getProperties()
    {
        Properties properties = new Properties();
        properties.put(IheSenderProperties.DATATYPE, name);
        properties.put(IheSenderProperties.JAVASCRIPT_HOST, "sink");
        properties.put(IheSenderProperties.JAVASCRIPT_SCRIPT, javaScriptTextPane.getText());
        
        return properties;
    }
    
    public void setProperties(Properties props)
    {
        resetInvalidProperties();
        
        try
        {
            List<String> actorList = (List<String>) parent.mirthClient.invokePluginMethod("IHE Configuration", "getActorNames", null);
            if(actorList == null || actorList.size() == 0)
            {
                actor.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"No actors found"}));
                generateConnection.setEnabled(false);
            }
            else
            {
                actor.setModel(new javax.swing.DefaultComboBoxModel(actorList.toArray()));
                generateConnection.setEnabled(true);
            }
        }
        catch (ClientException ex)
        {
            ex.printStackTrace();
            actor.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"No actors found"}));
        }
        
        javaScriptTextPane.setText((String) props.get(IheSenderProperties.JAVASCRIPT_SCRIPT));
    }
    
    public Properties getDefaults()
    {
        return new IheSenderProperties().getDefaults();
    }
    
    public boolean checkProperties(Properties props, boolean highlight)
    {
        resetInvalidProperties();
        boolean valid = true;
        
        if (((String) props.get(IheSenderProperties.JAVASCRIPT_SCRIPT)).length() == 0)
        {
            valid = false;
            if (highlight)
                javaScriptTextPane.setBackground(UIConstants.INVALID_COLOR);
        }
        
        return valid;
    }
    
    public String[] getDragAndDropCharacters(Properties props)
    {
        return new String[]{"$('", "')"};
    }
    
    private void resetInvalidProperties()
    {
        javaScriptTextPane.setBackground(null);
    }
    
    public String doValidate(Properties props, boolean highlight)
    {
        String error = null;
        
        if (!checkProperties(props, highlight))
            error = "Error in the form for connector \"" + getName() + "\".\n\n";
        
        String script = ((String) props.get(IheSenderProperties.JAVASCRIPT_SCRIPT));
        
        if (script.length() != 0)
        {
            Context context = Context.enter();
            try
            {
                context.compileString("function rhinoWrapper() {" + script + "\n}", UUID.randomUUID().toString(), 1, null);
            }
            catch (EvaluatorException e)
            {
                if (error == null)
                    error = "";
                error += "Error in connector \"" + getName() + "\" at Javascript:\nError on line " + e.lineNumber() + ": " + e.getMessage() + ".\n\n";
            }
            catch (Exception e)
            {
                if (error == null)
                    error = "";
                error += "Error in connector \"" + getName() + "\" at Javascript:\nUnknown error occurred during validation.";
            }
            
            Context.exit();
        }
        
        return error;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        jLabel1 = new javax.swing.JLabel();
        sqlLabel = new javax.swing.JLabel();
        actor = new com.webreach.mirth.client.ui.components.MirthComboBox();
        javaScriptTextPane = new com.webreach.mirth.client.ui.components.MirthSyntaxTextArea(true,false);
        generateConnection = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        transaction = new com.webreach.mirth.client.ui.components.MirthComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel1.setText("IHE Actor");

        sqlLabel.setText("JavaScript:");

        javaScriptTextPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        generateConnection.setText("Generate Transaction Using Actor");
        generateConnection.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                generateConnectionActionPerformed(evt);
            }
        });

        jLabel2.setText("IHE Transaction");

        transaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sqlLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel2)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(javaScriptTextPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, transaction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, actor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 61, Short.MAX_VALUE)
                        .add(generateConnection)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(actor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(transaction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(generateConnection))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(javaScriptTextPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .add(sqlLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private String generateScript()
    {
        
        StringBuilder scriptString = new StringBuilder();
        
        if(((String)transaction.getSelectedItem()).equals(PIX_SUBMIT))
        {
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
            scriptString.append("\n");
            scriptString.append("var actor = \"" + (String) actor.getSelectedItem() + "\";\n");
            scriptString.append("var actors = new ArrayList();\n");
            scriptString.append("actors.add(actor);\n");
            scriptString.append("configuration.resetConfiguration(actors);\n");
            scriptString.append("\n" );
            scriptString.append("var ssmd = new SubmissionSetMetaData();\n");
            scriptString.append("PatientBroker.getInstance().createPatient(\"patient\", IPatientConsumer.CreationReason.OUTPATIENT_REGISTER);\n");
        }
        if(((String)transaction.getSelectedItem()).equals(PDQ_QUERY))
        {
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
            scriptString.append("\n");
            scriptString.append("var actor = \"" + (String) actor.getSelectedItem() + "\";\n");
            scriptString.append("var actors = new ArrayList();\n");
            scriptString.append("actors.add(actor);\n");
            scriptString.append("configuration.resetConfiguration(actors);\n");
            scriptString.append("\n" );
            scriptString.append("var patientQuery = new PatientQuery();\n" );
            scriptString.append("patientQuery.setNameFirst(\"John\");\n" );
            scriptString.append("patientQuery.setNameLast(\"Doe\");\n" );
            scriptString.append("patientQuery.setGender(\"Male\");\n" );
            scriptString.append("\n" );
            scriptString.append("PatientBroker.getInstance().findPatients(patientQuery);\n");
        }
        if(((String)transaction.getSelectedItem()).equals(XDS_SUBMIT))
        {
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.doc.ccd);\n");
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.ihe.configuration);\n");
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.doc.ccd);\n");
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.ihe.configuration);\n");
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.ihe);\n");
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
            scriptString.append("\n");
            scriptString.append("importPackage(Packages.java.lang);\n");
            scriptString.append("importPackage(Packages.java.util);\n");
            scriptString.append("\n");
            scriptString.append("var actor = \"" + (String) actor.getSelectedItem() + "\";\n");
            scriptString.append("var actors = new ArrayList();\n");
            scriptString.append("actors.add(actor);\n");
            scriptString.append("configuration.resetConfiguration(actors);\n");
            scriptString.append("\n" );
            scriptString.append("var ssmd = new SubmissionSetMetaData();\n");
            scriptString.append("ssmd.setTitle(\"First Mirth Channel Document Submit\");\n");
            scriptString.append("ssmd.setComments(\"Test submit one document from a Mirth Channel\");\n");
            scriptString.append("ssmd.setContentCode(Packages.com.misyshealthcare.connect.base.SharedEnums.XdsContentCode.Transfer_of_care_Referral);\n");
            scriptString.append("\n");
            scriptString.append("var doc = IheService.buildReferralSummary('referral_summary');\n");
            scriptString.append("\n");
            scriptString.append("var ss = new SubmissionSet();\n");
            scriptString.append("ss.setDocuments([doc]);\n");
            scriptString.append("ss.setSubmissionSetMetaData( ssmd );\n");
            scriptString.append("IheService.submitDocuments(ss);\n");
        }
        if(((String)transaction.getSelectedItem()).equals(XDS_QUERY))
        {
            scriptString.append("importPackage(Packages.com.misyshealthcare.connect.base);\n");
            scriptString.append("importPackage(Packages.java.lang);\n");
            scriptString.append("importPackage(Packages.java.util);\n");
            scriptString.append("importPackage(Packages.mesatests);\n");
            scriptString.append("\n");
            scriptString.append("var log = new MesaTestLogger(System.out);\n");
            scriptString.append("\n");
            scriptString.append("var actor = \"" + (String) actor.getSelectedItem() + "\";\n");
            scriptString.append("var actors = new ArrayList();\n");
            scriptString.append("actors.add(actor);\n");
            scriptString.append("configuration.resetConfiguration(actors);\n");
            scriptString.append("\n" );
            scriptString.append("var query = new DocumentQuery();\n");
            scriptString.append("query.setEndTime( new DateQuery(GregorianCalendar.getInstance()));");
            scriptString.append("query.setPatientId(new PatientID(\"Patient ID\"));\n");
            scriptString.append("\n");
            scriptString.append("docs = DocumentBroker.getInstance().findDocuments(query);\n");
            scriptString.append("\n");
            scriptString.append("if (docs.size() == 0) {\n");
            scriptString.append("   logger.error(\"No document is found.\");\n");
            scriptString.append("} else {\n");
            scriptString.append("logger.error(docs.size() + \" documents are available:\");\n");
            scriptString.append("}\n");
            scriptString.append("\n");
            scriptString.append("for (i=0; i < docs.size(); i=i+1) {\n");
            scriptString.append("    var doc = docs.get(i);\n");
            scriptString.append("    logger.error(\"Document #\" + i + \": \" + doc.getTitle() + \", ClassCode: \" + doc.getClassCode().getValue() + \", Unique ID: \" + doc.getUniqueId() + \", URI=\" + doc.getUri());\n");
            scriptString.append("}\n");
        }     
        
        return scriptString.toString();
    }
    
    private void generateConnectionActionPerformed(java.awt.event.ActionEvent evt)
    {
        javaScriptTextPane.setText(generateScript() + "\n\n" + javaScriptTextPane.getText());
        
        parent.enableSave();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.webreach.mirth.client.ui.components.MirthComboBox actor;
    private javax.swing.JButton generateConnection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.webreach.mirth.client.ui.components.MirthSyntaxTextArea javaScriptTextPane;
    private javax.swing.JLabel sqlLabel;
    private com.webreach.mirth.client.ui.components.MirthComboBox transaction;
    // End of variables declaration//GEN-END:variables
    
}
