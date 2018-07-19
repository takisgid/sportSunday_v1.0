
package controller;

import enums.InternalFrameName;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.AmateurSports;
import model.Championship;
import serialization.Serializer;
import view.AboutInternalFrame;
import view.ChampionshipsInternalFrame;
import view.CompetitorsInternalFrame;
import view.MainContainerFrame;
import view.TeamsParticipationsInternalFrame;
import view.ReportInternalFrame;
import view.StartMenuInternalFrame;
import view.SolistParticipationInternalFrame;

public class MainContainerFrameController implements Serializable {
    
    private ArrayList<JInternalFrame> jInternalFrameList;
    
    private AmateurSports amateurSportsObject;
    private MainContainerFrame mainContainerFrameObject;
    private Serializer serializerObject;
    
    private StartMenuInternalFrame startMenuInternalFrameObject;
    private StartMenuInternalFrameController startMenuInternalFrameControllerObject;
    
    private ChampionshipsInternalFrame championshipsInternalFrameObject;
    private ChampionshipsInternalFrameController championshipsInternalFrameControllerObject;
    
    private CompetitorsInternalFrame competitorsInternalFrameObject;
    private CompetitorsInternalFrameController competitorsInternalFrameControllerObject;
    
    private TeamsParticipationsInternalFrame teamsParticipationsInternalFrameObject;
    private TeamsParticipationsInternalFrameController teamsParticipationsInternalFrameControllerObject;
    
    private SolistParticipationInternalFrame solistsParticipationsInternalFrameObject;
    private SolistsParticipationsInternalFrameController solistsParticipationsInternalFrameControllerObject;
    
    private ReportInternalFrame reportInternalFrameObject;
    private ReportInternalFrameController reportInternalFrameControllerObject;
    
    private AboutInternalFrame aboutInternalFrameObject;
    private AboutInternalFrameController aboutInternalFrameControllerObject;
    
    // constructor
    public MainContainerFrameController(AmateurSports amateurSportsObject) {
        this.amateurSportsObject = amateurSportsObject;
        
        jInternalFrameList = new ArrayList<>();
    }
    
    // setters
    public void setAmateurSportsObject(AmateurSports amateurSportsObject){
        this.amateurSportsObject = amateurSportsObject;
        
    }
    public void setMainContainerFrameObject(MainContainerFrame mainContainerFrameObject){
        this.mainContainerFrameObject = mainContainerFrameObject;
    }
    
    public void setSerializatorObject(Serializer serializatorObject){
        this.serializerObject = serializatorObject;
        
    }
    
    // getters
    
    public AmateurSports getAmateurSportsObject(){
        return this.amateurSportsObject;
    }
    /*
    public StartMenuInternalFrame getStartMenuInternalFrameObject(){
        return startMenuInternalFrameObject;
    }
    public ChampionshipsInternalFrame getChampionshipsInternalFrameObject(){
        return championshipsInternalFrameObject;
    }
    public CompetitorsInternalFrame getCompetitorsInternalFrameObject(){
        return competitorsInternalFrameObject;
    }
    */
    //Creation of all internalFrames
        
        // creation of startMenuInternalFrameObject,championshipsInternalFrameObject
        // competitorsInternalFrameObject. 
        // This (MainContainerFrameObject) is passed as argument.
        // γίνονται add στο desktopPane και τα μεγέθη τους προσαρμόζονται ωστε
        // να καλύπτουν όλο το desktopPane
    public void createInternalFramesAndControllers(){
        
        CommonMethodsController commonMethodsControllerObject = new CommonMethodsController
                                                             (this.amateurSportsObject, this.serializerObject);
        
        this.startMenuInternalFrameObject = new StartMenuInternalFrame();
        jInternalFrameList.add(startMenuInternalFrameObject);
        this.mainContainerFrameObject.
                                addJInternalFrameObjectToMainContainerJDesktopPaneObject(startMenuInternalFrameObject);
        this.startMenuInternalFrameControllerObject = new StartMenuInternalFrameController(this,
                                                                                    startMenuInternalFrameObject);
                                                                                        
        
        
        this.championshipsInternalFrameObject = new ChampionshipsInternalFrame();
        jInternalFrameList.add(championshipsInternalFrameObject);
        this.mainContainerFrameObject.
                            addJInternalFrameObjectToMainContainerJDesktopPaneObject(championshipsInternalFrameObject);
        this.championshipsInternalFrameControllerObject = new ChampionshipsInternalFrameController
                                                   (this,championshipsInternalFrameObject, this.amateurSportsObject,
                                                   commonMethodsControllerObject);
        
        this.competitorsInternalFrameObject = new CompetitorsInternalFrame();
        jInternalFrameList.add(competitorsInternalFrameObject);
        this.mainContainerFrameObject.
                            addJInternalFrameObjectToMainContainerJDesktopPaneObject(competitorsInternalFrameObject);
        this.competitorsInternalFrameControllerObject = new CompetitorsInternalFrameController
                                                  (this,competitorsInternalFrameObject, this.amateurSportsObject,
                                                  commonMethodsControllerObject);
        
        this.teamsParticipationsInternalFrameObject = new TeamsParticipationsInternalFrame();
        jInternalFrameList.add(teamsParticipationsInternalFrameObject);
        this.mainContainerFrameObject.
                        addJInternalFrameObjectToMainContainerJDesktopPaneObject(teamsParticipationsInternalFrameObject);
        this.teamsParticipationsInternalFrameControllerObject = new TeamsParticipationsInternalFrameController
                                                (this,teamsParticipationsInternalFrameObject, 
                                                        this.amateurSportsObject,commonMethodsControllerObject);
        
        this.solistsParticipationsInternalFrameObject = new SolistParticipationInternalFrame();
        jInternalFrameList.add(solistsParticipationsInternalFrameObject);
        this.mainContainerFrameObject.
                      addJInternalFrameObjectToMainContainerJDesktopPaneObject(solistsParticipationsInternalFrameObject);
        this.solistsParticipationsInternalFrameControllerObject = new SolistsParticipationsInternalFrameController
                                               (this,solistsParticipationsInternalFrameObject, 
                                                       this.amateurSportsObject,commonMethodsControllerObject);
        
        this.reportInternalFrameObject = new ReportInternalFrame();
        jInternalFrameList.add(reportInternalFrameObject);
        this.mainContainerFrameObject.
                            addJInternalFrameObjectToMainContainerJDesktopPaneObject(reportInternalFrameObject);
        this.reportInternalFrameControllerObject = new ReportInternalFrameController
                                                  (this,reportInternalFrameObject, 
                                                     this.amateurSportsObject, commonMethodsControllerObject);
   
        this.aboutInternalFrameObject = new AboutInternalFrame();
        jInternalFrameList.add(aboutInternalFrameObject);
        this.mainContainerFrameObject.
                            addJInternalFrameObjectToMainContainerJDesktopPaneObject(aboutInternalFrameObject);
        this.aboutInternalFrameControllerObject = new AboutInternalFrameController
                                                  (this,aboutInternalFrameObject, 
                                                     this.amateurSportsObject, commonMethodsControllerObject);
        
        // make visible only the startMenuInternalFrameObject
        showInternalFrameObject(InternalFrameName.STARTMENUINTERNAL);
    } 
    
    public void initializeInternalFrames(){
        
        // initialize components of championshipsInternalFrameObject
        this.championshipsInternalFrameControllerObject.initializeInternalFrameObjectComponents();
        
        // initialize components of competetorssInternalFrameObject
        this.competitorsInternalFrameControllerObject.initializeInternalFrameObjectComponents();
        
        this.reportInternalFrameControllerObject.initializeInternalFrameObjectComponents();
        
        this.setBackgroundColourToJInternalFrameObjects();
        this.removeUselessButtonFromJInternalFrameObjects();
        this.removeAbilityToMoveFromJInternalFrameObjects();
    }
    
    // na to kanw me int apo panw pros ta katw kai switch
    public void showInternalFrameObject(InternalFrameName internalFrameNameObject){
        
        switch (internalFrameNameObject) {
            
            case STARTMENUINTERNAL:
                this.startMenuInternalFrameObject.show();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.hide();
                break;
                    
            case CHAMPIONSHIPINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.show();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.hide();
                break;
                         
            case COMPETITORSINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.show();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.hide();
                break;
                      
            case TEAMSPARTICIPATIONSINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.show();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.hide();
                break;
                
            case SOLISTSPARTICIPATIONSINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.show();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.hide();
                break;
                
            case REPORTINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.show();
                this.aboutInternalFrameObject.hide();
                break;
                
            case ABOUTINTERNAL:
                this.startMenuInternalFrameObject.hide();
                this.championshipsInternalFrameObject.hide();
                this.competitorsInternalFrameObject.hide();
                this.teamsParticipationsInternalFrameObject.hide();
                this.solistsParticipationsInternalFrameObject.hide();
                this.reportInternalFrameObject.hide();
                this.aboutInternalFrameObject.show();
                break;
                
            default:
                break;
        }
    }
    
    public void setChampionshipObjectToteamsParticipationsInternalFrameControllerObject
                                                        (Championship championshipObject) {
        
        this.teamsParticipationsInternalFrameControllerObject.setChampionshipObject(championshipObject);
    }
    
    public void setChampionshipObjectTosolistssParticipationsInternalFrameControllerObject
                                                        (Championship championshipObject) {
        
        this.solistsParticipationsInternalFrameControllerObject.setChampionshipObject(championshipObject);
    }
    
    public void setChampionshipObjectToReportInternalFrameControllerObject
                                                    (Championship championshipObject) {
        this.reportInternalFrameControllerObject.setChampionshipObject(championshipObject);
    }
                                                    
    public void setBackgroundColourToJInternalFrameObjects() {
        
        // 3 dokimes: manisio asprogrey, krem 201,195,168, darkGrey 102,102,102
        
        float[] coloursArray = Color.RGBtoHSB(201, 195, 168, null);
        
        for (JInternalFrame currentJInternalFrameObject: this.jInternalFrameList)
            currentJInternalFrameObject.getContentPane().setBackground
                                    (Color.getHSBColor(coloursArray[0], coloursArray[1], coloursArray[2]));;
    }
    
    public void removeUselessButtonFromJInternalFrameObjects() {
        
        for (JInternalFrame currentJInternalFrameObject: this.jInternalFrameList){
            BasicInternalFrameUI ui = (BasicInternalFrameUI)currentJInternalFrameObject.getUI();
            Container north = (Container)ui.getNorthPane();
            north.remove(0);
            north.validate();
            north.repaint();
        }
            
    }
    
    public void removeAbilityToMoveFromJInternalFrameObjects() {
        
        for (JInternalFrame currentJInternalFrameObject: this.jInternalFrameList){
            
            //remove the listeners from UI which make the frame move
            BasicInternalFrameUI basicInternalFrameUI = ((javax.swing.plaf.basic.BasicInternalFrameUI)
                                                                  currentJInternalFrameObject.getUI());
            
            for (MouseListener listener : basicInternalFrameUI.getNorthPane().getMouseListeners()) {
                basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
            }
        }      
    }
    
    public void refreshChampionshipsInternaFrame(){
        this.championshipsInternalFrameObject.updateChampionshipsTable();
    }
    
    public void refreshCompetitorsFrame(){
        this.competitorsInternalFrameObject.actionsForCollegiateComboBoxActionPerformed();
        this.competitorsInternalFrameObject.actionsForNonCollegiateSportComboBoxActionPerformed();
    }
}
