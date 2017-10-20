/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poams;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ShawnKyzer
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByActId", query = "SELECT a FROM Activity a WHERE a.actId = :actId"),
    @NamedQuery(name = "Activity.findByActDateCreated", query = "SELECT a FROM Activity a WHERE a.actDateCreated = :actDateCreated"),
    @NamedQuery(name = "Activity.findByActDateDue", query = "SELECT a FROM Activity a WHERE a.actDateDue = :actDateDue"),
    @NamedQuery(name = "Activity.findByActResolutionDate", query = "SELECT a FROM Activity a WHERE a.actResolutionDate = :actResolutionDate"),
    @NamedQuery(name = "Activity.findByActNoteUserCreate", query = "SELECT a FROM Activity a WHERE a.actNoteUserCreate = :actNoteUserCreate"),
    @NamedQuery(name = "Activity.findByActNoteUserAssigned", query = "SELECT a FROM Activity a WHERE a.actNoteUserAssigned = :actNoteUserAssigned"),
    @NamedQuery(name = "Activity.findByActUserAssignedId", query = "SELECT a FROM Activity a WHERE a.actUserAssignedId = :actUserAssignedId"),
    @NamedQuery(name = "Activity.findByUserId", query = "SELECT a FROM Activity a WHERE a.userId = :userId"),
    @NamedQuery(name = "Activity.findByPrjId", query = "SELECT a FROM Activity a WHERE a.prjId = :prjId")})
public class Activity implements Serializable {
    @Column(name = "ACT_DATE_CREATED")
    private String actDateCreated;
    @Column(name = "ACT_DATE_DUE")
    private String actDateDue;
    @Column(name = "ACT_RESOLUTION_DATE")
    private String actResolutionDate;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SEQ_Activity")
    @Basic(optional = false)
    @Column(name = "ACT_ID")
    private Integer actId;
    @Column(name = "ACT_NOTE_USER_CREATE")
    private String actNoteUserCreate;
    @Column(name = "ACT_NOTE_USER_ASSIGNED")
    private String actNoteUserAssigned;
    @Column(name = "ACT_USER_ASSIGNED_ID")
    private Integer actUserAssignedId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "PRJ_ID")
    private Integer prjId;
    @Lob
    @Column(name = "ACT_RESOLUTION_NOTES")
    private String actResolutionNotes;

    public Activity() {

    }

    public Activity(Integer actId) {
        this.actId = actId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        Integer oldActId = this.actId;
        this.actId = actId;
        changeSupport.firePropertyChange("actId", oldActId, actId);
    }

    public String getActDateCreated() {
        return actDateCreated;
    }

    public void setActDateCreated(String actDateCreated) {
        String oldActDateCreated = this.actDateCreated;
        this.actDateCreated = actDateCreated;
        changeSupport.firePropertyChange("actDateCreated", oldActDateCreated, actDateCreated);
    }

    public String getActDateDue() {
        return actDateDue;
    }

    public void setActDateDue(String actDateDue) {
        String oldActDateDue = this.actDateDue;
        this.actDateDue = actDateDue;
        changeSupport.firePropertyChange("actDateDue", oldActDateDue, actDateDue);
    }

    public String getActResolutionDate() {
        return actResolutionDate;
    }

    public void setActResolutionDate(String actResolutionDate) {
        String oldActResolutionDate = this.actResolutionDate;
        this.actResolutionDate = actResolutionDate;
        changeSupport.firePropertyChange("actResolutionDate", oldActResolutionDate, actResolutionDate);
    }

    public String getActNoteUserCreate() {
        return actNoteUserCreate;
    }

    public void setActNoteUserCreate(String actNoteUserCreate) {
        String oldActNoteUserCreate = this.actNoteUserCreate;
        this.actNoteUserCreate = actNoteUserCreate;
        changeSupport.firePropertyChange("actNoteUserCreate", oldActNoteUserCreate, actNoteUserCreate);
    }

    public String getActNoteUserAssigned() {
        return actNoteUserAssigned;
    }

    public void setActNoteUserAssigned(String actNoteUserAssigned) {
        String oldActNoteUserAssigned = this.actNoteUserAssigned;
        this.actNoteUserAssigned = actNoteUserAssigned;
        changeSupport.firePropertyChange("actNoteUserAssigned", oldActNoteUserAssigned, actNoteUserAssigned);
    }

    public Integer getActUserAssignedId() {
        return actUserAssignedId;
    }

    public void setActUserAssignedId(Integer actUserAssignedId) {
        Integer oldActUserAssignedId = this.actUserAssignedId;
        this.actUserAssignedId = actUserAssignedId;
        changeSupport.firePropertyChange("actUserAssignedId", oldActUserAssignedId, actUserAssignedId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        Integer oldUserId = this.userId;
        this.userId = userId;
        changeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        Integer oldPrjId = this.prjId;
        this.prjId = prjId;
        changeSupport.firePropertyChange("prjId", oldPrjId, prjId);
    }

    public String getActResolutionNotes() {
        return actResolutionNotes;
    }

    public void setActResolutionNotes(String actResolutionNotes) {
        String oldActResolutionNotes = this.actResolutionNotes;
        this.actResolutionNotes = actResolutionNotes;
        changeSupport.firePropertyChange("actResolutionNotes", oldActResolutionNotes, actResolutionNotes);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actId != null ? actId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.actId == null && other.actId != null) || (this.actId != null && !this.actId.equals(other.actId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "poams.Activity[ actId=" + actId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
