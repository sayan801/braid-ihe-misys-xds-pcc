package com.webreach.mirth.plugins.ihepixwizard;

import java.awt.Component;
import java.util.List;

import org.netbeans.spi.wizard.WizardPage;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.client.ui.PlatformUI;

public class PatientDescriptorPage extends WizardPage
{
    
    /** Creates new form DestinationConnectorPage */
    public PatientDescriptorPage()
    {
        initComponents();
        assigningAuthorityTextField.setText("msg['PID']['PID.3']['PID.3.4'].toString()");
        assigningAuthorityIdTextField.setText("msg['PID']['PID.3']['PID.3.4'].toString()");
        visitSystemIdTextField.setText("msg['PV1']['PV1.19']['PV1.19.4'].toString()");
        visitIdTextField.setText("msg['PV1']['PV1.19']['PV1.19.1'].toString()");
        
        assigningAuthorityTextField.setName("assigningAuthority");
        assigningAuthorityIdTextField.setName("assigningAuthorityId");
        visitSystemIdTextField.setName("visitSystemId");
        visitIdTextField.setName("visitId");
    }
    
    public static final String getDescription() {
        return "Build patient descriptor";
    }
    
    protected String validateContents (Component component, Object o) {
        
        if (assigningAuthorityTextField.getText().length() == 0 || assigningAuthorityIdTextField.getText().length() == 0 || visitSystemIdTextField.getText().length() == 0 || visitIdTextField.getText().length() == 0) {
            return "You must fill in all fields.";
        } else {
            return null;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        assigningAuthorityTextField = new javax.swing.JTextField();
        assigningAuthorityIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        visitSystemIdTextField = new javax.swing.JTextField();
        visitIdTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("This channel will generate a Patient Descriptor object.");

        jLabel2.setText("You may specific mappings to message fields or constant values in \"quotes\".");

        jLabel3.setText("PID Assigning Authority:");

        jLabel4.setText("Assiging Authority ID:");

        jLabel5.setText("Visit System ID:");

        jLabel6.setText("Visit ID:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel6)
                            .add(jLabel5)
                            .add(jLabel4)
                            .add(jLabel3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(assigningAuthorityTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .add(assigningAuthorityIdTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, visitSystemIdTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, visitIdTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(assigningAuthorityTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(assigningAuthorityIdTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(visitSystemIdTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(visitIdTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assigningAuthorityIdTextField;
    private javax.swing.JTextField assigningAuthorityTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField visitIdTextField;
    private javax.swing.JTextField visitSystemIdTextField;
    // End of variables declaration//GEN-END:variables
    
}
