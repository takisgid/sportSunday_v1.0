
package view;

import adjustedClasses.StaticShower;
import controller.ReportInternalFrameController;
import enums.InternalFrameName;
import enums.TableSortEnum;
import exceptions.InvalidNonNegativeIntegerInputException;
import exceptions.InvalidResultTieException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Championship;

public class ReportInternalFrame extends AbstractJInternalFrame {

    ReportInternalFrameController controllerObject; 
    
    public ReportInternalFrame() {
        initComponents();
        
        createModels();
    }
    
    public void setReportInternalFrameControllerObject(ReportInternalFrameController 
                                                                            controllerObject){
        this.controllerObject = controllerObject;
    }
    
    private DefaultTableModel gamesTableModelObject =null;
    private DefaultTableModel tableTableModelObject = null;
    private DefaultComboBoxModel<String> typeOfSortForTableComboBoxModelObject = null;
    
    public void createModels(){
       
        this.gamesTableModelObject =(DefaultTableModel) this.gamesTable.getModel();
        this.tableTableModelObject = (DefaultTableModel) this.tableTable.getModel();
        
        this.typeOfSortForTableComboBoxModelObject = new DefaultComboBoxModel<>();
        this.typeOfSortForTableComboBox.setModel(typeOfSortForTableComboBoxModelObject);
    }
    
    public void updateInternalFrame(){
        this.updateInfoPanel();
        this.updateGamesTable();
        this.updateTableTable();
    }
    
    public void updateInfoPanel(){
        
        Championship championshipObject = this.controllerObject.getChampionshipObject();
        String minRosterTostring = championshipObject.getMinRoster().toString();
        
        this.championshipLabel.setText(championshipObject.toString());
        
        this.basicInfoLabel.setText(minRosterTostring + "vs" + minRosterTostring +
                " " + championshipObject.getSport().toString() + " " +
                championshipObject.getChampionshipTypeString()
                );
        
        //this.finalizableTextField.setText(getStringForFinalizableTextfield());
        //this.finalizedTextField.setText(this.getStringForFinalizedTextfield());
        
        updateVisibilityOfLotteryButton();
        
        writeReportGuideString();
    }
    
    
    public void updatetypeOfSortForTableComboBox(){
        this.controllerObject.updateTypeOfSortForTableComboBoxModel(typeOfSortForTableComboBoxModelObject);
    }
    
    private Integer phaseNumber =0;
    
    public void setPhaseNumber(Integer CurrentSportDay) {
        this.phaseNumber = CurrentSportDay;
    }
    
    public Integer getPhaseNumber() {
        return this.phaseNumber;
    }
    
    private String currentSportDayDescriptionString;
    
    public void setCurrentSportDayDescriptionString(String currentSportDayDescriptionString) {
        this.currentSportDayDescriptionString = currentSportDayDescriptionString;
    }
    
    public void updateGamesTable(){
             
        super.ClearJTable(gamesTableModelObject);
        
        //this.phaseLabel.setText(this.phaseNumber.toString());
        this.phaseLabel.setText(this.currentSportDayDescriptionString);
        
        // method που περνά τα πρωταθληματα στο model toy pinaka protathlimaton
        this.controllerObject.updateGamesTableModel(gamesTableModelObject,phaseNumber); 
        
        //this.tableTable.setPreferredScrollableViewportSize(this.tableTable.getPreferredSize());
        //tableTable.setFillsViewportHeight(true);
    }
    
    TableSortEnum jTableSortEnumObject = TableSortEnum.BASIC;
    
    public void resetIndicatorSortOfTableJTableToBasic(){
        this.jTableSortEnumObject = TableSortEnum.BASIC;
        this.typeOfSortForTableComboBox.setSelectedIndex(0);
    }
    
    
    
    public void updateTableTable(){
             
        super.ClearJTable(tableTableModelObject);
        
        // method που περνά τα πρωταθληματα στο model toy pinaka protathlimaton
        this.controllerObject.updateTableTableModel(tableTableModelObject,this.jTableSortEnumObject);  
        
        //dokimi
        this.updateVisibilityOfLotteryButton();
    }
    public void updateVisibilityOfLotteryButton(){
        
        if (this.controllerObject.getVisibilityOfLotteryButton())
            this.lotteryButton.setEnabled(true);
        else
            this.lotteryButton.setEnabled(false);
    }
    
    public String getStringForFinalizableTextfield(){
        if (this.controllerObject.getChampionshipObject().getFinalizable())
            return "Yes";
        else
            return "No";
    }
    
    public String getStringForFinalizedTextfield(){
        if (this.controllerObject.getChampionshipObject().getFinalized())
            return "Yes";
        else
            return "No";
    }
    
    public void stopEditingAtEditingCellOfGamesTable(){
        if(this.gamesTable.isEditing())
            this.gamesTable.getCellEditor().stopCellEditing();
    }
    
    public boolean isGamesTableEditable(){
        return this.controllerObject.isGamesTableEditable();
    }
    
    public void handlePointsColumn(){
        boolean flag = this.controllerObject.isPointBased();
        
        if (flag){
            this.tableTable.getColumnModel().getColumn(1).setMinWidth(50);
            this.tableTable.getColumnModel().getColumn(1).setMaxWidth(50);
        } 
        else{
            this.tableTable.getColumnModel().getColumn(1).setMinWidth(0);
            this.tableTable.getColumnModel().getColumn(1).setMaxWidth(0);
        }
    }
    
    public void handleTiesColumn(){
        boolean flag = this.controllerObject.isTieAllowed();
        
        if (flag){
            this.tableTable.getColumnModel().getColumn(4).setMinWidth(50);
            this.tableTable.getColumnModel().getColumn(4).setMaxWidth(50);
        } 
        else{
            this.tableTable.getColumnModel().getColumn(4).setMinWidth(0);
            this.tableTable.getColumnModel().getColumn(4).setMaxWidth(0);
        }
    }
    
    public void eraseCellValueOfGamesTable(int rowIndex,int columnIndex){
        this.gamesTableModelObject.setValueAt("-", rowIndex, columnIndex);
    }
    
    public void writeReportGuideString(){
        this.guideTextArea.setText(this.controllerObject.getReportGuideString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoPanel = new javax.swing.JPanel();
        championshipLabel = new javax.swing.JLabel();
        basicInfoLabel = new javax.swing.JLabel();
        gamesProgramPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gamesTable = new javax.swing.JTable();
        phaseLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        previousSportDayButton = new javax.swing.JButton();
        nextSportDayButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTable = new javax.swing.JTable();
        typeOfSortForTableComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lotteryButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        guideTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        infoPanel.setBackground(new java.awt.Color(201, 195, 168));
        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Championship", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        championshipLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(championshipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basicInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(championshipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(basicInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gamesProgramPanel.setBackground(new java.awt.Color(201, 195, 168));
        gamesProgramPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Games Fixtures", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        gamesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Game", "Home", "Away"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            //edw kanw th magkia
            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if (isGamesTableEditable())
                return canEdit [columnIndex];
                else
                return false;
            }
        });
        gamesTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                gamesTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(gamesTable);
        if (gamesTable.getColumnModel().getColumnCount() > 0) {
            gamesTable.getColumnModel().getColumn(0).setMinWidth(250);
            gamesTable.getColumnModel().getColumn(0).setPreferredWidth(250);
            gamesTable.getColumnModel().getColumn(0).setMaxWidth(250);
            gamesTable.getColumnModel().getColumn(1).setMinWidth(40);
            gamesTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            gamesTable.getColumnModel().getColumn(1).setMaxWidth(40);
            gamesTable.getColumnModel().getColumn(2).setMinWidth(40);
            gamesTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            gamesTable.getColumnModel().getColumn(2).setMaxWidth(40);
        }

        jPanel2.setBackground(new java.awt.Color(201, 195, 168));

        previousSportDayButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        previousSportDayButton.setForeground(java.awt.Color.blue);
        previousSportDayButton.setText("<<");
        previousSportDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousSportDayButtonActionPerformed(evt);
            }
        });

        nextSportDayButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nextSportDayButton.setForeground(java.awt.Color.blue);
        nextSportDayButton.setText(">>");
        nextSportDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextSportDayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(previousSportDayButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextSportDayButton)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousSportDayButton)
                    .addComponent(nextSportDayButton)))
        );

        javax.swing.GroupLayout gamesProgramPanelLayout = new javax.swing.GroupLayout(gamesProgramPanel);
        gamesProgramPanel.setLayout(gamesProgramPanelLayout);
        gamesProgramPanelLayout.setHorizontalGroup(
            gamesProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamesProgramPanelLayout.createSequentialGroup()
                .addGroup(gamesProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gamesProgramPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamesProgramPanelLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamesProgramPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(phaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        gamesProgramPanelLayout.setVerticalGroup(
            gamesProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamesProgramPanelLayout.createSequentialGroup()
                .addComponent(phaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        tablePanel.setBackground(new java.awt.Color(201, 195, 168));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Table", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        tableTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Competitor", "Points", "Games", "Wins", "Ties", "Defeats", "Off", "Def", "Diff"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableTable);
        if (tableTable.getColumnModel().getColumnCount() > 0) {
            tableTable.getColumnModel().getColumn(0).setMinWidth(125);
            tableTable.getColumnModel().getColumn(0).setPreferredWidth(125);
            tableTable.getColumnModel().getColumn(0).setMaxWidth(125);
            tableTable.getColumnModel().getColumn(1).setMinWidth(50);
            tableTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(1).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(2).setMinWidth(50);
            tableTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(2).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(3).setMinWidth(50);
            tableTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(3).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(4).setMinWidth(50);
            tableTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(4).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(5).setMinWidth(50);
            tableTable.getColumnModel().getColumn(5).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(5).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(6).setMinWidth(50);
            tableTable.getColumnModel().getColumn(6).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(6).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(7).setMinWidth(50);
            tableTable.getColumnModel().getColumn(7).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(7).setMaxWidth(50);
            tableTable.getColumnModel().getColumn(8).setMinWidth(50);
            tableTable.getColumnModel().getColumn(8).setPreferredWidth(50);
            tableTable.getColumnModel().getColumn(8).setMaxWidth(50);
        }

        typeOfSortForTableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeOfSortForTableComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setText("Sorted By:");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeOfSortForTableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(typeOfSortForTableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        buttonsPanel.setBackground(new java.awt.Color(201, 195, 168));

        backButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        backButton.setForeground(java.awt.Color.blue);
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveButton.setForeground(java.awt.Color.blue);
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addGap(12, 12, 12))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setBackground(new java.awt.Color(201, 195, 168));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("Report");

        jPanel3.setBackground(new java.awt.Color(201, 195, 168));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Available in version 2. Sorry!!!");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(201, 195, 168));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 0, 0)), "Short advice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        lotteryButton.setText("Lottery");
        lotteryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lotteryButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(null);

        guideTextArea.setEditable(false);
        guideTextArea.setBackground(new java.awt.Color(201, 195, 168));
        guideTextArea.setColumns(10);
        guideTextArea.setRows(1);
        jScrollPane3.setViewportView(guideTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(lotteryButton)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lotteryButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesUI/colina_b&w22.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gamesProgramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(infoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gamesProgramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        stopEditingAtEditingCellOfGamesTable();
        
        this.controllerObject.showInternalFrameObject(InternalFrameName.CHAMPIONSHIPINTERNAL);
    }//GEN-LAST:event_backButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        
        this.controllerObject.updatePhaseNumberOfInternalFrameOBject();
        this.controllerObject.updateCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber();
        this.resetIndicatorSortOfTableJTableToBasic();
        this.updateInternalFrame();
        
        this.handlePointsColumn();
        this.handleTiesColumn();
        
    }//GEN-LAST:event_formComponentShown

    private void lotteryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lotteryButtonActionPerformed
        // TODO add your handling code here:
        
        this.controllerObject.makeLottery();
        
        //this.finalizedTextField.setText(this.getStringForFinalizedTextfield());
        this.controllerObject.updateCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber();
        this.updateGamesTable();
        updateVisibilityOfLotteryButton();
        
        if (!this.controllerObject.isPointBased())
            this.nextSportDayButton.doClick();
        
        this.writeReportGuideString();
    }//GEN-LAST:event_lotteryButtonActionPerformed

    private void nextSportDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextSportDayButtonActionPerformed
        // TODO add your handling code here:
        
        stopEditingAtEditingCellOfGamesTable();
            
        if (this.phaseNumber < this.controllerObject.getMaxOfIndexForSportdays()){
            this.phaseNumber++;
            this.controllerObject.updateCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber();
            this.updateGamesTable();
        }
    }//GEN-LAST:event_nextSportDayButtonActionPerformed

    private void previousSportDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousSportDayButtonActionPerformed
        // TODO add your handling code here:
        stopEditingAtEditingCellOfGamesTable();
        
        if (this.phaseNumber > 0){
            this.phaseNumber--;
            this.controllerObject.updateCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber();
            this.updateGamesTable();
        }
    }//GEN-LAST:event_previousSportDayButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        stopEditingAtEditingCellOfGamesTable();
        
        this.controllerObject.serialize();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void gamesTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_gamesTablePropertyChange
        // TODO add your handling code here:
  
        int rowModelIndex;
        //int columnModelIndex;
        
        rowModelIndex = this.gamesTable.convertRowIndexToModel(this.gamesTable.getEditingRow()); 
        //columnModelIndex = this.gamesTable.convertColumnIndexToModel(this.gamesTable.getEditingColumn()); 
        
        if (rowModelIndex >=0)
            try{
                this.controllerObject.updateScores(rowModelIndex, this.phaseNumber,
                                                                            gamesTableModelObject);
                //this.resetIndicatorSortOfTableJTableToBasic();
                //this.updateTableTable();
            }
            catch(InvalidResultTieException e){  
                //this.controllerObject.normalizeInputOfCell(rowModelIndex,gamesTableModelObject);
                StaticShower.showWarningMessageForInvalidInput(e);
            }
            catch (InvalidNonNegativeIntegerInputException e){
                StaticShower.showWarningMessageForInvalidInput(e);
                this.controllerObject.normalizeInputOfCell(rowModelIndex,gamesTableModelObject);
            }
            catch(NumberFormatException e){
                     
                //this.controllerObject.normalizeInputOfCell(rowModelIndex,gamesTableModelObject);
            }
            finally{
                this.resetIndicatorSortOfTableJTableToBasic();
                this.updateTableTable();
                this.writeReportGuideString();
            }
        
    }//GEN-LAST:event_gamesTablePropertyChange

    private void typeOfSortForTableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeOfSortForTableComboBoxActionPerformed
        // TODO add your handling code here:
        //this.stopEditingAtEditingCellOfGamesTable();
        
        this.jTableSortEnumObject = 
                TableSortEnum.byGivenOrderNumber(this.typeOfSortForTableComboBox.getSelectedIndex());
        try{
        this.updateTableTable();
        }catch (NullPointerException e){
            
        }
    }//GEN-LAST:event_typeOfSortForTableComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel basicInfoLabel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JLabel championshipLabel;
    private javax.swing.JPanel gamesProgramPanel;
    private javax.swing.JTable gamesTable;
    private javax.swing.JTextArea guideTextArea;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton lotteryButton;
    private javax.swing.JButton nextSportDayButton;
    private javax.swing.JLabel phaseLabel;
    private javax.swing.JButton previousSportDayButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTable tableTable;
    private javax.swing.JComboBox<String> typeOfSortForTableComboBox;
    // End of variables declaration//GEN-END:variables
}
