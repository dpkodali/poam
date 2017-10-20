package poams;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author: Shawn Kyzer
 */


@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByPrjId", query = "SELECT p FROM Project p WHERE p.prjId = :prjId"),
    @NamedQuery(name = "Project.findByPrjName", query = "SELECT p FROM Project p WHERE p.prjName = :prjName"),
    @NamedQuery(name = "Project.findByPrjActive", query = "SELECT p FROM Project p WHERE p.prjActive = :prjActive"),
    @NamedQuery(name = "Project.findByUserId", query = "SELECT p FROM Project p WHERE p.userId = :userId")})
public class Project implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SEQ_project")
    //@TableGenerator(name="ProjectSequence", pkColumnName="rowid", pkColumnValue="SEQ_NAME", 
    //        valueColumnName="FKEY", table="SEQUENCE", allocationSize=1, initialValue=1)
    @Basic(optional = false)
    @Column(name = "PRJ_ID")
    private Integer prjId;
    @Column(name = "PRJ_NAME")
    private String prjName;
    @Lob
    @Column(name = "PRJ_DESCRIPTION")
    private String prjDescription;
    @Column(name = "PRJ_ACTIVE")
    private String prjActive;
    @Column(name = "USER_ID")
    private Integer userId;

    public Project() {
    }

    public Project(Integer prjId) {
        this.prjId = prjId;
    }

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        Integer oldPrjId = this.prjId;
        this.prjId = prjId;
        changeSupport.firePropertyChange("prjId", oldPrjId, prjId);
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        String oldPrjName = this.prjName;
        this.prjName = prjName;
        changeSupport.firePropertyChange("prjName", oldPrjName, prjName);
    }

    public String getPrjDescription() {
        return prjDescription;
    }

    public void setPrjDescription(String prjDescription) {
        String oldPrjDescription = this.prjDescription;
        this.prjDescription = prjDescription;
        changeSupport.firePropertyChange("prjDescription", oldPrjDescription, prjDescription);
    }

    public String getPrjActive() {
        return prjActive;
    }

    public void setPrjActive(String prjActive) {
        String oldPrjActive = this.prjActive;
        this.prjActive = prjActive;
        changeSupport.firePropertyChange("prjActive", oldPrjActive, prjActive);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        Integer oldUserId = this.userId;
        this.userId = userId;
        changeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prjId != null ? prjId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.prjId == null && other.prjId != null) || (this.prjId != null && !this.prjId.equals(other.prjId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return prjName;
    }
  
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
