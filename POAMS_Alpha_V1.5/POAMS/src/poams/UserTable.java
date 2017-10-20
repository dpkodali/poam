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
@Table(name = "user_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTable.findAll", query = "SELECT u FROM UserTable u"),
    @NamedQuery(name = "UserTable.findByUserId", query = "SELECT u FROM UserTable u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserTable.findByUserLast", query = "SELECT u FROM UserTable u WHERE u.userLast = :userLast"),
    @NamedQuery(name = "UserTable.findByUserLogin", query = "SELECT u FROM UserTable u WHERE u.userLogin = :userLogin"),
    @NamedQuery(name = "UserTable.findByUserPassword", query = "SELECT u FROM UserTable u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "UserTable.findByUserRole", query = "SELECT u FROM UserTable u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "UserTable.findByUserFirst", query = "SELECT u FROM UserTable u WHERE u.userFirst = :userFirst"),
    @NamedQuery(name = "UserTable.findByUserActive", query = "SELECT u FROM UserTable u WHERE u.userActive = :userActive")})

public class UserTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "USER_LAST")
    private String userLast;
    @Column(name = "USER_LOGIN")
    private String userLogin;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "USER_ROLE")
    private String userRole;
    @Column(name = "USER_FIRST")
    private String userFirst;
    @Column(name = "USER_ACTIVE")
    private String userActive;

    public UserTable() {
    }

    public UserTable(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLast() {
        return userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    public String getUserActive() {
        return userActive;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "poams.UserTable[ userId=" + userId + " ]";
        return userPassword + ","+ userId.toString();
    }

}
