
package view;

import adjustedClasses.StaticShower;
import controller.TeamsParticipationsInternalFrameController;
import enums.InternalFrameName;
import exceptions.InvalidAddBecauseOfLimit;
import static java.lang.Integer.max;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Championship;

public class TeamsParticipationsInternalFrame extends AbstractParticipationsInternalFrame {

    public TeamsParticipationsInternalFrame() {
        initComponents();
        
        createModels();
    }
    
    private DefaultListModel<String> availableTeamsDefaultListModelObject = null;
    private DefaultListModel<String> inTeamsDefaultListModelObject = null;
    private DefaultComboBoxModel<String> teamsComboBoxModelObject;
    private DefaultListModel<String> availableRosterDefaultListModelObject = null;
    private DefaultListModel<String> inRosterDefaultListModelObject = null;
    
    public void createModels(){
        
        this.availableTeamsDefaultListModelObject = new DefaultListModel<>();
        this.availableTeamsList.setModel(availableTeamsDefaultListModelObject);
        
        this.inTeamsDefaultListModelObject = new DefaultListModel<>();
        this.inTeamsList.setModel(inTeamsDefaultListModelObject);
        
        this.teamsComboBoxModelObject = new DefaultComboBoxModel<>();
        this.teamsComboBox.setModel(teamsComboBoxModelObject);
        
        this.availableRosterDefaultListModelObject = new DefaultListModel<>();
        this.availableRosterList.setModel(availableRosterDefaultListModelObject);
        
        this.inRosterDefaultListModelObject = new DefaultListModel<>();
        this.inRosterList.setModel(inRosterDefaultListModelObject);
    }
    
    public void updateInternalFrame(){
        
        //System.out.println("Sergiooooooooooooooooooooo");
        
        
        
        super.updateInternalFrame(availableTeamsDefaultListModelObject, inTeamsDefaultListModelObject);
        updateTeamsComboBox();
        this.updateAvailableRosterList();
        this.updateInRosterList();
    }
    
    public void updateVisibilityOfInOutButtons(){
    
        if (this.controllerObject.isChampionshipObjectFinalized()){
            this.inTeamsButton.setEnabled(false);
            this.outTeamsButton.setEnabled(false);
        }
        else{
            this.inTeamsButton.setEnabled(true);
            this.outTeamsButton.setEnabled(true);
        }
        
              
    }
    
    
    public void updateInternalFrameForInOrForOutAvailableButtonUsed(){
        
        this.teamsInTextField.setText(this.controllerObject.getChampionshipObject().
                                                                    getNumberOfCompetitors().toString());
        
        super.updateInternalFrameForInOrForOutAvailableButtonUsed(availableTeamsDefaultListModelObject,
                                                                            inTeamsDefaultListModelObject);
        updateTeamsComboBox();
        
        if (this.teamsComboBox.getItemCount() == 0){
            super.ClearJList(this.availableRosterDefaultListModelObject);
            super.ClearJList(this.inRosterDefaultListModelObject);
            this.NoOfPlayersInTextField.setText("0");
        }
    }
    
    public void updateInfoPanel(){
        
        Championship championshipObject = this.controllerObject.getChampionshipObject();
        
        this.championshipLabel.setText(championshipObject.toString());
        
        String minRosterTostring = championshipObject.getMinRoster().toString();
        this.basicInfoLabel.setText(minRosterTostring + " vs " + minRosterTostring +
                                    " " + championshipObject.getSport().toString() + " " + 
                                    championshipObject.getChampionshipTypeString() );
        
        this.limitPlayersLabel.setText("(Limit: " + championshipObject.getMaxRoster().toString() + ")");
        this.teamsInTextField.setText(championshipObject.getNumberOfCompetitors().toString());
        this.limitTeamsLabel.setText("(Limit: " + championshipObject.getNumberOfCompetitorsBigO().toString() + ")");
        
        updateNoOfPlayersInTextField();
        
        this.writeParticipationsGuideString();
    }
    
    public void updateNoOfPlayersInTextField(){
        
        Championship championshipObject = this.controllerObject.getChampionshipObject();
        
        try{
            this.NoOfPlayersInTextField.setText(championshipObject.getNumberOfInRoster
                                            (this.teamsComboBox.getSelectedIndex()).toString());
        }
        catch (NullPointerException nullPointerExceptionObject){}
        
    }
    
    public void updateAvailableCompetitorsList(){
        
        super.updateAvailableCompetitorsList(availableTeamsDefaultListModelObject);
    }
    
    public void updateInCompetitorsList(){
        
        super.updateInCompetitorsList(inTeamsDefaultListModelObject);
    }
    
    public void updateTeamsComboBox(){
       
        ((TeamsParticipationsInternalFrameController)this.controllerObject).
                                                    updateTeamsComboBoxModel(teamsComboBoxModelObject);
    
        if (teamsComboBoxModelObject.getSize() == 0)
            this.teamsComboBox.setEnabled(false);
        else
            this.teamsComboBox.setEnabled(true);
    }
    
    public void updateAvailableRosterList(){
        super.ClearJList(this.availableRosterDefaultListModelObject);
        
        ((TeamsParticipationsInternalFrameController)this.controllerObject).
                                    updateAvailableRosterListModel(availableRosterDefaultListModelObject,
                                                           max(0, this.teamsComboBox.getSelectedIndex()));
    }
    
    public void updateInRosterList(){
        super.ClearJList(this.inRosterDefaultListModelObject);
        
        ((TeamsParticipationsInternalFrameController)this.controllerObject).
                                    updateInRosterListModel(inRosterDefaultListModelObject,
                                                            max(0, this.teamsComboBox.getSelectedIndex()));
    }
    
    public void addPlayerToInRoster(){
        
        ((TeamsParticipationsInternalFrameController)this.controllerObject).addPlayerToInRoster
        (this.teamsComboBox.getSelectedIndex(),this.availableRosterList.getSelectedValue());
        
        this.updateNoOfPlayersInTextField();
    }
    
    public void removePlayerromInRoster(){
        
        ((TeamsParticipationsInternalFrameController)this.controllerObject).removePlayerFromInRoster
        (this.teamsComboBox.getSelectedIndex(),this.inRosterList.getSelectedIndex());
        
        this.updateNoOfPlayersInTextField();
    }
    
    public void writeParticipationsGuideString(){
        this.guideTextArea.setText(this.controllerObject.getParticipationsGuideString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        availableTeamsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        availableTeamsList = new javax.swing.JList<>();
        teamsButtonPanel = new javax.swing.JPanel();
        inTeamsButton = new javax.swing.JButton();
        outTeamsButton = new javax.swing.JButton();
        InTeamsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inTeamsList = new javax.swing.JList<>();
        infoPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        teamsInTextField = new javax.swing.JTextField();
        limitTeamsLabel = new javax.swing.JLabel();
        championshipLabel = new javax.swing.JLabel();
        basicInfoLabel = new javax.swing.JLabel();
        RosterApplicationPanel = new javax.swing.JPanel();
        teamLabel = new javax.swing.JLabel();
        teamsComboBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        availableRosterList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        inRosterList = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        InRosterButton = new javax.swing.JButton();
        outRosterButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        limitPlayersLabel = new javax.swing.JLabel();
        NoOfPlayersInLabel = new javax.swing.JLabel();
        NoOfPlayersInTextField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        guideTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(201, 195, 168));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("Participation - Teams");

        jPanel2.setBackground(new java.awt.Color(201, 195, 168));

        jPanel1.setBackground(new java.awt.Color(201, 195, 168));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Teams application", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        availableTeamsPanel.setBackground(new java.awt.Color(201, 195, 168));
        availableTeamsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Available Teams"));

        availableTeamsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(availableTeamsList);

        javax.swing.GroupLayout availableTeamsPanelLayout = new javax.swing.GroupLayout(availableTeamsPanel);
        availableTeamsPanel.setLayout(availableTeamsPanelLayout);
        availableTeamsPanelLayout.setHorizontalGroup(
            availableTeamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableTeamsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        availableTeamsPanelLayout.setVerticalGroup(
            availableTeamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableTeamsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        teamsButtonPanel.setBackground(new java.awt.Color(201, 195, 168));

        inTeamsButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        inTeamsButton.setForeground(java.awt.Color.blue);
        inTeamsButton.setText("In");
        inTeamsButton.setMaximumSize(new java.awt.Dimension(53, 23));
        inTeamsButton.setMinimumSize(new java.awt.Dimension(53, 23));
        inTeamsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inTeamsButtonActionPerformed(evt);
            }
        });

        outTeamsButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        outTeamsButton.setForeground(java.awt.Color.blue);
        outTeamsButton.setText("Out");
        outTeamsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outTeamsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout teamsButtonPanelLayout = new javax.swing.GroupLayout(teamsButtonPanel);
        teamsButtonPanel.setLayout(teamsButtonPanelLayout);
        teamsButtonPanelLayout.setHorizontalGroup(
            teamsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teamsButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(teamsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inTeamsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outTeamsButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        teamsButtonPanelLayout.setVerticalGroup(
            teamsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teamsButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inTeamsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(outTeamsButton)
                .addContainerGap())
        );

        InTeamsPanel.setBackground(new java.awt.Color(201, 195, 168));
        InTeamsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Teams in"));

        inTeamsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(inTeamsList);

        javax.swing.GroupLayout InTeamsPanelLayout = new javax.swing.GroupLayout(InTeamsPanel);
        InTeamsPanel.setLayout(InTeamsPanelLayout);
        InTeamsPanelLayout.setHorizontalGroup(
            InTeamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InTeamsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );
        InTeamsPanelLayout.setVerticalGroup(
            InTeamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InTeamsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(availableTeamsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(teamsButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InTeamsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(InTeamsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(availableTeamsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(teamsButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        infoPanel.setBackground(new java.awt.Color(201, 195, 168));
        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Championship", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        jLabel6.setText("Teams in");

        teamsInTextField.setEditable(false);

        limitTeamsLabel.setText("Limit");

        championshipLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(championshipLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basicInfoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamsInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limitTeamsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addComponent(championshipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(basicInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(teamsInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limitTeamsLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RosterApplicationPanel.setBackground(new java.awt.Color(201, 195, 168));
        RosterApplicationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Roster Application", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        teamLabel.setText("Team");

        teamsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamsComboBoxActionPerformed(evt);
            }
        });

        availableRosterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(availableRosterList);

        inRosterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(inRosterList);

        jPanel6.setBackground(new java.awt.Color(201, 195, 168));

        InRosterButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        InRosterButton.setForeground(java.awt.Color.blue);
        InRosterButton.setText("In");
        InRosterButton.setMaximumSize(new java.awt.Dimension(53, 23));
        InRosterButton.setMinimumSize(new java.awt.Dimension(53, 23));
        InRosterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InRosterButtonActionPerformed(evt);
            }
        });

        outRosterButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        outRosterButton.setForeground(java.awt.Color.blue);
        outRosterButton.setText("Out");
        outRosterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outRosterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(outRosterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InRosterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InRosterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outRosterButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Available roster");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Applied Roster");

        NoOfPlayersInLabel.setText("No of players in");

        NoOfPlayersInTextField.setEditable(false);

        javax.swing.GroupLayout RosterApplicationPanelLayout = new javax.swing.GroupLayout(RosterApplicationPanel);
        RosterApplicationPanel.setLayout(RosterApplicationPanelLayout);
        RosterApplicationPanelLayout.setHorizontalGroup(
            RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                        .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teamLabel)
                            .addComponent(teamsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE))
                    .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                        .addComponent(NoOfPlayersInLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NoOfPlayersInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limitPlayersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56)))
                .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(31, 31, 31)
                .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );
        RosterApplicationPanelLayout.setVerticalGroup(
            RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                        .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11))
                        .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4)))
                            .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(RosterApplicationPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(teamLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(RosterApplicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(limitPlayersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NoOfPlayersInLabel)
                            .addComponent(NoOfPlayersInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(201, 195, 168));

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveButton.setForeground(java.awt.Color.blue);
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setForeground(java.awt.Color.blue);
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton)
                .addGap(79, 79, 79))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(backButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(201, 195, 168));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Short advice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        guideTextArea.setBackground(new java.awt.Color(201, 195, 168));
        guideTextArea.setColumns(10);
        guideTextArea.setRows(1);
        guideTextArea.setBorder(null);
        jScrollPane5.setViewportView(guideTextArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesUI/uncleSamSmallb&w.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(RosterApplicationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RosterApplicationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.controllerObject.showInternalFrameObject(InternalFrameName.CHAMPIONSHIPINTERNAL);
    }//GEN-LAST:event_backButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        
        this.updateInternalFrame();
        this.updateVisibilityOfInOutButtons();
    }//GEN-LAST:event_formComponentShown

    private void inTeamsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inTeamsButtonActionPerformed
        
        try{
            if (this.availableTeamsList.getSelectedValue() == null)
                throw new NullPointerException();
            
            super.checkForInvalidAddBecauseOfLimit("teams");
            
            this.controllerObject.addCompetitorToChampionship(this.controllerObject.getSelectedCompetitor
                                                (this.availableTeamsList.getSelectedValue().toString()));
            updateInternalFrameForInOrForOutAvailableButtonUsed();
        
            this.writeParticipationsGuideString();
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Available teams\" list");
        }
        catch(InvalidAddBecauseOfLimit e){
            StaticShower.showWarningMessageForInvalidInput(e);
        }
        
    }//GEN-LAST:event_inTeamsButtonActionPerformed

    private void outTeamsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outTeamsButtonActionPerformed

        try{
            
            if (this.inTeamsList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            this.controllerObject.removeCompetitorFromChampionship(this.inTeamsList.getSelectedIndex());
        
            updateInternalFrameForInOrForOutAvailableButtonUsed();
        
            this.writeParticipationsGuideString();
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                           " from \"Teams in\" list");
        }
    }//GEN-LAST:event_outTeamsButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        this.controllerObject.serialize();
    }//GEN-LAST:event_saveButtonActionPerformed
    //int counter =0;
    private void teamsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamsComboBoxActionPerformed

        if (this.teamsComboBoxModelObject.getSize() != 0){
            this.updateAvailableRosterList();
            this.updateInRosterList();
            this.updateNoOfPlayersInTextField();
        }
    }//GEN-LAST:event_teamsComboBoxActionPerformed

    private void InRosterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InRosterButtonActionPerformed
        // TODO add your handling code here:
        try{
            if (this.availableRosterList.getSelectedIndex() == -1)
                throw new NullPointerException();
            
            Championship championshipObject = this.controllerObject.getChampionshipObject();
        
            if (this.teamsComboBox.getSelectedIndex() != -1){
                int numberOfInPlayers = championshipObject.getNumberOfInRoster
                                            (this.teamsComboBox.getSelectedIndex());
                if (numberOfInPlayers == championshipObject.getMaxRoster())
                    throw new InvalidAddBecauseOfLimit("players");
            }
                
            addPlayerToInRoster();
            this.updateAvailableRosterList();
            this.updateInRosterList();
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Available roster\" list");
        }
        catch(InvalidAddBecauseOfLimit e){
            StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_InRosterButtonActionPerformed

    private void outRosterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outRosterButtonActionPerformed
        // TODO add your handling code here:
        try{
            if (this.inRosterList.getSelectedIndex() == -1)
                throw new NullPointerException();
            
            removePlayerromInRoster();
            this.updateAvailableRosterList();
            this.updateInRosterList();
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Applied roster\" list");
        }
    }//GEN-LAST:event_outRosterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InRosterButton;
    private javax.swing.JPanel InTeamsPanel;
    private javax.swing.JLabel NoOfPlayersInLabel;
    private javax.swing.JTextField NoOfPlayersInTextField;
    private javax.swing.JPanel RosterApplicationPanel;
    private javax.swing.JList<String> availableRosterList;
    private javax.swing.JList<String> availableTeamsList;
    private javax.swing.JPanel availableTeamsPanel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel basicInfoLabel;
    private javax.swing.JLabel championshipLabel;
    private javax.swing.JTextArea guideTextArea;
    private javax.swing.JList<String> inRosterList;
    private javax.swing.JButton inTeamsButton;
    private javax.swing.JList<String> inTeamsList;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel limitPlayersLabel;
    private javax.swing.JLabel limitTeamsLabel;
    private javax.swing.JButton outRosterButton;
    private javax.swing.JButton outTeamsButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel teamLabel;
    private javax.swing.JPanel teamsButtonPanel;
    private javax.swing.JComboBox<String> teamsComboBox;
    private javax.swing.JTextField teamsInTextField;
    // End of variables declaration//GEN-END:variables
}
