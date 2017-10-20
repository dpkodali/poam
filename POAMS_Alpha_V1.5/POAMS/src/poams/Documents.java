/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poams;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ShawnKyzer
 */
@Entity
@Table(name = "documents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documents.findAll", query = "SELECT d FROM Documents d"),
    @NamedQuery(name = "Documents.findByDocId", query = "SELECT d FROM Documents d WHERE d.docId = :docId"),
    @NamedQuery(name = "Documents.findByDocType", query = "SELECT d FROM Documents d WHERE d.docType = :docType"),
    @NamedQuery(name = "Documents.findByDocUrl", query = "SELECT d FROM Documents d WHERE d.docUrl = :docUrl")})
public class Documents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "DOC_ID")
    private Integer docId;
    @Column(name = "DOC_TYPE")
    private String docType;
    @Column(name = "DOC_URL")
    private String docUrl;
    @Lob
    @Column(name = "DOC_DESC")
    private String docDesc;

    public Documents() {
    }

    public Documents(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "poams.Documents[ docId=" + docId + " ]";
    }
    
}
