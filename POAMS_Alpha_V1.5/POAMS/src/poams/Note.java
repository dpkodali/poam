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
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByNoteId", query = "SELECT n FROM Note n WHERE n.noteId = :noteId"),
    @NamedQuery(name = "Note.findByNoteDateCreated", query = "SELECT n FROM Note n WHERE n.noteDateCreated = :noteDateCreated"),
    @NamedQuery(name = "Note.findByNoteTime", query = "SELECT n FROM Note n WHERE n.noteTime = :noteTime"),
    @NamedQuery(name = "Note.findByUserId", query = "SELECT n FROM Note n WHERE n.userId = :userId"),
    @NamedQuery(name = "Note.findByPrjId", query = "SELECT n FROM Note n WHERE n.prjId = :prjId")})
public class Note implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SEQ_note")
    @Basic(optional = false)
    @Column(name = "NOTE_ID")
    private Integer noteId;
    @Column(name = "NOTE_DATE_CREATED")
   // @Temporal(TemporalType.TIMESTAMP)
    private String noteDateCreated;
    @Column(name = "NOTE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteTime;
    @Lob
    @Column(name = "NOTE_TEXT")
    private String noteText;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "PRJ_ID")
    private Integer prjId;

    public Note() {
    }

    public Note(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        Integer oldNoteId = this.noteId;
        this.noteId = noteId;
        changeSupport.firePropertyChange("noteId", oldNoteId, noteId);
    }

    public String getNoteDateCreated() {
        return noteDateCreated;
    }

    public void setNoteDateCreated(String noteDateCreated) {
        String oldNoteDateCreated = this.noteDateCreated;
        this.noteDateCreated = noteDateCreated;
        changeSupport.firePropertyChange("noteDateCreated", oldNoteDateCreated, noteDateCreated);
    }

    public Date getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Date noteTime) {
        Date oldNoteTime = this.noteTime;
        this.noteTime = noteTime;
        changeSupport.firePropertyChange("noteTime", oldNoteTime, noteTime);
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        String oldNoteText = this.noteText;
        this.noteText = noteText;
        changeSupport.firePropertyChange("noteText", oldNoteText, noteText);
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "poams.Note[ noteId=" + noteId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
