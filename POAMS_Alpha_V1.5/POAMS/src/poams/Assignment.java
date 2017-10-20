/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poams;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ShawnKyzer
 */
@Entity
@Table(name = "assignment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a"),
    @NamedQuery(name = "Assignment.findByAssignId", query = "SELECT a FROM Assignment a WHERE a.assignId = :assignId"),
    @NamedQuery(name = "Assignment.findByAssigneeUserId", query = "SELECT a FROM Assignment a WHERE a.assigneeUserId = :assigneeUserId"),
    @NamedQuery(name = "Assignment.findByAssignDate", query = "SELECT a FROM Assignment a WHERE a.assignDate = :assignDate"),
    @NamedQuery(name = "Assignment.findByUserId", query = "SELECT a FROM Assignment a WHERE a.userId = :userId"),
    @NamedQuery(name = "Assignment.findByPrjId", query = "SELECT a FROM Assignment a WHERE a.prjId = :prjId")})
public class Assignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ASSIGN_ID")
    private Integer assignId;
    @Lob
    @Column(name = "ASSIGN_NOTES")
    private String assignNotes;
    @Column(name = "ASSIGNEE_USER_ID")
    private Integer assigneeUserId;
    @Column(name = "ASSIGN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "PRJ_ID")
    private Integer prjId;

    public Assignment() {
    }

    public Assignment(Integer assignId) {
        this.assignId = assignId;
    }

    public Integer getAssignId() {
        return assignId;
    }

    public void setAssignId(Integer assignId) {
        this.assignId = assignId;
    }

    public String getAssignNotes() {
        return assignNotes;
    }

    public void setAssignNotes(String assignNotes) {
        this.assignNotes = assignNotes;
    }

    public Integer getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(Integer assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        this.prjId = prjId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignId != null ? assignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment other = (Assignment) object;
        if ((this.assignId == null && other.assignId != null) || (this.assignId != null && !this.assignId.equals(other.assignId))) {
            return false;
        }
        return true;
    }

    
    public Integer toInt() {
        return assignId;
    }
    
}
