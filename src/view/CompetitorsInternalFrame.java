
package view;

import adjustedClasses.TreeSetWithGetIndex;
import adjustedClasses.StaticNormalizer;
import adjustedClasses.StaticShower;
import model.AmateurSports;
import model.Championship;
import model.Player;
import model.Solist;
import enums.Sport;
import model.Team;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import serialization.Serializer;
import adjustedClasses.UsefulStaticStuff;
import controller.CompetitorsInternalFrameController;
import enums.InternalFrameName;
import exceptions.InvalidTextException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;


public class CompetitorsInternalFrame extends AbstractJInternalFrame {
    
    CompetitorsInternalFrameController controllerObject;
    // Constructor
    public CompetitorsInternalFrame() {
       
        initComponents();
        
        createModels();
    }
    
    public void setCompetitorsInternalFrameControllerObject(CompetitorsInternalFrameController controllerObject){
        this.controllerObject = controllerObject;
    }
    
    private DefaultComboBoxModel<String> collegiatesportComboBoxModelObject;
    private DefaultComboBoxModel<String> nonCollegiatesportComboBoxModelObject;
            
    private DefaultListModel<String> teamsDefaultListModelObject = null;
    private DefaultListModel<String> solistsDefaultListModelObject = null;
    private DefaultListModel<String> rosterDefaultListModelObject = null;
    
    public void createModels(){
        this.collegiatesportComboBoxModelObject = new DefaultComboBoxModel<>();
        this.collegiateComboBox.setModel(collegiatesportComboBoxModelObject);
        
        this.nonCollegiatesportComboBoxModelObject = new DefaultComboBoxModel<>();
        this.nonCollegiateSportComboBox.setModel(nonCollegiatesportComboBoxModelObject);
        
        this.teamsDefaultListModelObject = new DefaultListModel<>();
        this.teamsList.setModel(teamsDefaultListModelObject);
        
        this.solistsDefaultListModelObject = new DefaultListModel<>();
        this.solistsList.setModel(solistsDefaultListModelObject);
            
        this.rosterDefaultListModelObject = new DefaultListModel<>();
        this.rosterList.setModel(rosterDefaultListModelObject);
    }
    
    // method for updating collegiateSportComboBox
    // runs once
    public void updateCollegiateSportComboBox(){
        // Creation object τύπου DefaultComboBoxModel και σύνδεση με το sportComboBoxModelObject
        
        // Πέρνα ολα τα ατομικά και ομαδικά αθλήματα
        this.controllerObject.updateSportComboBoxModel(collegiatesportComboBoxModelObject,true,false);
    }
    
    // method for updating nonCollegiateSportComboBox
    // runs once
    public void updateNonCollegiateSportComboBox(){
        // Creation object τύπου DefaultComboBoxModel και σύνδεση με το sportComboBoxModelObject
        
        // Πέρνα ολα τα ατομικά και ομαδικά αθλήματα
        this.controllerObject.updateSportComboBoxModel(nonCollegiatesportComboBoxModelObject,false,true);
        
    }
    
    // UpdateLists methods
    
    //updateTeamsList method
    
    
    public void updateTeamsList(){
        
        super.ClearJList(teamsDefaultListModelObject);
        
        this.controllerObject.updateTeamsListModel(teamsDefaultListModelObject,
                                                (String)this.collegiateComboBox.getSelectedItem());
        
        if (!teamsDefaultListModelObject.isEmpty())        
            this.teamsList.setSelectedIndex(0);
    }
    
    //updateSolistsList method
    
    
    public void updateSolistsList(){
        super.ClearJList(solistsDefaultListModelObject);
        
        this.controllerObject.updateSolistsListModel(solistsDefaultListModelObject,
                                                (String)this.nonCollegiateSportComboBox.getSelectedItem());
        
        if (!solistsDefaultListModelObject.isEmpty())
            this.solistsList.setSelectedIndex(0);
    }
    
    //updateRosterList method
    
    public void updateRosterList(){
        super.ClearJList(rosterDefaultListModelObject);
                   
        if (!teamsDefaultListModelObject.isEmpty()) 
            this.controllerObject.updateRosterListModel(rosterDefaultListModelObject,
                                this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                                        (String)this.collegiateComboBox.getSelectedItem()));      
    }
    public void setShowNoOfPlayersLabelText(Integer NoOfPlayersInteger){
        this.showNoOfPlayersLabel.setText("No of Players: " + NoOfPlayersInteger.toString());
    }
    public void clearCreateEditTeamForm(){
        List<JComponent> componentsList = Arrays.asList(this.teamNameTextField, 
                                                        this.sportShowLabel);
        
        super.clearForm(componentsList);
    }
    public void clearCreateEditPlayerForm(){
        List<JComponent> componentsList = Arrays.asList(this.playerLastNameTextField,
                                                        this.playerFirstNameTextField,
                                                        this.playerShowPlayerSportLabel,
                                                        this.playerShowIdLabel);
        super.clearForm(componentsList);
    }
    public void clearCreateEditSolistForm(){
        List<JComponent> componentsList = Arrays.asList(this.solistLastNameTextField,
                                                        this.solistFirstNameTextField,
                                                        this.solistShowSportLabel,
                                                        this.solistShowIDLabel);
        super.clearForm(componentsList);
    }
   
    public void clearTeamNameTextField(){
        this.teamNameTextField.setText("");
    }
    public void clearPlayerFirstNameTextField(){
        this.playerFirstNameTextField.setText("");
    }
    public void clearPlayerLastNameTextField(){
        this.playerLastNameTextField.setText("");
    }
    public void clearSolistFirstNameTextField(){
        this.solistFirstNameTextField.setText("");
    }
    public void clearSolistLastNameTextField(){
        this.solistLastNameTextField.setText("");
    }
    public void checkCreateTeam() throws InvalidTextException{
        this.controllerObject.checkCreateTeam(this.teamNameTextField.getText());
    }
    public void createTeam(){
        this.controllerObject.createTeam(this.teamNameTextField.getText(),
                                                            (String)this.collegiateComboBox.getSelectedItem());   
        
    }
    public void createTeam(TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex){
        this.controllerObject.createTeam(this.teamNameTextField.getText(),
                                            (String)this.collegiateComboBox.getSelectedItem(),rosterTreeSetWithGetIndex);  
    }
    public void removeTeam(Team targetTeam){
        this.controllerObject.removeTeam(targetTeam);
    }
    public void checkCreatePlayer() throws InvalidTextException{
        this.controllerObject.checkCreatePlayer(this.playerLastNameTextField.getText(),
                                                this.playerFirstNameTextField.getText());
    }
    public void createPlayer(){
        Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                (String)this.collegiateComboBox.getSelectedItem());
        
        this.controllerObject.createPlayer(selectedTeam, this.playerLastNameTextField.getText(), 
                                                                        this.playerFirstNameTextField.getText());
    }
    public void createPlayer(int selectedPlayerIdInt){
        Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                (String)this.collegiateComboBox.getSelectedItem());
        
        this.controllerObject.createPlayer(selectedTeam, this.playerLastNameTextField.getText(), 
                                                                        this.playerFirstNameTextField.getText(),
                                                                        selectedPlayerIdInt);
    }
    public void removePlayer(){
        Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                (String)this.collegiateComboBox.getSelectedItem());
        
        this.controllerObject.removePlayer(selectedTeam, this.rosterList.getSelectedIndex());
        
    }
    public void checkCreateSolist() throws InvalidTextException{
        this.controllerObject.checkCreateSolist(this.solistLastNameTextField.getText(),
                                                this.solistFirstNameTextField.getText());
    }
    public void createSolist(){
        this.controllerObject.createSolist(this.solistLastNameTextField.getText(),this.solistFirstNameTextField.getText(),
                        (String)this.nonCollegiateSportComboBox.getSelectedItem());
        
    }
    public void createSolist(int selectedSolistId){
        this.controllerObject.createSolist(selectedSolistId,this.solistLastNameTextField.getText(),
                                                                    this.solistFirstNameTextField.getText(),
                                                    (String)this.nonCollegiateSportComboBox.getSelectedItem());
    }
    public void removeSolist(){
        this.controllerObject.removeSolist(this.solistsList.getSelectedValue().toString());
    }
    
    public void actionsForCollegiateComboBoxActionPerformed() {                                                   
        
        this.updateTeamsList();
        this.updateRosterList();
        this.clearCreateEditTeamForm();
        this.clearCreateEditPlayerForm();
    }                                                  

    public void actionsForNonCollegiateSportComboBoxActionPerformed() {                                                           

        this.updateSolistsList();
        this.clearCreateEditSolistForm();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        teamsSolistsTabbedPane = new javax.swing.JTabbedPane();
        teamPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        rosterList = new javax.swing.JList<>();
        rosterLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        teamsList = new javax.swing.JList<>();
        teamsLabel = new javax.swing.JLabel();
        collegiateComboBox = new javax.swing.JComboBox<>();
        collegiateSportLabel = new javax.swing.JLabel();
        showNoOfPlayersLabel = new javax.swing.JLabel();
        createEditTeamPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        teamNameTextField = new javax.swing.JTextField();
        createTeamButton = new javax.swing.JButton();
        teamEditButton = new javax.swing.JButton();
        teamsDeleteButton = new javax.swing.JButton();
        sportShowLabel = new javax.swing.JLabel();
        teamsDoneEditingButton = new javax.swing.JButton();
        teamClearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        createEditPlayerPanel = new javax.swing.JPanel();
        playerLastNameLabel = new javax.swing.JLabel();
        playerLastNameTextField = new javax.swing.JTextField();
        playerFirstNameLabel = new javax.swing.JLabel();
        playerFirstNameTextField = new javax.swing.JTextField();
        playrSportLabel = new javax.swing.JLabel();
        playerIdLabel = new javax.swing.JLabel();
        playerShowPlayerSportLabel = new javax.swing.JLabel();
        playerShowIdLabel = new javax.swing.JLabel();
        PlayerCreateButton = new javax.swing.JButton();
        playerDoneEditingButton = new javax.swing.JButton();
        playerClearButton = new javax.swing.JButton();
        playerEditButton = new javax.swing.JButton();
        playersDeleteButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        solistPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        solistsList = new javax.swing.JList<>();
        solistsLabel = new javax.swing.JLabel();
        nonCollegiateSportComboBox = new javax.swing.JComboBox<>();
        individualSportLabel = new javax.swing.JLabel();
        createEditSolistPanel = new javax.swing.JPanel();
        solistCreateButton = new javax.swing.JButton();
        solistDoneEditingButton = new javax.swing.JButton();
        solistClearButton = new javax.swing.JButton();
        solistEditButton = new javax.swing.JButton();
        solistDeleteButton = new javax.swing.JButton();
        solistLastNameLabel = new javax.swing.JLabel();
        solistLastNameTextField = new javax.swing.JTextField();
        solistFirstNameLabel = new javax.swing.JLabel();
        solistFirstNameTextField = new javax.swing.JTextField();
        solistSportLabel = new javax.swing.JLabel();
        solistIDLabel = new javax.swing.JLabel();
        solistShowSportLabel = new javax.swing.JLabel();
        solistShowIDLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        teamsSaveButton = new javax.swing.JButton();
        backToMainButton = new javax.swing.JButton();

        titleLabel.setBackground(new java.awt.Color(201, 195, 168));
        titleLabel.setFont(new java.awt.Font("Vrinda", 1, 22)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 0, 0));
        titleLabel.setText("Competitors");

        teamsSolistsTabbedPane.setBackground(new java.awt.Color(201, 195, 168));
        teamsSolistsTabbedPane.setForeground(java.awt.Color.blue);
        teamsSolistsTabbedPane.setFont(new java.awt.Font("Vrinda", 1, 14)); // NOI18N

        teamPanel.setBackground(new java.awt.Color(201, 195, 168));

        jPanel1.setBackground(new java.awt.Color(201, 195, 168));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "All teams", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(201, 195, 168));

        rosterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(rosterList);

        rosterLabel.setText("Roster");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rosterLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rosterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4))
        );

        jPanel4.setBackground(new java.awt.Color(201, 195, 168));

        teamsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        teamsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                teamsListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(teamsList);

        teamsLabel.setText("Teams");

        collegiateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collegiateComboBoxActionPerformed(evt);
            }
        });

        collegiateSportLabel.setText("Team Sports");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(collegiateSportLabel)
                        .addGap(0, 340, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(teamsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(collegiateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showNoOfPlayersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(collegiateSportLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showNoOfPlayersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(collegiateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(teamsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        createEditTeamPanel.setBackground(new java.awt.Color(201, 195, 168));
        createEditTeamPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Create / Edit Team", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        nameLabel.setText("Team Name:");

        teamNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                teamNameTextFieldFocusLost(evt);
            }
        });

        createTeamButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        createTeamButton.setForeground(java.awt.Color.blue);
        createTeamButton.setText("Create");
        createTeamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTeamButtonActionPerformed(evt);
            }
        });

        teamEditButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        teamEditButton.setForeground(java.awt.Color.blue);
        teamEditButton.setText("Edit");
        teamEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamEditButtonActionPerformed(evt);
            }
        });

        teamsDeleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        teamsDeleteButton.setForeground(java.awt.Color.blue);
        teamsDeleteButton.setText("Delete");
        teamsDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamsDeleteButtonActionPerformed(evt);
            }
        });

        teamsDoneEditingButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        teamsDoneEditingButton.setForeground(java.awt.Color.blue);
        teamsDoneEditingButton.setText("Done Editing");
        teamsDoneEditingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamsDoneEditingButtonActionPerformed(evt);
            }
        });

        teamClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        teamClearButton.setForeground(java.awt.Color.blue);
        teamClearButton.setText("Clear");
        teamClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamClearButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Sport:");

        javax.swing.GroupLayout createEditTeamPanelLayout = new javax.swing.GroupLayout(createEditTeamPanel);
        createEditTeamPanel.setLayout(createEditTeamPanelLayout);
        createEditTeamPanelLayout.setHorizontalGroup(
            createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                        .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(nameLabel)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createEditTeamPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teamNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sportShowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(createTeamButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamsDoneEditingButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamClearButton))
                    .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(teamEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teamsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createEditTeamPanelLayout.setVerticalGroup(
            createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createEditTeamPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(nameLabel))
                    .addComponent(teamNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sportShowLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createTeamButton)
                    .addComponent(teamsDoneEditingButton)
                    .addComponent(teamClearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditTeamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teamEditButton)
                    .addComponent(teamsDeleteButton))
                .addGap(60, 60, 60))
        );

        createEditPlayerPanel.setBackground(new java.awt.Color(201, 195, 168));
        createEditPlayerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Create / Edit Player", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        playerLastNameLabel.setText("Last Name:");

        playerLastNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                playerLastNameTextFieldFocusLost(evt);
            }
        });

        playerFirstNameLabel.setText("First Name:");

        playerFirstNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                playerFirstNameTextFieldFocusLost(evt);
            }
        });

        playrSportLabel.setText("Sport:");

        playerIdLabel.setText("ID");

        PlayerCreateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        PlayerCreateButton.setForeground(java.awt.Color.blue);
        PlayerCreateButton.setText("Create");
        PlayerCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerCreateButtonActionPerformed(evt);
            }
        });

        playerDoneEditingButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playerDoneEditingButton.setForeground(java.awt.Color.blue);
        playerDoneEditingButton.setText("Done Editing");
        playerDoneEditingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerDoneEditingButtonActionPerformed(evt);
            }
        });

        playerClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playerClearButton.setForeground(java.awt.Color.blue);
        playerClearButton.setText("Clear");
        playerClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerClearButtonActionPerformed(evt);
            }
        });

        playerEditButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playerEditButton.setForeground(java.awt.Color.blue);
        playerEditButton.setText("Edit");
        playerEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerEditButtonActionPerformed(evt);
            }
        });

        playersDeleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        playersDeleteButton.setForeground(java.awt.Color.blue);
        playersDeleteButton.setText("Delete");
        playersDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playersDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createEditPlayerPanelLayout = new javax.swing.GroupLayout(createEditPlayerPanel);
        createEditPlayerPanel.setLayout(createEditPlayerPanelLayout);
        createEditPlayerPanelLayout.setHorizontalGroup(
            createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                        .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playerFirstNameLabel)
                            .addComponent(playerLastNameLabel))
                        .addGap(15, 15, 15)
                        .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playerLastNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(playerFirstNameTextField)))
                    .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                        .addComponent(playrSportLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                                        .addComponent(PlayerCreateButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerDoneEditingButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playerClearButton))
                                    .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(playerEditButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(playersDeleteButton)))
                                .addGap(0, 39, Short.MAX_VALUE))
                            .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                                .addComponent(playerShowPlayerSportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(playerIdLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerShowIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        createEditPlayerPanelLayout.setVerticalGroup(
            createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createEditPlayerPanelLayout.createSequentialGroup()
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerLastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerFirstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playrSportLabel)
                    .addComponent(playerIdLabel)
                    .addComponent(playerShowPlayerSportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerShowIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PlayerCreateButton)
                    .addComponent(playerDoneEditingButton)
                    .addComponent(playerClearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerEditButton)
                    .addComponent(playersDeleteButton)))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesUI/victorPolataevSmallerB&W.jpg"))); // NOI18N

        javax.swing.GroupLayout teamPanelLayout = new javax.swing.GroupLayout(teamPanel);
        teamPanel.setLayout(teamPanelLayout);
        teamPanelLayout.setHorizontalGroup(
            teamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, teamPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(teamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(teamPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createEditTeamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(createEditPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(teamPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        teamPanelLayout.setVerticalGroup(
            teamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(teamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(teamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(createEditTeamPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(createEditPlayerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        teamsSolistsTabbedPane.addTab("Teams", teamPanel);

        solistPanel.setBackground(new java.awt.Color(201, 195, 168));

        jPanel2.setBackground(new java.awt.Color(201, 195, 168));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "All Solists", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        solistsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(solistsList);

        solistsLabel.setText("Solists");

        nonCollegiateSportComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonCollegiateSportComboBoxActionPerformed(evt);
            }
        });

        individualSportLabel.setText("Individual Sport");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(individualSportLabel)
                            .addComponent(solistsLabel)
                            .addComponent(nonCollegiateSportComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(individualSportLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nonCollegiateSportComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(solistsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        createEditSolistPanel.setBackground(new java.awt.Color(201, 195, 168));
        createEditSolistPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Create / Edit Solist", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        solistCreateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solistCreateButton.setForeground(java.awt.Color.blue);
        solistCreateButton.setText("Create");
        solistCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solistCreateButtonActionPerformed(evt);
            }
        });

        solistDoneEditingButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solistDoneEditingButton.setForeground(java.awt.Color.blue);
        solistDoneEditingButton.setText("Done Editing");
        solistDoneEditingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solistDoneEditingButtonActionPerformed(evt);
            }
        });

        solistClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solistClearButton.setForeground(java.awt.Color.blue);
        solistClearButton.setText("Clear");
        solistClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solistClearButtonActionPerformed(evt);
            }
        });

        solistEditButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solistEditButton.setForeground(java.awt.Color.blue);
        solistEditButton.setText("Edit");
        solistEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solistEditButtonActionPerformed(evt);
            }
        });

        solistDeleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solistDeleteButton.setForeground(java.awt.Color.blue);
        solistDeleteButton.setText("Delete");
        solistDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solistDeleteButtonActionPerformed(evt);
            }
        });

        solistLastNameLabel.setText("Last Name:");

        solistLastNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                solistLastNameTextFieldFocusLost(evt);
            }
        });

        solistFirstNameLabel.setText("First Name:");

        solistFirstNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                solistFirstNameTextFieldFocusLost(evt);
            }
        });

        solistSportLabel.setText("Sport:");

        solistIDLabel.setText("ID:");

        javax.swing.GroupLayout createEditSolistPanelLayout = new javax.swing.GroupLayout(createEditSolistPanel);
        createEditSolistPanel.setLayout(createEditSolistPanelLayout);
        createEditSolistPanelLayout.setHorizontalGroup(
            createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                        .addComponent(solistLastNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(solistLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createEditSolistPanelLayout.createSequentialGroup()
                        .addComponent(solistFirstNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(solistFirstNameTextField))
                    .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                        .addComponent(solistSportLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(solistEditButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solistDeleteButton))
                            .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                                .addComponent(solistCreateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solistDoneEditingButton)
                                .addGap(13, 13, 13)
                                .addComponent(solistClearButton))
                            .addGroup(createEditSolistPanelLayout.createSequentialGroup()
                                .addComponent(solistShowSportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solistIDLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(solistShowIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        createEditSolistPanelLayout.setVerticalGroup(
            createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createEditSolistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solistLastNameLabel)
                    .addComponent(solistLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solistFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solistFirstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(solistIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(solistShowIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(solistShowSportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(solistSportLabel))
                .addGap(18, 18, 18)
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solistCreateButton)
                    .addComponent(solistDoneEditingButton)
                    .addComponent(solistClearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createEditSolistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solistDeleteButton)
                    .addComponent(solistEditButton))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(201, 195, 168));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesUI/tennisDiveSmallerB&W.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(201, 195, 168));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesUI/runnerCropSmaller.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout solistPanelLayout = new javax.swing.GroupLayout(solistPanel);
        solistPanel.setLayout(solistPanelLayout);
        solistPanelLayout.setHorizontalGroup(
            solistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(solistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createEditSolistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        solistPanelLayout.setVerticalGroup(
            solistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(solistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(solistPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(createEditSolistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        teamsSolistsTabbedPane.addTab("Solists", solistPanel);

        jPanel5.setBackground(new java.awt.Color(201, 195, 168));

        teamsSaveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        teamsSaveButton.setForeground(java.awt.Color.blue);
        teamsSaveButton.setText("Save");
        teamsSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamsSaveButtonActionPerformed(evt);
            }
        });

        backToMainButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backToMainButton.setForeground(java.awt.Color.blue);
        backToMainButton.setText("Back");
        backToMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(teamsSaveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backToMainButton)
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teamsSaveButton)
                    .addComponent(backToMainButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teamsSolistsTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teamsSolistsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //ActionEvents
    private void backToMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainButtonActionPerformed
        this.controllerObject.showInternalFrameObject(InternalFrameName.STARTMENUINTERNAL);
    }//GEN-LAST:event_backToMainButtonActionPerformed

    private void collegiateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collegiateComboBoxActionPerformed
        /*
        this.updateTeamsList();
        this.updateRosterList();
        this.clearCreateEditTeamForm();
        this.clearCreateEditPlayerForm();
        */
        this.actionsForCollegiateComboBoxActionPerformed();
    }//GEN-LAST:event_collegiateComboBoxActionPerformed

    private void nonCollegiateSportComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonCollegiateSportComboBoxActionPerformed
        /*
        this.updateSolistsList();
        this.clearCreateEditSolistForm();
        */
        this.actionsForNonCollegiateSportComboBoxActionPerformed();
    }//GEN-LAST:event_nonCollegiateSportComboBoxActionPerformed

    private void createTeamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTeamButtonActionPerformed
        try {
            this.checkCreateTeam();
            this.createTeam();
            updateTeamsList();
            clearCreateEditTeamForm();
        } catch (InvalidTextException e) {
                StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_createTeamButtonActionPerformed

    private void teamEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamEditButtonActionPerformed

        try{
            if (this.teamsList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                                                                    (String)this.collegiateComboBox.getSelectedItem());  
            this.teamNameTextField.setText(selectedTeam.toString());
            this.sportShowLabel.setText(selectedTeam.getSport().toString());
            selectedTeam=null;  
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Teams\" list");
        }
    }//GEN-LAST:event_teamEditButtonActionPerformed

    private void teamClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamClearButtonActionPerformed
        
        clearCreateEditTeamForm();
    }//GEN-LAST:event_teamClearButtonActionPerformed

    private void teamsDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamsDeleteButtonActionPerformed
        
        try{
            if (this.teamsList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            this.controllerObject.removeTeam(this.controllerObject.getSelectedTeam(
                                    this.teamsList.getSelectedValue().toString(),
                              (String)this.collegiateComboBox.getSelectedItem()));
            this.updateTeamsList();
            //this.clearCreateEditTeamForm();
             
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Teams\" list");
        }
    }//GEN-LAST:event_teamsDeleteButtonActionPerformed

    private void teamsDoneEditingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamsDoneEditingButtonActionPerformed
        try{
            this.checkCreateTeam();
            
            Team targetTeam = this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                                                                (String)this.collegiateComboBox.getSelectedItem());
            TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex = targetTeam.getRosterTreeSetWithGetIndex();
        
            this.removeTeam(targetTeam);
            this.createTeam(rosterTreeSetWithGetIndex);
            this.updateTeamsList();
            this.clearCreateEditTeamForm();
        }catch (InvalidTextException e) {
                StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_teamsDoneEditingButtonActionPerformed

    
    private void teamsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_teamsListValueChanged
        // TODO add your handling code here:
        //Anagkaia ta 2 if. Alliws eixa nullpointer Exception
        
        //if (rosterListFlagFirstTime == true)
            //return;
            
        if (this.teamsList.getSelectedValue() == null)
            return;
        
            this.updateRosterList();
    }//GEN-LAST:event_teamsListValueChanged

    private void PlayerCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerCreateButtonActionPerformed
        
        try {
            this.checkCreatePlayer();
            this.createPlayer();
            this.updateRosterList();
            this.clearCreateEditPlayerForm();
        } catch (InvalidTextException e) {
            StaticShower.showWarningMessageForInvalidInput(e);
        }
        
    }//GEN-LAST:event_PlayerCreateButtonActionPerformed

    private void playersDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playersDeleteButtonActionPerformed
        
        try{
            if (this.rosterList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                (String)this.collegiateComboBox.getSelectedItem());
            this.controllerObject.removePlayer(selectedTeam, this.rosterList.getSelectedIndex());
            this.updateRosterList();    
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Roster\" list");
        }
    }//GEN-LAST:event_playersDeleteButtonActionPerformed

    private void playerEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerEditButtonActionPerformed
        // TODO add your handling code here:
        
        try{
            if (this.rosterList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                (String)this.collegiateComboBox.getSelectedItem());
        
            Player selectedPlayer = selectedTeam.getPlayerAt(this.rosterList.getSelectedIndex());
        
            this.playerLastNameTextField.setText(selectedPlayer.getName());
            this.playerFirstNameTextField.setText(selectedPlayer.getFirstName());
            this.playerShowPlayerSportLabel.setText(selectedPlayer.getSport().toString());
            this.playerShowIdLabel.setText(selectedPlayer.getAthleteID().toString()); 
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Roster\" list");
        }
    }//GEN-LAST:event_playerEditButtonActionPerformed

    private void playerDoneEditingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerDoneEditingButtonActionPerformed
        try {
            this.checkCreatePlayer();
            
            Team selectedTeam =this.controllerObject.getSelectedTeam(this.teamsList.getSelectedValue().toString(),
                                                                (String)this.collegiateComboBox.getSelectedItem());
            Player selectedPlayer = selectedTeam.getPlayerAt(this.rosterList.getSelectedIndex());
            int selectedPlayerIdInt = selectedPlayer.getAthleteID();
        
            this.removePlayer();
            this.createPlayer(selectedPlayerIdInt);
            this.updateRosterList();
            this.clearCreateEditPlayerForm();
        } catch (InvalidTextException e) {
            StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_playerDoneEditingButtonActionPerformed

    private void playerClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerClearButtonActionPerformed
        
        this.clearCreateEditPlayerForm();
    }//GEN-LAST:event_playerClearButtonActionPerformed

    private void solistCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solistCreateButtonActionPerformed
        try {
            this.checkCreateSolist();
            this.createSolist();
            updateSolistsList();
            clearCreateEditSolistForm();
        } catch (InvalidTextException e) {
            StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_solistCreateButtonActionPerformed

    private void solistEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solistEditButtonActionPerformed
        // TODO add your handling code here:
        try{
            if (this.solistsList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            Solist selectedSolist =this.controllerObject.getSelectedSolist
                                                            (this.solistsList.getSelectedValue().toString());
            this.solistLastNameTextField.setText(selectedSolist.getLastName());
            this.solistFirstNameTextField.setText(selectedSolist.getFirstName());
            this.solistShowSportLabel.setText(selectedSolist.getSport().toString());
            this.solistShowIDLabel.setText(selectedSolist.getAthleteID().toString());
            selectedSolist=null;     
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Solists\" list");
        }
    }//GEN-LAST:event_solistEditButtonActionPerformed

    private void solistDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solistDeleteButtonActionPerformed
        // TODO add your handling code here:
        try{
            if (this.solistsList.getSelectedIndex() ==-1)
                throw new NullPointerException();
            
            this.controllerObject.removeSolist(this.solistsList.getSelectedValue().toString());
            this.updateSolistsList();
            //this.clearCreateEditTeamForm();        
        }
        catch(NullPointerException e){
            StaticShower.showWarningMessage(StaticShower.NOT_EXACTLY_ONE_LINE + 
                                                            " from \"Solists\" list");
        } 
    }//GEN-LAST:event_solistDeleteButtonActionPerformed

    private void solistDoneEditingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solistDoneEditingButtonActionPerformed
        
        try {
            this.checkCreateSolist();
            
            int selectedSolistId = this.controllerObject.getSelectedSolist
                                                (this.solistsList.getSelectedValue().toString()).getAthleteID();
            this.removeSolist();
            this.createSolist(selectedSolistId);
            this.updateSolistsList();
            this.clearCreateEditSolistForm();
        } catch (InvalidTextException e) {
            StaticShower.showWarningMessageForInvalidInput(e);
        }
    }//GEN-LAST:event_solistDoneEditingButtonActionPerformed

    private void solistClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solistClearButtonActionPerformed
        
        this.clearCreateEditSolistForm();
    }//GEN-LAST:event_solistClearButtonActionPerformed

    private void teamsSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamsSaveButtonActionPerformed
        
        this.controllerObject.serialize();
    }//GEN-LAST:event_teamsSaveButtonActionPerformed

    private void teamNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_teamNameTextFieldFocusLost
        this.teamNameTextField.setText(StaticNormalizer.normalizeTextString
                                        (this.teamNameTextField.getText()));
    }//GEN-LAST:event_teamNameTextFieldFocusLost

    private void playerLastNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_playerLastNameTextFieldFocusLost
        // TODO add your handling code here:
        this.playerLastNameTextField.setText(StaticNormalizer.normalizeTextString
                                        (this.playerLastNameTextField.getText()));
    }//GEN-LAST:event_playerLastNameTextFieldFocusLost

    private void playerFirstNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_playerFirstNameTextFieldFocusLost
        // TODO add your handling code here:
        this.playerFirstNameTextField.setText(StaticNormalizer.normalizeTextString
                                        (this.playerFirstNameTextField.getText()));
    }//GEN-LAST:event_playerFirstNameTextFieldFocusLost

    private void solistLastNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_solistLastNameTextFieldFocusLost
        // TODO add your handling code here:
        this.solistLastNameTextField.setText(StaticNormalizer.normalizeTextString
                                        (this.solistLastNameTextField.getText()));
    }//GEN-LAST:event_solistLastNameTextFieldFocusLost

    private void solistFirstNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_solistFirstNameTextFieldFocusLost
        // TODO add your handling code here:
        this.solistFirstNameTextField.setText(StaticNormalizer.normalizeTextString
                                        (this.solistFirstNameTextField.getText()));
    }//GEN-LAST:event_solistFirstNameTextFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PlayerCreateButton;
    private javax.swing.JButton backToMainButton;
    private javax.swing.JComboBox<String> collegiateComboBox;
    private javax.swing.JLabel collegiateSportLabel;
    private javax.swing.JPanel createEditPlayerPanel;
    private javax.swing.JPanel createEditSolistPanel;
    private javax.swing.JPanel createEditTeamPanel;
    private javax.swing.JButton createTeamButton;
    private javax.swing.JLabel individualSportLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> nonCollegiateSportComboBox;
    private javax.swing.JButton playerClearButton;
    private javax.swing.JButton playerDoneEditingButton;
    private javax.swing.JButton playerEditButton;
    private javax.swing.JLabel playerFirstNameLabel;
    private javax.swing.JTextField playerFirstNameTextField;
    private javax.swing.JLabel playerIdLabel;
    private javax.swing.JLabel playerLastNameLabel;
    private javax.swing.JTextField playerLastNameTextField;
    private javax.swing.JLabel playerShowIdLabel;
    private javax.swing.JLabel playerShowPlayerSportLabel;
    private javax.swing.JButton playersDeleteButton;
    private javax.swing.JLabel playrSportLabel;
    private javax.swing.JLabel rosterLabel;
    private javax.swing.JList<String> rosterList;
    private javax.swing.JLabel showNoOfPlayersLabel;
    private javax.swing.JButton solistClearButton;
    private javax.swing.JButton solistCreateButton;
    private javax.swing.JButton solistDeleteButton;
    private javax.swing.JButton solistDoneEditingButton;
    private javax.swing.JButton solistEditButton;
    private javax.swing.JLabel solistFirstNameLabel;
    private javax.swing.JTextField solistFirstNameTextField;
    private javax.swing.JLabel solistIDLabel;
    private javax.swing.JLabel solistLastNameLabel;
    private javax.swing.JTextField solistLastNameTextField;
    private javax.swing.JPanel solistPanel;
    private javax.swing.JLabel solistShowIDLabel;
    private javax.swing.JLabel solistShowSportLabel;
    private javax.swing.JLabel solistSportLabel;
    private javax.swing.JLabel solistsLabel;
    private javax.swing.JList<String> solistsList;
    private javax.swing.JLabel sportShowLabel;
    private javax.swing.JButton teamClearButton;
    private javax.swing.JButton teamEditButton;
    private javax.swing.JTextField teamNameTextField;
    private javax.swing.JPanel teamPanel;
    private javax.swing.JButton teamsDeleteButton;
    private javax.swing.JButton teamsDoneEditingButton;
    private javax.swing.JLabel teamsLabel;
    private javax.swing.JList<String> teamsList;
    private javax.swing.JButton teamsSaveButton;
    private javax.swing.JTabbedPane teamsSolistsTabbedPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
